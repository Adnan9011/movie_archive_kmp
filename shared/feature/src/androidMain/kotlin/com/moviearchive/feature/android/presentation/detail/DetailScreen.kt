package com.moviearchive.feature.android.presentation.detail

import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import com.moviearchive.feature.presentation.detail.DetailScreen

@Preview
@Composable
private fun DetailScreenPreview() {
    DetailScreen(
        modifier = Modifier,
        movieId = 0,
        onBackClicked = {}
    )
}