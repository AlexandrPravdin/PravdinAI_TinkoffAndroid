package com.example.pravdinai_tinkoffandroid.ui.screens

import androidx.activity.compose.BackHandler
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.aspectRatio
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.automirrored.filled.ArrowBack
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.material3.TopAppBar
import androidx.compose.material3.TopAppBarDefaults.topAppBarColors
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.dimensionResource
import androidx.compose.ui.text.SpanStyle
import androidx.compose.ui.text.buildAnnotatedString
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.withStyle
import androidx.compose.ui.tooling.preview.Preview
import coil.compose.AsyncImage
import coil.request.ImageRequest
import com.example.pravdinai_tinkoffandroid.R
import com.example.pravdinai_tinkoffandroid.data.network.Country
import com.example.pravdinai_tinkoffandroid.data.network.FilmDetailed
import com.example.pravdinai_tinkoffandroid.data.network.Genre
import com.example.pravdinai_tinkoffandroid.ui.screens.utils.toStringOfCountry
import com.example.pravdinai_tinkoffandroid.ui.screens.utils.toStringOfGenres

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun DetailsScreen(
    film: FilmDetailed,
    onBackButtonPressed: () -> Unit,
    modifier: Modifier = Modifier
) {
    BackHandler {
        onBackButtonPressed()
    }
    Scaffold(topBar = {
        TopAppBar(
            modifier = modifier,
            navigationIcon = {
                IconButton(
                    onClick = { onBackButtonPressed() }) {
                    Icon(
                        Icons.AutoMirrored.Filled.ArrowBack,
                        contentDescription = Icons.AutoMirrored.Filled.ArrowBack.name,
                        tint = Color.White
                    )
                }
            },
            title = {},
            colors = topAppBarColors(
                containerColor = Color.Transparent,
            )
        )

    }) { innerPadding ->
        val padding = innerPadding
        DetailsColumn(film = film, modifier)
    }
}

@Composable
private fun DetailsColumn(
    film: FilmDetailed,
    modifier: Modifier = Modifier
) {
    LazyColumn(
        modifier = modifier,
        horizontalAlignment = Alignment.CenterHorizontally,
    )
    {
        item {
            AsyncImage(
                model = ImageRequest.Builder(context = LocalContext.current)
                    .data(film.posterUrl)
                    .crossfade(true)
                    .build(),
                contentDescription = film.nameRu,
                contentScale = ContentScale.Crop,
                modifier = Modifier
                    .fillMaxWidth()
                    .aspectRatio(2f / 3f)
            )
            TextColumn(film = film, modifier)
        }
    }
}


@Composable
private fun TextColumn(
    film: FilmDetailed,
    modifier: Modifier = Modifier
) {
    Column(
        modifier = modifier
            .fillMaxWidth()
            .padding(dimensionResource(id = R.dimen.large)),
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        val textModifier = Modifier
            .fillMaxWidth()
            .padding(bottom = dimensionResource(id = R.dimen.large))
        Text(
            modifier = textModifier,
            text = film.nameRu,
            color = MaterialTheme.colorScheme.onPrimaryContainer,
            style = MaterialTheme.typography.headlineSmall,
            fontWeight = FontWeight.Bold,
        )
        Text(
            modifier = textModifier,
            text = film.description,
            color = MaterialTheme.colorScheme.secondary,
            style = MaterialTheme.typography.bodyLarge,
        )
        Text(
            modifier = textModifier,
            text = buildAnnotatedString {
                withStyle(
                    style = SpanStyle(fontWeight = FontWeight.Bold)
                ) {
                    append(
                        if (film.genres.size == 1) {
                            "Жанр:"
                        } else {
                            "Жанры:"
                        }
                    )
                }
                append(film.genres.toStringOfGenres())
            },
            color = MaterialTheme.colorScheme.secondary,
            style = MaterialTheme.typography.bodyLarge,
        )
        Text(
            modifier = textModifier,
            text = buildAnnotatedString {
                withStyle(
                    style = SpanStyle(fontWeight = FontWeight.Bold)
                ) {
                    append(
                        if (film.countries.size == 1) {
                            "Страна:"
                        } else {
                            "Страны:"
                        }
                    )
                }
                append(film.countries.toStringOfCountry())
            },
            color = MaterialTheme.colorScheme.secondary,
            style = MaterialTheme.typography.bodyLarge,
        )
    }
}


@Preview(showBackground = true)
@Composable
fun DetailsScreenPreview() {
    val filmDetailedMokup = FilmDetailed(
        countries = listOf(Country("USA")),
        description = "Хакер Нео узнает, что его мир — виртуальный. Выдающийся экшен, доказавший, что зрелищное кино может быть умным",
        genres = listOf(Genre("Шок контент"), Genre("Шок2 контент")),
        nameRu = "Матрица",
        posterUrl = "https://kinopoiskapiunofficial.tech/images/posters/kp/301.jpg",
        ratingImdb = 8.7,
        serial = false,
        year = 1999
    )

    DetailsScreen(film = filmDetailedMokup,
        onBackButtonPressed = { })
}