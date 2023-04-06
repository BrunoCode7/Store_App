package com.example.product_presentation.model

import android.os.Parcelable
import kotlinx.parcelize.Parcelize

@Parcelize
data class ProductUi(
    val category: String,
    val description: String,
    val id: Int,
    val image: String,
    val price: Double,
    val rating: RatingUi,
    val title: String
) : Parcelable