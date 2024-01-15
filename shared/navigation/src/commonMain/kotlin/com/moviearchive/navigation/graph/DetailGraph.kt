package com.moviearchive.navigation.graph

import androidx.compose.runtime.Composable
import cafe.adriel.voyager.core.registry.rememberScreen
import cafe.adriel.voyager.core.screen.Screen
import cafe.adriel.voyager.navigator.LocalNavigator
import cafe.adriel.voyager.navigator.currentOrThrow
import com.moviearchive.feature.presentation.detail.DetailScreen
import com.moviearchive.navigation.SharedGraphScreen

class DetailGraph(val id: Int) : Screen {

    @Composable
    override fun Content() {
        val navigator = LocalNavigator.currentOrThrow
        val homeGraph = rememberScreen(SharedGraphScreen.Home)

        DetailScreen(
            movieId = id,
            onBackClicked = {
                navigator.push(homeGraph)
            }
        )
    }
}