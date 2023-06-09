package com.example.product_data_remote.di

import com.example.product_data_remote.source.RemoteProductDataSourceImpl
import com.example.product_data_repository.data_source.remote.RemoteProductDataSource
import dagger.Binds
import dagger.Module
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent

@Module
@InstallIn(SingletonComponent::class)
abstract class RemoteDataSourceModule {
    @Binds
    abstract fun bindProductDataSource(
        productDataSourceImpl:
        RemoteProductDataSourceImpl
    ): RemoteProductDataSource

}