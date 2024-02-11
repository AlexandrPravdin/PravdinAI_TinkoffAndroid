package com.example.pravdinai_tinkoffandroid.data.network

import kotlinx.serialization.Serializable

@Serializable
data class Response(
    val pagesCount: Int, val films: List<Film>
)


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

@Serializable
data class FilmDetailed(
//    val completed: Boolean,
    val countries: List<Country>,
//    val coverUrl: String,
    val description: String,
//    val editorAnnotation: String,
//    val endYear: Int,
//    val filmLength: Int,
    val genres: List<Genre>,
//    val has3D: Boolean,
//    val hasImax: Boolean,
//    val imdbId: String,
//    val isTicketsAvailable: Boolean,
//    val kinopoiskHDId: String,
//    val kinopoiskId: Int,
//    val lastSync: String,
//    val logoUrl: String,
//    val nameEn: String,
//    val nameOriginal: String,
    val nameRu: String,
    val posterUrl: String,
//    val posterUrlPreview: String,
//    val productionStatus: String,
//    val ratingAgeLimits: String,
//    val ratingAwait: Double,
//    val ratingAwaitCount: Int,
//    val ratingFilmCritics: Double,
//    val ratingFilmCriticsVoteCount: Int,
//    val ratingGoodReview: Double,
//    val ratingGoodReviewVoteCount: Int,
    val ratingImdb: Double,
//    val ratingImdbVoteCount: Int,
//    val ratingKinopoisk: Double,
//    val ratingKinopoiskVoteCount: Int,
//    val ratingMpaa: String,
//    val ratingRfCritics: Double,
//    val ratingRfCriticsVoteCount: Int,
//    val reviewsCount: Int,
    val serial: Boolean,
//    val shortDescription: String,
//    val shortFilm: Boolean,
//    val slogan: String,
//    val startYear: Int,
//    val type: String,
//    val webUrl: String,
    val year: Int
)
