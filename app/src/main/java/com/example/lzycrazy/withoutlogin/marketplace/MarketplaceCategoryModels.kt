package com.example.lzycrazy.withoutlogin.marketplace

import android.os.Parcelable
import kotlinx.parcelize.Parcelize

@Parcelize
data class MarketPost(
    val _id: String,
    val title: String,
    val price: String,
    val description: String,
    val postedBy: UserInfo,
    val location: LocationInfo,
    val category: CategoryRef? = null,
    val images: List<String> = emptyList()
) : Parcelable

@Parcelize
data class ImagePost(
    val _id: String,
    val postUrl: String,
    val type: String,
    val name: String
) : Parcelable

data class ImagePostResponse(
    val success: Boolean,
    val data: List<ImagePost>
)

// --- User & Location Info ---

@Parcelize
data class UserInfo(
    val name: String,
    val memberSince: String
) : Parcelable

@Parcelize
data class LocationInfo(
    val city: String,
    val state: String,
    val neighbourhood: String,
    val coordinates: Coordinates? = null
) : Parcelable

@Parcelize
data class Coordinates(
    val latitude: String,
    val longitude: String
) : Parcelable

// --- Category Models ---

@Parcelize
data class CategoryRef(
    val _id: String,
    val name: String,
    val imageData: ImageData
) : Parcelable

data class ApiResponse(
    val success: Boolean,
    val data: CategoryData
)

data class CategoryData(
    val categories: List<Category>
)

@Parcelize
data class Category(
    val _id: String,
    val name: String,
    val imageData: ImageData,
    val subcategories: List<SubCategory>
) : Parcelable

@Parcelize
data class SubCategory(
    val name: String,
    val imageData: ImageData
) : Parcelable

@Parcelize
data class ImageData(
    val name: String,
    val url: String
) : Parcelable
