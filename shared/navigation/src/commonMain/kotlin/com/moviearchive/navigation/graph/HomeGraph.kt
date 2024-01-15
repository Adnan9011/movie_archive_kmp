package com.moviearchive.navigation.graph

import androidx.compose.runtime.Composable
import cafe.adriel.voyager.core.registry.ScreenRegistry
import cafe.adriel.voyager.core.screen.Screen
import cafe.adriel.voyager.navigator.LocalNavigator
import cafe.adriel.voyager.navigator.currentOrThrow
import com.moviearchive.feature.presentation.home.HomeScreen
import com.moviearchive.navigation.SharedGraphScreen

class HomeGraph : Screen {

    @Composable
    override fun Content() {
        val navigator = LocalNavigator.currentOrThrow

        HomeScreen(
            onShowDetail = { movieId ->
                navigator.push(ScreenRegistry.get(SharedGraphScreen.Detail(movieId)))
            }
        )
    }
}