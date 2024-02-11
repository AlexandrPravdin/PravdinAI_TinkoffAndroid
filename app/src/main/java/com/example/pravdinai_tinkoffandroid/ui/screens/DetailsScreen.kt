package com.example.pravdinai_tinkoffandroid.ui.screens

import androidx.activity.compose.BackHandler
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.aspectRatio
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.automirrored.filled.ArrowBack
import androidx.compose.material3.BottomSheetScaffold
import androidx.compose.material3.Button
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.material3.TopAppBar
import androidx.compose.material3.TopAppBarDefaults.topAppBarColors
import androidx.compose.material3.rememberBottomSheetScaffoldState
import androidx.compose.runtime.Composable
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.text.SpanStyle
import androidx.compose.ui.text.buildAnnotatedString
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.withStyle
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import coil.compose.AsyncImage
import coil.request.ImageRequest
import com.example.pravdinai_tinkoffandroid.data.network.Country
import com.example.pravdinai_tinkoffandroid.data.network.FilmDetailed
import com.example.pravdinai_tinkoffandroid.data.network.Genre
import com.example.pravdinai_tinkoffandroid.ui.screens.utils.toStringOfCountry
import com.example.pravdinai_tinkoffandroid.ui.screens.utils.toStringOfGenres
import kotlinx.coroutines.launch

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
                        contentDescription = Icons.AutoMirrored.Filled.ArrowBack.name
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
            .padding(16.dp),
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        val textModifier = Modifier
            .fillMaxWidth()
            .padding(bottom = 16.dp)
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

@Preview
@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun SimpleBottomSheetScaffoldSample() {
    val scope = rememberCoroutineScope()
    val scaffoldState = rememberBottomSheetScaffoldState()

    BottomSheetScaffold(
        scaffoldState = scaffoldState,
        sheetPeekHeight = 128.dp,
        sheetContent = {
            Box(
                Modifier
                    .fillMaxWidth()
                    .height(128.dp),
                contentAlignment = Alignment.Center
            ) {
                Text("Swipe up to expand sheet")
            }
            Column(
                Modifier
                    .fillMaxWidth()
                    .padding(64.dp),
                horizontalAlignment = Alignment.CenterHorizontally
            ) {
                Text("Sheet content")
                Spacer(Modifier.height(20.dp))
                Button(
                    onClick = {
                        scope.launch { scaffoldState.bottomSheetState.partialExpand() }
                    }
                ) {
                    Text("Click to collapse sheet")
                }
            }
        }) { innerPadding ->
        Box(
            modifier = Modifier
                .fillMaxSize()
                .padding(innerPadding),
            contentAlignment = Alignment.Center
        ) {
            Text("Scaffold Content")
        }
    }
}