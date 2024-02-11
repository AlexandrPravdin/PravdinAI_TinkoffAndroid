package com.example.pravdinai_tinkoffandroid.data.network

import com.example.pravdinai_tinkoffandroid.data.FilmRepository

class FilmNetworkRepository(
    private val filmApiService: FilmApiService
) : FilmRepository {
    override suspend fun getFilms(): List<Film> {
        val response = filmApiService.getFilms()
        val filmList: MutableList<Film> = mutableListOf()
        response.films.forEach { item ->
            filmList.add(item)
        }
        val filmList2 = response.films
        return filmList2
    }
}