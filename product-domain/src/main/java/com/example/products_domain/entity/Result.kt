package com.example.products_domain.entity

sealed class Result<out T:Any>{
    data class Success<out T:Any>(val data:T):Result<T>()
    class Error(val Exception:UseCaseException):Result<Nothing>()
}
