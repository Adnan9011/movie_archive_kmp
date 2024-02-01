package com.moviearchive.feature.android.presentation.home

import androidx.compose.runtime.Composable
import androidx.compose.ui.tooling.preview.Preview
import com.moviearchive.feature.model.WeekTopUiModel
import com.moviearchive.feature.presentation.home.items.MovieItem

@Preview
@Composable
private fun MovieItemPreview() {//TODO: Refactor
    MovieItem(
        movie = WeekTopUiModel(
            id = "",
            title = "Title",
            image = "",
            rate = 7.8,
            duration = 3000
        ),
        onShowDetail = {}
    )
}