package com.example.products_domain.entity

sealed class UseCaseException(cause: Throwable) : Throwable(cause) {

    class ProductException(cause: Throwable) : UseCaseException(cause)
    class UnKnownException(cause: Throwable) : UseCaseException(cause)


    companion object {
        fun createFromThrowable(throwable: Throwable): UseCaseException {
            return if (throwable is UseCaseException) throwable else UnKnownException(throwable)
        }
    }
}
