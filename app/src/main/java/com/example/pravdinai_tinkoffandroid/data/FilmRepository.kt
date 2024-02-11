package com.example.pravdinai_tinkoffandroid.data

import com.example.pravdinai_tinkoffandroid.data.network.Film

interface FilmRepository {
    suspend fun getFilms(): List<Film>
}