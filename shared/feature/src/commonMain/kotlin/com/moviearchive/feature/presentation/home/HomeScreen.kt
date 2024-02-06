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
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.runtime.remember
import androidx.compose.ui.Modifier
import com.moviearchive.feature.model.CelebrityUiModel
import com.moviearchive.feature.presentation.home.widget.CelebritiesWidget
import com.moviearchive.feature.presentation.home.widget.TopBannerWidget
import com.moviearchive.feature.presentation.home.widget.TopTenWidget
import com.moviearchive.feature.util.HOME_MOVIE_TITLE
import com.moviearchive.ui.widget.AppBarHome

@Composable
fun HomeScreen(
    modifier: Modifier = Modifier,
    viewModel: HomeViewModel,
    onShowDetail: (movieId: String) -> Unit,
    onShowCelebrity: (celebrity: CelebrityUiModel) -> Unit
) {

    val snackbarHostState = remember { SnackbarHostState() }
    val favoriteStatus by viewModel.uiFavoriteStatus.collectAsState()


    Scaffold(
        topBar = {
            AppBarHome(
                title = HOME_MOVIE_TITLE,
                isLiked = favoriteStatus,
                onFavoriteClicked = { isFavorite ->
                    viewModel.updateFavoriteStatus(isFavorite = isFavorite)
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
    onShowCelebrity: (celebrity: CelebrityUiModel) -> Unit,
) {
    val scrollState = rememberScrollState()

    LaunchedEffect(
        key1 = true,
    ) {
        viewModel.getData()
        viewModel.getFavoriteStatus()
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