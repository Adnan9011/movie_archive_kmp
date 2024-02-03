package com.moviearchive.feature.presentation.detail

import androidx.compose.material3.Scaffold
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import com.moviearchive.feature.presentation.detail.widget.DetailWidget
import com.moviearchive.ui.widget.AppBarDetail

@Composable
fun DetailScreen(
    viewModel: DetailViewModel,
    movieId: String,
    onBackClicked: () -> Unit
) {
    val movieTitle by viewModel.movieTitle.collectAsState()

    Scaffold(
        topBar = {
            AppBarDetail(
                title = movieTitle,
                onBackClicked = onBackClicked
            )
        }
    ) {
        DetailContent(
            movieId = movieId,
            viewModel = viewModel
        )
    }
}

@Composable
fun DetailContent(
    movieId: String,
    viewModel: DetailViewModel
) {

    LaunchedEffect(
        key1 = movieId,
    ) {
        viewModel.getMovie(movieId)
    }

    DetailWidget(
        viewModel = viewModel
    )
}