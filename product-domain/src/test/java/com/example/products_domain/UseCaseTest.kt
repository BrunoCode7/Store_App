package com.example.products_domain

import com.example.products_domain.entity.Result.Success
import com.example.products_domain.usecase.UseCase
import kotlinx.coroutines.ExperimentalCoroutinesApi
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.first
import kotlinx.coroutines.flow.flowOf
import kotlinx.coroutines.test.TestCoroutineDispatcher
import kotlinx.coroutines.test.runBlockingTest
import org.junit.Assert.assertEquals
import org.junit.Before
import org.junit.Test
import org.mockito.kotlin.mock

/**
 * test case for base use case class
 */
class UseCaseTest {

    @ExperimentalCoroutinesApi
    private val configuration = UseCase.Configuration(TestCoroutineDispatcher())
    private val request = mock<UseCase.Request>()
    private val response = mock<UseCase.Response>()

    @ExperimentalCoroutinesApi
    private lateinit var useCase:
            UseCase<UseCase.Request, UseCase.Response>

    @ExperimentalCoroutinesApi
    @Before
    fun setUp() {
        useCase = object : UseCase<UseCase.Request,
                UseCase.Response>(configuration) {
            override fun process(request: Request):
                    Flow<Response> {
                assertEquals(
                    this@UseCaseTest.request,
                    request
                )
                return flowOf(response)
            }
        }
    }

    @ExperimentalCoroutinesApi
    @Test
    fun testExecuteSuccess() = runBlockingTest {
        val result = useCase.execute(request).first()
        assertEquals(Success(response), result)
    }
}