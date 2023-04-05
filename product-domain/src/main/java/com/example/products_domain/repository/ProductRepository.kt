package com.example.products_domain.repository

import com.example.products_domain.entity.product.Product
import kotlinx.coroutines.flow.Flow

interface ProductRepository {
    fun getProducts(): Flow<List<Product>>
}