package com.example.coonectingapiwithsqlite

import android.content.ContentValues
import android.content.Context
import android.database.Cursor
import android.database.sqlite.SQLiteDatabase
import android.database.sqlite.SQLiteOpenHelper
import android.util.Log
import com.example.coonectingapiwithsqlite.Product
import com.google.gson.Gson
import com.google.gson.reflect.TypeToken

class DatabaseHelper(context: Context) : SQLiteOpenHelper(context, DATABASE_NAME, null, DATABASE_VERSION) {

    companion object {
        private const val DATABASE_NAME = "test2.db"
        private const val DATABASE_VERSION = 1
        private const val TABLE_NAME = "productTable"
        private const val COLUMN_ID = "id"
        private const val COLUMN_TITLE = "title"
        private const val COLUMN_DESCRIPTION = "description"
        private const val COLUMN_PRICE = "price"
        private const val COLUMN_BRAND = "brand"
        private const val COLUMN_CATEGORY = "category"
        private const val COLUMN_RATING = "rating"
        private const val COLUMN_IMAGE = "image"
    }

    override fun onCreate(db: SQLiteDatabase) {
        val createTable = "CREATE TABLE $TABLE_NAME (" +
                "$COLUMN_ID INTEGER PRIMARY KEY, " +
                "$COLUMN_TITLE TEXT, " +
                "$COLUMN_DESCRIPTION TEXT, " +
                "$COLUMN_PRICE REAL, " +
                "$COLUMN_BRAND TEXT, " +
                "$COLUMN_CATEGORY TEXT, " +
                "$COLUMN_RATING REAL, " +
                "$COLUMN_IMAGE TEXT" +
                ")"
        db.execSQL(createTable)
    }

    override fun onUpgrade(db: SQLiteDatabase, oldVersion: Int, newVersion: Int) {
        db.execSQL("DROP TABLE IF EXISTS $TABLE_NAME")
        onCreate(db)
    }

    fun insertProduct(product: Product) {
        val db = writableDatabase
        try {
            val contentValues = ContentValues().apply {
                put(COLUMN_ID, product.id)
                put(COLUMN_TITLE, product.title)
                put(COLUMN_DESCRIPTION, product.description)
                put(COLUMN_PRICE, product.price)
                put(COLUMN_CATEGORY, product.category)
                put(COLUMN_BRAND, product.brand)
                put(COLUMN_RATING, product.rating)
                put(COLUMN_IMAGE, Gson().toJson(product.images))
            }
            db.insert(TABLE_NAME, null, contentValues)
            Log.d("DatabaseHelper", "Inserted product: ${product.title}")
        } catch (e: Exception) {
            Log.e("DatabaseHelper", "Error inserting product: ${product.title}, ${e.message}")
        } finally {
            db.close()
        }
    }

    fun getAllProducts(): ArrayList<Product> {
        val productList = ArrayList<Product>()
        val db = readableDatabase
        var cursor: Cursor? = null
        try {
            cursor = db.rawQuery("SELECT * FROM $TABLE_NAME", null)
            cursor?.let {
                while (it.moveToNext()) {
                    val id = it.getInt(it.getColumnIndexOrThrow(COLUMN_ID))
                    val title = it.getString(it.getColumnIndexOrThrow(COLUMN_TITLE))
                    val description = it.getString(it.getColumnIndexOrThrow(COLUMN_DESCRIPTION))
                    val price = it.getDouble(it.getColumnIndexOrThrow(COLUMN_PRICE))
                    val rating = it.getDouble(it.getColumnIndexOrThrow(COLUMN_RATING))
                    val category = it.getString(it.getColumnIndexOrThrow(COLUMN_CATEGORY))
                    val brand = it.getString(it.getColumnIndexOrThrow(COLUMN_BRAND))
                    val imagesJson = it.getString(it.getColumnIndexOrThrow(COLUMN_IMAGE))
                    val images: List<String> = Gson().fromJson(imagesJson, object : TypeToken<List<String>>() {}.type)
                    productList.add(Product(id=id, title=title, description=description, price=price, brand=brand, category=category, rating=rating, images=images))
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
}
