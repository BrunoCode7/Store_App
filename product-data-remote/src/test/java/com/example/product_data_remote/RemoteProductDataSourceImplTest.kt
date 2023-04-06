package com.example.product_data_remote

import com.example.product_data_remote.networking.ProductService
import com.example.product_data_remote.networking.product.ProductApiModel
import com.example.product_data_remote.networking.product.RatingApiModel
import com.example.product_data_remote.source.RemoteProductDataSourceImpl
import com.example.products_domain.entity.product.Product
import com.example.products_domain.entity.product.Rating
import kotlinx.coroutines.ExperimentalCoroutinesApi
import kotlinx.coroutines.flow.first
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
class RemoteProductDataSourceImplTest {

    private val productService = mock<ProductService>()
    private val productDataSource = RemoteProductDataSourceImpl(productService)


    @ExperimentalCoroutinesApi
    @Test
    fun testGetProducts() = runBlockingTest {
        val expectedProduct = Product(
            "test1", "test1", 1, "test1", 1.0, Rating(1, 1.0), "title1"
        )

        val remoteProduct = ProductApiModel(
            1, "title1", 1.0, "test1", "test1", "test1",
            RatingApiModel(1.0, 1)
        )

        val expectedProducts = listOf(expectedProduct)
        val remoteProducts = listOf(remoteProduct)

        whenever(productService.getProducts()).thenReturn(remoteProducts)

        val result = productDataSource.getProducts().first()
        assertEquals(expectedProducts, result)


    }
}