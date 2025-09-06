package com.example.lzycrazy.auth

import com.example.lzycrazy.auth.ApiService
import okhttp3.OkHttpClient
import okhttp3.logging.HttpLoggingInterceptor
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

object ApiClient {
    //  To access localhost from Android Emulator
    private const val BASE_URL = "https://api.lzycrazy.com/"

    // Logging interceptor to see network logs
    private val logger = HttpLoggingInterceptor().apply {
        level = HttpLoggingInterceptor.Level.BODY
    }

    // OkHttpClient with logging
    private val client = OkHttpClient.Builder()
        .addInterceptor(logger)
        .build()

    // Retrofit instance with Gson converter and OkHttp client
    val instance: ApiService by lazy {
        Retrofit.Builder()
            .baseUrl(BASE_URL)
            .addConverterFactory(GsonConverterFactory.create())
            .client(client)
            .build()
            .create(ApiService::class.java)
    }
}