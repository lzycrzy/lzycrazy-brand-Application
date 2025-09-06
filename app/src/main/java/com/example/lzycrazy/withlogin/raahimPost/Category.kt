package com.example.lzycrazy.withlogin.raahimPost

data class Category(
    val name: String,
    val subcategories: List<String>,
    var isExpanded: Boolean = false
)
