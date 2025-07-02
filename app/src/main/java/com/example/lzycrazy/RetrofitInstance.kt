package com.example.lzycrazy

import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

object RetrofitInstance {

    private const val BASE_URL = "https://api.lzycrazy.com" // 👈 Replace with your real API base

    val api: ServicesApiInterface by lazy {
        Retrofit.Builder()
            .baseUrl(BASE_URL)
            .addConverterFactory(GsonConverterFactory.create())
            .build()
            .create(ServicesApiInterface::class.java)
    }
}
