package com.example.pravdinai_tinkoffandroid.data.network

import com.example.pravdinai_tinkoffandroid.data.FilmRepository

class FilmNetworkRepository(
    private val filmApiService: FilmApiService
) : FilmRepository {
    override suspend fun getFilms(): List<Film> =
        filmApiService.getFilms().films


    override suspend fun getFilmById(id: Int): FilmDetailed =
        filmApiService.getFilmById(id)

}