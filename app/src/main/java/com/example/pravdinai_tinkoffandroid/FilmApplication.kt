package com.example.pravdinai_tinkoffandroid

import android.app.Application
import com.example.pravdinai_tinkoffandroid.data.AppContainer
import com.example.pravdinai_tinkoffandroid.data.AppDataContainer

class FilmApplication : Application() {
    lateinit var container: AppContainer

    override fun onCreate() {
        super.onCreate()
        container = AppDataContainer()
    }
}