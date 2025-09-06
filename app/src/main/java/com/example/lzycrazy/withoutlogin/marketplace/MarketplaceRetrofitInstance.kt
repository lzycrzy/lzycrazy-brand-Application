package com.example.lzycrazy.withoutlogin.marketplace

import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory


object MarketplaceRetrofitInstance {
    val api: MarketplaceApiService by lazy {
        Retrofit.Builder()
            .baseUrl("https://api.lzycrazy.com/")
            .addConverterFactory(GsonConverterFactory.create())
            .build()
            .create(MarketplaceApiService::class.java)
    }
}