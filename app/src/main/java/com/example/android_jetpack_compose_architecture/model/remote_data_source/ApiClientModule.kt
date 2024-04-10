package com.example.android_jetpack_compose_architecture.model.remote_data_source

import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
// only one module instance.
object ApiClientModule {
    @Provides
    @Singleton
    fun provideApiClient(apiClientProvider: ApiClientProvider): ApiClient {
        return apiClientProvider.provide()
    }
}