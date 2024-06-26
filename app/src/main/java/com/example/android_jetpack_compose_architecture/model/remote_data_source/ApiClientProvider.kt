package com.example.android_jetpack_compose_architecture.model.remote_data_source

import com.jakewharton.retrofit2.converter.kotlinx.serialization.asConverterFactory
import kotlinx.serialization.json.Json
import okhttp3.MediaType.Companion.toMediaType
import retrofit2.Retrofit
import javax.inject.Inject

class ApiClientProvider @Inject constructor() {
    companion object {
        private const val API_END_URL = "https://api.github.com/"
    }

    fun provide(): ApiClient {
        return Retrofit.Builder()
            .baseUrl(API_END_URL)
            .addConverterFactory(
                Json {
                    ignoreUnknownKeys = true
                }.asConverterFactory("application/json".toMediaType())
            )
            .build()
            .create(ApiClient::class.java)
    }
}