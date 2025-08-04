package com.example.lzycrazy.withoutlogin.services

import retrofit2.Call
import retrofit2.http.GET

interface ServicesApiInterface {
    @GET("https://api.lzycrazy.com/api/v1/services")
    fun getServices(): Call<List<ServicesAdapterItem>>
}
