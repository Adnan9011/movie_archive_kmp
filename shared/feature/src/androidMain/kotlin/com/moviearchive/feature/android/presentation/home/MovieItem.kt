package com.moviearchive.feature.android.presentation.home

import androidx.compose.runtime.Composable
import androidx.compose.ui.tooling.preview.Preview
import com.moviearchive.feature.model.MovieUiModel
import com.moviearchive.feature.presentation.home.MovieItem

@Preview
@Composable
private fun MovieItemPreview() {
    MovieItem(
        movie = MovieUiModel(
            id = 0,
            imageUrl = "",
            title = "Title",
            numComments = 0,
            numLikes = 0,
            isLiked = false
        ),
        onShowDetail = {}
    )
}