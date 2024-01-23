package com.moviearchive.feature.android.presentation.detail

import androidx.compose.runtime.Composable
import androidx.compose.ui.tooling.preview.Preview
import com.moviearchive.feature.presentation.detail.DetailScreen

@Preview
@Composable
private fun DetailScreenPreview() {
    DetailScreen(
        movieId = 0,
        onBackClicked = {}
    )
}

@Preview
@Composable
private fun HeaderPreview() {//TODO: Refactor
//    Header(
//        movie = MovieUiModel(
//            id = 0,
//            imageUrl = "",
//            title = "Title",
//            numComments = 0,
//            numLikes = 0,
//            isLiked = false
//        ),
//        viewModel = koinInject()
//    )
}