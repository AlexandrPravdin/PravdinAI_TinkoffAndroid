package com.example.pravdinai_tinkoffandroid.ui.navigation

import android.os.Build
import androidx.annotation.RequiresExtension
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.lifecycle.viewmodel.compose.viewModel
import androidx.navigation.NavHostController
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
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
    navController: NavHostController,
    modifier: Modifier = Modifier
) {
    val viewModel: FilmViewModel = viewModel(factory = AppViewModelProvider.Factory)

    val uiState: HomeUiState = viewModel.uiState
    NavHost(
        navController = navController,
        startDestination = "HomeScreen",
        modifier = modifier
    ) {
        composable("HomeScreen") {
            when (uiState) {
                is HomeUiState.Success -> {
                    HomeScreen(
                        allFilms = uiState.response,
                        onCardClick = { film: Film ->
                            viewModel.updateDetailsScreenStates(film)
                            navController.navigate("DetailsScreen")
                        }
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
        composable("DetailsScreen") {
            when (uiState) {
                is HomeUiState.Success -> {
                    DetailsScreen(
                        film = uiState.currentSelectedFilm,
                        onBackButtonPressed = { navController.popBackStack() }
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
}