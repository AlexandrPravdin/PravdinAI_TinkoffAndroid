package com.example.pravdinai_tinkoffandroid.ui.screens

import androidx.activity.compose.BackHandler
import androidx.compose.foundation.layout.Column
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import com.example.pravdinai_tinkoffandroid.data.network.FilmDetailed

@Composable
fun DetailsScreen(
    film: FilmDetailed,
    onBackButtonPressed: () -> Unit,
    modifier: Modifier = Modifier
) {
    BackHandler {
        onBackButtonPressed()
    }

    Column(modifier = modifier) {
        Text(text = film.nameRu)
    }
}