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
//    @ForeignKey(entity = GenreEntity::class, parentColumns = ["id"], childColumns = ["genreId"])
    val genreId: Int,
//    @ForeignKey(entity = CountryEntity::class, parentColumns = ["id"], childColumns = ["countryId"])
)

fun FilmEntity.toFilm(): Film{
    return Film(
        filmId = filmId,
        nameRu = nameRu,
        year = year,
        posterUrlPreview = posterUrlPreview,
        genres = listOf(Genre("fantazy"))
    )
}

fun Film.toFilmEntity(): FilmEntity{
    return FilmEntity(
        filmId = filmId,
        nameRu = nameRu,
        year = year,
        posterUrlPreview = posterUrlPreview,
        genreId = 1
    )
}

fun List<FilmEntity>.mapToFilms(): List<Film> {
    return map { filmEntity ->
        Film(
            filmId = filmEntity.filmId,
            nameRu = filmEntity.nameRu,
            year = filmEntity.year,
            posterUrlPreview = filmEntity.posterUrlPreview,
            genres = listOf(Genre("filmEntity.genreId")) // Здесь нужно получить список жанров из какого-то репозитория или другого источника
        )
    }
}



/*
@Entity(tableName = "film_info")
data class FilmInfoEntity(
    @PrimaryKey(autoGenerate = true) val id: Long = 0,
    val filmId: Int, // Внешний ключ
    val nameRu: String,
    val posterUrl: String,
    val description: String,
    @ForeignKey(entity = GenreEntity::class, parentColumns = ["id"], childColumns = ["genreId"])
    val genreId: Int,
    @ForeignKey(entity = CountryEntity::class, parentColumns = ["id"], childColumns = ["countryId"])
    val countryId: Int
)

@Entity(tableName = "genres")
data class GenreEntity(
    @PrimaryKey(autoGenerate = true) val id: Int,
    val genre: String
)

@Entity(tableName = "countries")
data class CountryEntity(
    @PrimaryKey(autoGenerate = true) val id: Int,
    val country: String
)
*/
