package com.example.pravdinai_tinkoffandroid.ui

import android.os.Build
import androidx.annotation.RequiresExtension
import androidx.lifecycle.ViewModelProvider
import androidx.lifecycle.viewmodel.CreationExtras
import androidx.lifecycle.viewmodel.initializer
import androidx.lifecycle.viewmodel.viewModelFactory
import com.example.pravdinai_tinkoffandroid.FilmApplication
import com.example.pravdinai_tinkoffandroid.ui.screens.FilmViewModel

object AppViewModelProvider {
    @RequiresExtension(extension = Build.VERSION_CODES.S, version = 7)
    val Factory = viewModelFactory {
        initializer {
            FilmViewModel(
                appApplication().container.filmRepository
            )
        }

    }
}

fun CreationExtras.appApplication(): FilmApplication =
    (this[ViewModelProvider.AndroidViewModelFactory.APPLICATION_KEY] as FilmApplication)