package com.example.pravdinai_tinkoffandroid.data.local

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import kotlinx.coroutines.flow.Flow

@Dao
interface FilmEntityDao {
    @Query("SELECT * FROM FILMS")
    fun getAllFilms(): Flow<List<FilmEntity>>

    @Insert(onConflict = OnConflictStrategy.IGNORE)
    suspend fun insert(filmEntity: FilmEntity)
}