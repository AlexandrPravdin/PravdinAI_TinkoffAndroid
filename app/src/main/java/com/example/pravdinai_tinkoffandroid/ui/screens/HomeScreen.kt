package com.example.pravdinai_tinkoffandroid.ui.screens

import androidx.compose.foundation.ExperimentalFoundationApi
import androidx.compose.foundation.background
import androidx.compose.foundation.combinedClickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.aspectRatio
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.material3.TopAppBar
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.hapticfeedback.HapticFeedbackType
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.platform.LocalHapticFeedback
import androidx.compose.ui.res.dimensionResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextOverflow
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import coil.compose.AsyncImage
import coil.request.ImageRequest
import com.example.pravdinai_tinkoffandroid.R
import com.example.pravdinai_tinkoffandroid.data.network.Film
import com.example.pravdinai_tinkoffandroid.data.network.Genre
import com.example.pravdinai_tinkoffandroid.ui.screens.utils.firstLetterToUpperCase


@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun HomeScreen(
    allFilms: List<Film>,
    onCardClick: (Film) -> Unit,
    onLongClick: (Film) -> Unit,
    title: String,
    modifier: Modifier = Modifier
) {
    Scaffold(
        topBar = {
            TopAppBar(
                modifier = modifier,
                title = {
                    Text(
                        text = title,
                        style = MaterialTheme.typography.headlineMedium,
                        fontWeight = FontWeight.Bold
                    )
                })
        },
    ) { innerPadding ->
        HomeScreenColumn(
            modifier = modifier.padding(top = innerPadding.calculateTopPadding()),
            onHomeScreenCardClick = onCardClick,
            onLongClick = onLongClick,
            allFilms = allFilms,
        )
    }
}


@OptIn(ExperimentalFoundationApi::class)
@Composable
fun HomeScreenColumn(
    onHomeScreenCardClick: (Film) -> Unit,
    onLongClick: (Film) -> Unit,
    allFilms: List<Film>,
    modifier: Modifier = Modifier,
) {
    val haptics = LocalHapticFeedback.current
    LazyColumn(
        content = {
            items(allFilms) {
                FilmCardItem(
                    filmName = it.nameRu,
                    year = it.year,
                    imageUrl = it.posterUrlPreview,
                    genres = it.genres,
                    modifier = Modifier
                        .combinedClickable(
                            onClick = { onHomeScreenCardClick(it) },
                            onLongClick = {
                                haptics.performHapticFeedback(HapticFeedbackType.LongPress)
                                onLongClick(it)
                            },
                            onLongClickLabel = stringResource(R.string.error_message)
                        )

                )
            }
            item {
                Spacer(modifier = modifier.height(20.dp))
            }
        },
        horizontalAlignment = Alignment.CenterHorizontally,
        contentPadding = PaddingValues(
            horizontal = dimensionResource(id = R.dimen.large),
        ), //contentPadding
        verticalArrangement = Arrangement.spacedBy(dimensionResource(id = R.dimen.large)),
        modifier = modifier.background(MaterialTheme.colorScheme.background)
    )
}

@Composable
fun FilmCardItem(
    filmName: String,
    imageUrl: String,
    year: String,
    genres: List<Genre>,
    modifier: Modifier = Modifier,
) {
    Card(
        modifier = modifier
            .fillMaxWidth()
            .height(150.dp),
        //ToDo correct the shape of card
        shape = RoundedCornerShape(dimensionResource(id = R.dimen.large)),
        elevation = CardDefaults.cardElevation(dimensionResource(id = R.dimen.large)),
        colors = CardDefaults.cardColors(
            containerColor = MaterialTheme.colorScheme.background,
        ),
    ) {
        Row(modifier = Modifier.padding(dimensionResource(id = R.dimen.medium)))
        {
            AsyncImage(
                model = ImageRequest.Builder(context = LocalContext.current)
                    .data(imageUrl)
                    .crossfade(true)
                    .build(),
                contentDescription = filmName,
                contentScale = ContentScale.Crop,
                modifier = Modifier
                    .aspectRatio(2f / 3f)
                    .clip(RoundedCornerShape(dimensionResource(id = R.dimen.medium)))
            )
            Column(
                modifier = Modifier
                    .padding(start = dimensionResource(id = R.dimen.large))
                    .fillMaxSize(),
                verticalArrangement = Arrangement.Center
            ) {
                Text(
                    text = filmName,
                    color = MaterialTheme.colorScheme.onPrimaryContainer,
                    style = MaterialTheme.typography.headlineSmall,
                    fontWeight = FontWeight.Bold,
                    overflow = TextOverflow.Ellipsis,
                    maxLines = 1
                )
                Spacer(modifier = Modifier.height(16.dp))
                Text(
                    text = "${genres.first().genre.firstLetterToUpperCase()} ($year)",
                    color = MaterialTheme.colorScheme.secondary,
                    style = MaterialTheme.typography.bodyLarge,
                )
            }
        }
    }
}


@Preview(showBackground = true)
@Composable
fun FilmCardPreview() {
    FilmCardItem(
        filmName = "Matrix",
        year = "1982",
        imageUrl = "http://books.google.com/books/content?id=iTsfAQAAMAAJ&printsec=frontcover&img=1&zoom=1&source=gbs_api",
        genres = listOf(Genre("Popa"))
    )
}


/*
@Preview(showBackground = true)
@Composable
fun HomePreview() {
    HomeScreen(allFilms = emptyList(), onCardClick = {}, onLongClick = {})
}
*/

