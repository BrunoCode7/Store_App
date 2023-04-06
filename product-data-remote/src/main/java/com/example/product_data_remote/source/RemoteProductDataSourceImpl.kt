package com.example.product_data_remote.source

import com.example.product_data_remote.networking.ProductService
import com.example.product_data_remote.networking.product.ProductApiModel
import com.example.product_data_repository.data_source.remote.RemoteProductDataSource
import com.example.products_domain.entity.UseCaseException
import com.example.products_domain.entity.product.Product
import com.example.products_domain.entity.product.Rating
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.catch
import kotlinx.coroutines.flow.flow
import kotlinx.coroutines.flow.map
import javax.inject.Inject

class RemoteProductDataSourceImpl @Inject constructor(
    private val productService: ProductService
) : RemoteProductDataSource {

    override fun getProducts(): Flow<List<Product>> = flow {
        emit(productService.getProducts())
    }.map { products ->
        products.map { productApiModel -> convert(productApiModel) }
    }.catch { throw UseCaseException.ProductException(it) }


    private fun convert(productApiModel: ProductApiModel) = Product(
        category = productApiModel.category,
        description = productApiModel.description,
        id = productApiModel.id,
        image = productApiModel.image,
        price = productApiModel.price,
        rating = Rating(productApiModel.rating.count, productApiModel.rating.rate),
        title = productApiModel.title
    )
}