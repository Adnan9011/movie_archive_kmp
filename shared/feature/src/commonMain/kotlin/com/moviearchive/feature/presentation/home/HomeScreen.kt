package com.moviearchive.feature.presentation.home

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.verticalScroll
import androidx.compose.material3.Scaffold
import androidx.compose.material3.SnackbarHost
import androidx.compose.material3.SnackbarHostState
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.remember
import androidx.compose.ui.Modifier
import com.moviearchive.feature.presentation.home.widget.CelebritiesWidget
import com.moviearchive.feature.presentation.home.widget.TopBannerWidget
import com.moviearchive.feature.presentation.home.widget.TopTenWidget
import com.moviearchive.ui.widget.AppBarHome
import org.koin.compose.koinInject

@Composable
fun HomeScreen(
    modifier: Modifier = Modifier,
    viewModel: HomeViewModel = koinInject(),
    onShowDetail: (movieId: String) -> Unit,
    onShowCelebrity: (celebrityId: String) -> Unit
) {

    val snackbarHostState = remember { SnackbarHostState() }

    Scaffold(
        topBar = {
            AppBarHome(
                title = "Movies",
                onFavoriteClicked = { isFavorite ->
                    //Todo: :) you know what to do, Change SQLDelight table and so on
                }
            )
        },
        snackbarHost = { SnackbarHost(snackbarHostState) },
    ) { paddingValues ->
        HomeContent(
            viewModel = viewModel,
            modifier = modifier.padding(paddingValues),
            snackbarHost = snackbarHostState,
            onShowDetail = onShowDetail,
            onShowCelebrity = onShowCelebrity
        )
    }
}

@Composable
fun HomeContent(
    viewModel: HomeViewModel,
    modifier: Modifier,
    snackbarHost: SnackbarHostState,
    onShowDetail: (movieId: String) -> Unit,
    onShowCelebrity: (celebrityId: String) -> Unit,
) {
    val scrollState = rememberScrollState()

    LaunchedEffect(
        key1 = true,
    ) {
        viewModel.getWeekTopTen()
        viewModel.getCelebrities()
    }

    Column(
        modifier = modifier
            .verticalScroll(scrollState)
    ) {

        TopBannerWidget()

        TopTenWidget(
            viewModel = viewModel,
            snackbarHost = snackbarHost,
            onShowDetail = onShowDetail
        )

        CelebritiesWidget(
            viewModel = viewModel,
            snackbarHost = snackbarHost,
            onShowCelebrity = onShowCelebrity
        )
    }
}