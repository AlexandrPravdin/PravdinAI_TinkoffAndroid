package com.example.pravdinai_tinkoffandroid.data.network

import kotlinx.serialization.Serializable

@Serializable
data class Response(
    val pagesCount: Int,
    val films: List<Film>
)


/*@Serializable
data class Film(
    val nameRu: String,
    val posterUrl: String,
    val year: Int,
    val genres: List<Items>
)*/

@Serializable
data class Film(
    val countries: List<Country>,
    val filmId: Int,
    val filmLength: String,
    val genres: List<Genre>,
    val isAfisha: Int,
//    val isRatingUp: Any,
//    val nameEn: String,
    val nameRu: String,
    val posterUrl: String,
    val posterUrlPreview: String,
    val rating: String,
//    val ratingChange: Any,
    val ratingVoteCount: Int,
    val year: String
)


@Serializable
data class Genre(
    val genre: String
)

@Serializable
data class Country(
    val country: String
)