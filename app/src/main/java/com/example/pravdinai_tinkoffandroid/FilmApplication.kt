package com.example.pravdinai_tinkoffandroid

import android.app.Application
import com.example.pravdinai_tinkoffandroid.data.AppContainer
import com.example.pravdinai_tinkoffandroid.data.AppDataContainer
import com.example.pravdinai_tinkoffandroid.data.local.AppDatabase

class FilmApplication : Application() {
    lateinit var container: AppContainer

    val database: AppDatabase by lazy {
        AppDatabase.getDatabase(this)
    }

    override fun onCreate() {
        super.onCreate()
        container = AppDataContainer()
    }
}