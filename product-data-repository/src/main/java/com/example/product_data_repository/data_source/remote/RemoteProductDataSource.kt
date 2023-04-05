package com.example.product_data_repository.data_source.remote

import com.example.products_domain.entity.product.Product
import kotlinx.coroutines.flow.Flow

interface RemoteProductDataSource {
    fun getProducts(): Flow<List<Product>>
}