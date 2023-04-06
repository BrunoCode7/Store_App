package com.example.product_presentation.model

import android.os.Parcelable
import kotlinx.parcelize.Parcelize

@Parcelize
data class RatingUi(
    val count: Int,
    val rate: Double
) : Parcelable
