package com.example.pravdinai_tinkoffandroid.data

import com.example.pravdinai_tinkoffandroid.data.network.FilmApiService
import com.example.pravdinai_tinkoffandroid.data.network.FilmNetworkRepository
import com.jakewharton.retrofit2.converter.kotlinx.serialization.asConverterFactory
import kotlinx.serialization.ExperimentalSerializationApi
import kotlinx.serialization.json.Json
import okhttp3.MediaType.Companion.toMediaType
import retrofit2.Retrofit

interface AppContainer {
    val filmRepository: FilmRepository
}

class AppDataContainer : AppContainer {
    private val BASE_URL = "https://kinopoiskapiunofficial.tech"

    @OptIn(ExperimentalSerializationApi::class)
    private val json = Json {
        ignoreUnknownKeys = true
        explicitNulls = false
    }

    private val retrofit: Retrofit = Retrofit.Builder()
        .addConverterFactory(json.asConverterFactory("application/json".toMediaType()))
        .baseUrl(BASE_URL).build()

    private val retrofitService: FilmApiService by lazy {
        retrofit.create(FilmApiService::class.java)
    }

    override val filmRepository: FilmRepository by lazy {
        FilmNetworkRepository(retrofitService)
    }

}