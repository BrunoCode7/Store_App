package com.example.product_data_remote.networking

import com.example.product_data_remote.networking.product.ProductApiModel
import retrofit2.http.GET

interface ProductService {
    @GET("/products")
    suspend fun getProducts():List<ProductApiModel>
}