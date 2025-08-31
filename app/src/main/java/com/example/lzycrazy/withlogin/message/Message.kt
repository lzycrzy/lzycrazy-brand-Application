package com.example.lzycrazy.withlogin.message

data class Message(
    val text: String,
    val isSentByUser: Boolean, // true if you, false if the other person
    val time: String
)
