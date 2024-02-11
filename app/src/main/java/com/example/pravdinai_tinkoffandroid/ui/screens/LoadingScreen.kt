package com.example.pravdinai_tinkoffandroid.ui.screens

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.width
import androidx.compose.material.CircularProgressIndicator
import androidx.compose.material3.MaterialTheme
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.StrokeCap
import androidx.compose.ui.res.dimensionResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.example.pravdinai_tinkoffandroid.R

@Composable
fun LoadingScreen(
    modifier: Modifier = Modifier
) {
    Box(
        modifier = modifier
            .fillMaxSize()
            .background(MaterialTheme.colorScheme.background),
        contentAlignment = Alignment.Center
    ) {
        CircularProgressIndicator(
            modifier = Modifier.width(70.dp/*dimensionResource(id = R.dimen.progres_indicator_size)*/),
            strokeWidth = dimensionResource(id = R.dimen.medium),
            color = MaterialTheme.colorScheme.primary,
            strokeCap = StrokeCap.Round
        )
    }
}


@Preview
@Composable
fun LoadingScreenPreview() {
    LoadingScreen()
}