package com.example.pravdinai_tinkoffandroid.data.local

import androidx.room.Entity
import androidx.room.ForeignKey
import androidx.room.PrimaryKey
import com.example.pravdinai_tinkoffandroid.data.network.Film
import com.example.pravdinai_tinkoffandroid.data.network.Genre
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.map


@Entity(tableName = "films")
data class FilmEntity(
    @PrimaryKey val filmId: Int,
    val nameRu: String,
    val year: String,
    val posterUrlPreview: String,
    val genre: String,
)


fun Film.toFilmEntity(): FilmEntity{
    return FilmEntity(
        filmId = filmId,
        nameRu = nameRu,
        year = year,
        posterUrlPreview = posterUrlPreview,
        genre = genres.first().genre
    )
}

fun List<FilmEntity>.mapToFilms(): List<Film> {
    return map { filmEntity ->
        Film(
            filmId = filmEntity.filmId,
            nameRu = filmEntity.nameRu,
            year = filmEntity.year,
            posterUrlPreview = filmEntity.posterUrlPreview,
            genres = listOf(Genre(filmEntity.genre)) // Здесь нужно получить список жанров из какого-то репозитория или другого источника
        )
    }
}