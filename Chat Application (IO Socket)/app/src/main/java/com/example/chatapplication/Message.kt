package com.example.chatapplication

data class Message(
    var nickname: String = "",
    var message: String = "" ,
    val isSent: Boolean
)