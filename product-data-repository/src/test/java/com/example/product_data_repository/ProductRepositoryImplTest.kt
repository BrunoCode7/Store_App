package com.example.product_data_repository

import com.example.product_data_repository.data_source.remote.RemoteProductDataSource
import com.example.product_data_repository.data_source.repository.ProductRepositoryImpl
import com.example.products_domain.entity.product.Product
import com.example.products_domain.entity.product.Rating
import kotlinx.coroutines.ExperimentalCoroutinesApi
import kotlinx.coroutines.flow.first
import kotlinx.coroutines.flow.flowOf
import kotlinx.coroutines.test.runBlockingTest
import org.junit.Assert.assertEquals
import org.junit.Test
import org.mockito.kotlin.mock
import org.mockito.kotlin.whenever

/**
 * Example local unit test, which will execute on the development machine (host).
 *
 * See [testing documentation](http://d.android.com/tools/testing).
 */
class ProductRepositoryImplTest {
    private val remoteProductDataSource = mock<RemoteProductDataSource>()
    private val repositoryImpl = ProductRepositoryImpl(remoteProductDataSource)

    @ExperimentalCoroutinesApi
    @Test
    fun testGetProducts() = runBlockingTest {
        val product1 = Product(
            "test1", "test1", 1, "test1", 1.0, Rating(1, 1.0), "title1"
        )
        val product2 = Product(
            "test2", "test2", 2, "test2", 2.0, Rating(2, 2.0), "title2"
        )
        val product3 = Product(
            "test3", "test3", 3, "test3", 3.0, Rating(3, 3.0), "title3"
        )

        val list = listOf(product1, product2, product3)
        whenever(remoteProductDataSource.getProducts()).thenReturn(flowOf(list))
        val result = repositoryImpl.getProducts().first()
        assertEquals(list, result)
    }
}