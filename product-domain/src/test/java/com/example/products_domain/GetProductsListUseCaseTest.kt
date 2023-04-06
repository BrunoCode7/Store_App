package com.example.products_domain

import com.example.products_domain.entity.product.Product
import com.example.products_domain.entity.product.Rating
import com.example.products_domain.repository.ProductRepository
import com.example.products_domain.usecase.GetProductsListUseCase
import kotlinx.coroutines.ExperimentalCoroutinesApi
import kotlinx.coroutines.flow.first
import kotlinx.coroutines.flow.flowOf
import kotlinx.coroutines.test.runBlockingTest
import org.junit.Assert.assertEquals
import org.junit.Test
import org.mockito.kotlin.mock
import org.mockito.kotlin.whenever


/**
 * test case for GetProductsListUseCase class
 */

class GetProductsListUseCaseTest {
    private val productRepository = mock<ProductRepository>()
    private val useCase = GetProductsListUseCase(
        configuration = mock(),
        productRepository = productRepository
    )

    @ExperimentalCoroutinesApi
    @Test
    fun testProcess() = runBlockingTest {
        val product1 = Product(
            "test1", "test1", 1, "test1", 1.0, Rating(1, 1.0), "title1"
        )
        val product2 = Product(
            "test2", "test2", 2, "test2", 2.0, Rating(2, 2.0), "title2"
        )
        val product3 = Product(
            "test3", "test3", 3, "test3", 3.0, Rating(3, 3.0), "title3"
        )

        whenever(productRepository.getProducts()).thenReturn(
            flowOf(
                listOf(
                    product1,
                    product2,
                    product3
                )
            )
        )

        val response = useCase.process(GetProductsListUseCase.Request).first()

        assertEquals(
            GetProductsListUseCase.Response(listOf(product1, product2, product3)),
            response
        )

    }


}