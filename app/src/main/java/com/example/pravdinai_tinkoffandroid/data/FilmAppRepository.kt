package com.example.pravdinai_tinkoffandroid.data

import com.example.pravdinai_tinkoffandroid.data.network.Film
import com.example.pravdinai_tinkoffandroid.data.network.FilmApiService
import com.example.pravdinai_tinkoffandroid.data.network.FilmDetailed

class FilmNetworkRepository(
    private val filmApiService: FilmApiService,
) : FilmRepository {
    override suspend fun getFilms(): List<Film> =
        filmApiService.getFilms().films


    override suspend fun getFilmById(id: Int): FilmDetailed =
        filmApiService.getFilmById(id)


}