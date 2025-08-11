package com.example.lzycrazy.auth

data class RegisterRequest(
    val fullName: String,
    val email: String,
    val phone: String,
    val password: String
)