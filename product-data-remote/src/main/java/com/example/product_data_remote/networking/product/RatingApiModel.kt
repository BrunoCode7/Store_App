package com.example.product_data_remote.networking.product

import com.squareup.moshi.Json

data class RatingApiModel (
        @Json(name = "rate") val rate: Double,
        @Json(name = "count") val count: Int,
)