package com.example.lzycrazy.withoutlogin.services

// ServicesApiInterface.kt
import retrofit2.Call
import retrofit2.http.GET

interface ServicesApiInterface {
    @GET("/api/v1/services")
    fun getServices(): Call<ServicesResponse>
}
