package com.example.product_data_repository.data_source.di

import com.example.product_data_repository.data_source.repository.ProductRepositoryImpl
import com.example.products_domain.repository.ProductRepository
import dagger.Binds
import dagger.Module
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent

@Module
@InstallIn(SingletonComponent::class)
abstract class RepositoryModule {

    @Binds
    abstract fun bindProductRepository(productRepositoryImpl: ProductRepositoryImpl):ProductRepository

}