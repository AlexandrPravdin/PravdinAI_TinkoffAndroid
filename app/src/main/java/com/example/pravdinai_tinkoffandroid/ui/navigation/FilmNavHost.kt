package com.example.pravdinai_tinkoffandroid.ui.navigation

import android.os.Build
import androidx.annotation.RequiresExtension
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.width
import androidx.compose.material3.BottomAppBar
import androidx.compose.material3.Button
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.lifecycle.viewmodel.compose.viewModel
import androidx.navigation.NavHostController
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import com.example.pravdinai_tinkoffandroid.R
import com.example.pravdinai_tinkoffandroid.data.local.mapToFilms
import com.example.pravdinai_tinkoffandroid.data.network.Film
import com.example.pravdinai_tinkoffandroid.ui.AppViewModelProvider
import com.example.pravdinai_tinkoffandroid.ui.screens.DetailsScreen
import com.example.pravdinai_tinkoffandroid.ui.screens.ErrorScreen
import com.example.pravdinai_tinkoffandroid.ui.screens.FilmViewModel
import com.example.pravdinai_tinkoffandroid.ui.screens.HomeScreen
import com.example.pravdinai_tinkoffandroid.ui.screens.HomeUiState
import com.example.pravdinai_tinkoffandroid.ui.screens.LoadingScreen

@RequiresExtension(extension = Build.VERSION_CODES.S, version = 7)
@Composable
fun AppNavHost(
    navController: NavHostController, modifier: Modifier = Modifier
) {
    val viewModel: FilmViewModel = viewModel(factory = AppViewModelProvider.Factory)

    val uiState: HomeUiState = viewModel.uiState
    NavHost(
        navController = navController, startDestination = "HomeScreen", modifier = modifier
    ) {

        composable("HomeScreen") {
            Scaffold(bottomBar = {
                BottomAppBar(
                    containerColor = Color.Transparent, modifier = Modifier.height(100.dp)
                ) {
                    Row(
                        modifier = modifier.fillMaxWidth(),
                        horizontalArrangement = Arrangement.SpaceAround,
                        verticalAlignment = Alignment.CenterVertically,
                    ) {
                        BottomAppBarButton(
                            modifier = modifier,
                            onButtonClick = { navController.navigate("HomeScreen") },
                            title = stringResource(R.string.popular)
                        )
                        BottomAppBarButton(
                            modifier = modifier,
                            onButtonClick = { navController.navigate("FavouriteScreen") },
                            title = stringResource(R.string.pinned)
                        )
                    }
                }
            }) { innerPadding ->
                val padding = innerPadding
                when (uiState) {
                    is HomeUiState.Success -> {
                        HomeScreen(
                            allFilms = uiState.response,
                            onCardClick = { film: Film ->
                                viewModel.updateRemoteDetailsScreenStates(film)
                                navController.navigate("DetailsScreen")
                            },
                            onLongClick = { film: Film ->
                                viewModel.insertFilmToFavourite(film)
                            },
                            title = stringResource(R.string.popular)
                        )
                    }
                    is HomeUiState.Error -> {
                        ErrorScreen({ viewModel.initializeUiState() })
                    }
                    is HomeUiState.Loading -> {
                        LoadingScreen()
                    }
                }
            }
        }
        composable("FavouriteScreen") {
            Scaffold(bottomBar = {
                BottomAppBar(
                    containerColor = Color.Transparent, modifier = Modifier.height(100.dp)
                ) {
                    Row(
                        modifier = modifier.fillMaxWidth(),
                        horizontalArrangement = Arrangement.SpaceAround,
                        verticalAlignment = Alignment.CenterVertically,
                    ) {
                        BottomAppBarButton(
                            modifier = modifier,
                            onButtonClick = { navController.navigate("HomeScreen") },
                            title = stringResource(R.string.popular)
                        )
                        BottomAppBarButton(
                            modifier = modifier,
                            onButtonClick = { navController.navigate("FavouriteScreen") },
                            title = stringResource(R.string.pinned)
                        )
                    }
                }
            }) { innerPadding ->
                val padding = innerPadding
                HomeScreen(
                    allFilms = viewModel.getAllFavouriteFilms()
                        .collectAsState(initial = listOf()).value.mapToFilms(),
                    onCardClick = {},
                    onLongClick = {},
                    title = stringResource(R.string.pinned)
                )
            }
        }


        composable("DetailsScreen") {
            when (uiState) {
                is HomeUiState.Success -> {
                    DetailsScreen(film = uiState.currentSelectedFilm,
                        onBackButtonPressed = { navController.popBackStack() })
                }

                is HomeUiState.Error -> {
                    ErrorScreen({ viewModel.initializeUiState() })
                }

                is HomeUiState.Loading -> {
                    LoadingScreen()
                }
            }
        }
    }
}

@Composable
private fun BottomAppBarButton(
    title: String,
    onButtonClick: () -> Unit,
    modifier: Modifier,
) {
    Button(
        modifier = modifier
            .height(60.dp)
            .width(160.dp), onClick = onButtonClick
    ) {
        Text(
            text = title,
            style = MaterialTheme.typography.headlineSmall,
            fontWeight = FontWeight.Bold,
            fontSize = 18.sp
        )
    }
}