package com.example.products_domain.usecase

import com.example.products_domain.entity.product.Product
import com.example.products_domain.repository.ProductRepository
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.map
import javax.inject.Inject

class GetProductsListUseCase @Inject constructor(
    configuration: Configuration,
    private val productRepository: ProductRepository
) : UseCase<GetProductsListUseCase.Request, GetProductsListUseCase.Response>(configuration = configuration) {

    override fun process(request: Request): Flow<Response> =
        productRepository.getProducts().map {
            Response(it)
        }

    object Request : UseCase.Request
    data class Response(val data: List<Product>) :
        UseCase.Response

}