package com.example.test2ui

import android.content.ContentValues
import android.content.Context
import android.database.Cursor
import android.database.sqlite.SQLiteDatabase
import android.database.sqlite.SQLiteOpenHelper
import android.util.Log

class DatabaseHelper(context: Context) : SQLiteOpenHelper(context, DATABASE_NAME, null, DATABASE_VERSION) {

    companion object {
        private const val DATABASE_NAME = "test2.db"
        private const val DATABASE_VERSION = 1
        private const val TABLE_NAME = "commentTable"
        private const val COLUMN_ID = "id"
        private const val COLUMN_POSTID = "postid"
        private const val COLUMN_NAME = "name"
        private const val COLUMN_EMAIL = "email"
        private const val COLUMN_COMMENT = "comment"
    }

    override fun onCreate(db: SQLiteDatabase) {
        val createTable = "CREATE TABLE $TABLE_NAME (" +
                "$COLUMN_ID INTEGER PRIMARY KEY, " +
                "$COLUMN_NAME TEXT, " +
                "$COLUMN_EMAIL TEXT, " +
                "$COLUMN_POSTID INTEGER, " +
                "$COLUMN_COMMENT TEXT " +
                ")"
        db.execSQL(createTable)
    }

    override fun onUpgrade(db: SQLiteDatabase, oldVersion: Int, newVersion: Int) {
        db.execSQL("DROP TABLE IF EXISTS $TABLE_NAME")
        onCreate(db)
    }

    fun insertComment(comment: CommentDataClassItem) {
        val db = writableDatabase
        try {
            val contentValues = ContentValues().apply {
                put(COLUMN_ID, comment.id)
                put(COLUMN_NAME, comment.name)
                put(COLUMN_EMAIL, comment.email)
                put(COLUMN_COMMENT, comment.body)
                put(COLUMN_POSTID, comment.postId)
            }
            db.insert(TABLE_NAME, null, contentValues)
            Log.d("DatabaseHelper", "Inserted product: ${comment.name}")
        } catch (e: Exception) {
            Log.e("DatabaseHelper", "Error inserting product: ${comment.email}, ${e.message}")
        } finally {
            db.close()
        }
    }

    fun getAllComments(): ArrayList<CommentDataClassItem> {
        val productList = ArrayList<CommentDataClassItem>()
        val db = readableDatabase
        var cursor: Cursor? = null
        try {
            cursor = db.rawQuery("SELECT * FROM $TABLE_NAME", null)
            cursor?.let {
                while (it.moveToNext()) {
                    val id = it.getInt(it.getColumnIndexOrThrow(COLUMN_ID))
                    val name = it.getString(it.getColumnIndexOrThrow(COLUMN_NAME))
                    val email = it.getString(it.getColumnIndexOrThrow(COLUMN_EMAIL))
                    val comment = it.getString(it.getColumnIndexOrThrow(COLUMN_COMMENT))
                    val postid = it.getInt(it.getColumnIndexOrThrow(COLUMN_POSTID))
                    productList.add(CommentDataClassItem(id = id, name = name, email = email, body = comment, postId = postid))
                }
            }
        } catch (e: Exception) {
            Log.e("DatabaseHelper", "Error fetching all products: ${e.message}")
        } finally {
            cursor?.close()
            db.close()
        }
        return productList
    }

    fun deleteComment(commentId: Int) {
        val db = writableDatabase
        val whereClause = "$COLUMN_ID = ?"
        val whereArgs = arrayOf(commentId.toString())
        db.delete(TABLE_NAME, whereClause, whereArgs)
        db.close()
    }
}
