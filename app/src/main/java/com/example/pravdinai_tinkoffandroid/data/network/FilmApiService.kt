package com.example.pravdinai_tinkoffandroid.data.network

import retrofit2.http.GET
import retrofit2.http.Header
import retrofit2.http.Path

const val API_KEY = "117f1abe-73cc-43b0-a062-4322a81106d8"
const val CONTENT_TYPE = "application/json"
const val TYPE = "TOP_100_POPULAR_FILMS"

interface FilmApiService {
    @GET("/api/v2.2/films/top")
    suspend fun getFilms(
        @Header("X-API-KEY") apiKey: String = API_KEY,
        @Header("Content-Type") contentType: String = CONTENT_TYPE,
        @Header("type") type: String = TYPE
    ): Response


    @GET("/api/v2.2/films/{id}")
    suspend fun getFilmById(
        @Path("id") id: Int,
        @Header("X-API-KEY") apiKey: String = API_KEY,
        @Header("Content-Type") contentType: String = CONTENT_TYPE,
    ): FilmDetailed
}