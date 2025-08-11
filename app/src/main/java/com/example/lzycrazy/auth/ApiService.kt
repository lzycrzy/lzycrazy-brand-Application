package com.example.lzycrazy.auth

import com.example.lzycrazy.auth.ForgotPasswordRequest
import com.example.lzycrazy.auth.LoginRequest
import com.example.lzycrazy.auth.LoginResponse
import com.example.lzycrazy.auth.RegisterRequest
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