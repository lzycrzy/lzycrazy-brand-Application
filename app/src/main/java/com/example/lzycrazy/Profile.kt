package com.example.lzycrazy

data class Profile(
    val email: String,
    val phone: String,
    val gender: String,
    val country: String,
    val state: String,
    val city: String,
    val businessProfileLink: String? = null,
    val neighbour: String? = null,
    val dateOfBirth: String? = null
)

