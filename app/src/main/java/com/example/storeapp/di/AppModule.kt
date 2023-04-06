package com.example.storeapp.di

import android.app.Application
import com.example.products_domain.usecase.UseCase
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.HiltAndroidApp
import dagger.hilt.components.SingletonComponent
import kotlinx.coroutines.Dispatchers

@Module
@InstallIn(SingletonComponent::class)
class AppModule {
    @Provides
    fun provideUseCaseConfiguration() =
        UseCase.Configuration(Dispatchers.IO)
}


@HiltAndroidApp
class ProductApplication : Application()