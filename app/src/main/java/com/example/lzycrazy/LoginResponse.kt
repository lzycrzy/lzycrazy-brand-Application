package com.example.lzycrazy

data class LoginResponse(
    val success: Boolean,
    val token: String?,        // Or whatever your backend returns
    val message: String?       // Optional message
)
