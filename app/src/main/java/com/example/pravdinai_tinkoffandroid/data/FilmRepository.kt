package com.example.pravdinai_tinkoffandroid.data

import com.example.pravdinai_tinkoffandroid.data.network.Film
import com.example.pravdinai_tinkoffandroid.data.network.FilmDetailed

interface FilmRepository {
    suspend fun getFilms(): List<Film>
    suspend fun getFilmById(id: Int): FilmDetailed
}