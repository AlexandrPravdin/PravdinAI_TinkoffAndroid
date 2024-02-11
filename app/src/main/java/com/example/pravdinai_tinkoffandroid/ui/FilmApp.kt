package com.example.pravdinai_tinkoffandroid.ui

import android.os.Build
import androidx.annotation.RequiresExtension
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.navigation.compose.rememberNavController
import com.example.pravdinai_tinkoffandroid.ui.navigation.AppNavHost

@RequiresExtension(extension = Build.VERSION_CODES.S, version = 7)
@Composable
fun FilmApp(
    modifier: Modifier = Modifier
) {
    val navController = rememberNavController()
    AppNavHost(navController)
}