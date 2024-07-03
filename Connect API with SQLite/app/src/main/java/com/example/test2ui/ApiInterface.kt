package com.example.test2ui

import retrofit2.Call
import retrofit2.http.GET

interface ApiInterface {
    @GET("/comments")
    fun getCommentData(): Call<List<CommentDataClassItem>>
}

