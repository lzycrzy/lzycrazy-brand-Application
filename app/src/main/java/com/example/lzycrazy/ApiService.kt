package com.example.lzycrazy

import retrofit2.Call
import retrofit2.http.Body
import retrofit2.http.POST

interface ApiService {

    @POST("api/v1/users/login")
    fun loginUser(@Body request: LoginRequest): Call<LoginResponse>

    @POST("api/v1/users/register")
    fun registerUser(@Body request: RegisterRequest): Call<Void>

    @POST("api/v1/users/password/forgot")
    fun forgotPassword(@Body request: ForgotPasswordRequest): Call<Void>

}
