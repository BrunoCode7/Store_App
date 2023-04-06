package com.example.product_data_remote.networking.product

import com.squareup.moshi.Json

data class ProductApiModel(
    @Json(name = "id") val id: Int,
    @Json(name = "title") val title: String,
    @Json(name = "price") val price: Double,
    @Json(name = "description") val description: String,
    @Json(name = "category") val category: String,
    @Json(name = "image") val image: String,
    @Json(name = "rating") val rating: RatingApiModel,
)
