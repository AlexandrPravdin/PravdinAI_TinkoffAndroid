package com.example.pravdinai_tinkoffandroid.ui.screens

import android.net.http.HttpException
import android.os.Build
import androidx.annotation.RequiresExtension
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.setValue
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.pravdinai_tinkoffandroid.data.FilmRepository
import com.example.pravdinai_tinkoffandroid.data.network.Film
import kotlinx.coroutines.launch
import okio.IOException

sealed interface HomeUiState {
    data class Success(
        val response: List<Film>,
        var currentSelectedFilm: Film
    ) : HomeUiState

    data class Error(
        val errorName: String
    ) : HomeUiState

    data object Loading : HomeUiState
}

@RequiresExtension(extension = Build.VERSION_CODES.S, version = 7)
class FilmViewModel(
    private val filmRepository: FilmRepository,
) : ViewModel() {

    var uiState: HomeUiState by mutableStateOf(
        HomeUiState.Loading
    )
        private set


    init {
        initializeUiState()
    }

    fun initializeUiState() {
        uiState = HomeUiState.Loading
        viewModelScope.launch {
            uiState = try {
                val response = filmRepository.getFilms()
                HomeUiState.Success(
                    response = response,
                    currentSelectedFilm = response[0]
                )
            } catch (e: IOException) {
                HomeUiState.Error(errorName = e.message ?: "")
            } catch (e: HttpException) {
                HomeUiState.Error(errorName = e.message ?: "")
            }

        }
    }

    fun updateDetailsScreenStates(film: Film){
        uiState = (uiState as HomeUiState.Success).copy(
            currentSelectedFilm = film
        )
    }
}