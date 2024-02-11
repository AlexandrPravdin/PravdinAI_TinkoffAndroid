package com.example.pravdinai_tinkoffandroid.ui.screens

import androidx.compose.foundation.layout.Column
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import com.example.pravdinai_tinkoffandroid.data.network.Film

@Composable
fun DetailsScreen(
    film: Film,
    modifier: Modifier
) {
    Column(modifier = modifier) {
        Text(text = film.nameRu)
    }
}