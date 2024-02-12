package com.example.pravdinai_tinkoffandroid.data.network

import kotlinx.serialization.Serializable

@Serializable
data class Response(
    val pagesCount: Int, val films: List<Film>
)


@Serializable
data class Film(
    val filmId: Int,
    val nameRu: String,
    val year: String,
    val posterUrlPreview: String,
    val genres: List<Genre>,
)


@Serializable
data class Genre(
    val genre: String
)

@Serializable
data class Country(
    val country: String
)

@Serializable
data class FilmDetailed(
    val countries: List<Country>,
    val description: String,
    val genres: List<Genre>,
    val nameRu: String,
    val posterUrl: String,
    val ratingImdb: Double,
    val serial: Boolean,
    val year: Int
)
