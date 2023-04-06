package com.example.product_data_repository.data_source.repository

import com.example.product_data_repository.data_source.remote.RemoteProductDataSource
import com.example.products_domain.entity.product.Product
import com.example.products_domain.repository.ProductRepository
import kotlinx.coroutines.flow.Flow
import javax.inject.Inject

class ProductRepositoryImpl @Inject constructor(
    private val remoteProductDataSource: RemoteProductDataSource

) : ProductRepository {
    override fun getProducts(): Flow<List<Product>> = remoteProductDataSource.getProducts()
}