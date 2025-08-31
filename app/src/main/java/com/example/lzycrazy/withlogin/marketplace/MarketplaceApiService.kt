package com.example.lzycrazy.withlogin.marketplace

import retrofit2.Response
import retrofit2.http.GET
import retrofit2.http.Query

interface MarketplaceApiService {
    @GET("api/v1/categories/public")
    suspend fun getCategories(): Response<ApiResponse>

    @GET("api/v1/admin/market-post")
    suspend fun getImagePosts(): Response<ImagePostResponse>

    @GET("api/v1/categories/subcategories")
    suspend fun getPostsBySubcategory(
        @Query("category") categoryId: String,
        @Query("subcategory") subcategoryName: String
    ): Response<List<MarketPost>>
}


