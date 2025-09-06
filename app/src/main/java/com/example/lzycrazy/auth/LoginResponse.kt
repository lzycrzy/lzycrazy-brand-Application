package com.example.lzycrazy.auth

data class LoginResponse(
    val success: Boolean,
    val token: String?,        // Or whatever your backend returns
<<<<<<< HEAD:app/src/main/java/com/example/lzycrazy/LoginResponse.kt
    val message: String?
// Optional message
)
=======
    val message: String?       // Optional message
)
>>>>>>> 3818ee7e7ac955f3076add2364d7e5ed85f9236b:app/src/main/java/com/example/lzycrazy/auth/LoginResponse.kt
