package com.moviearchive.feature.android.presentation.detail

import androidx.compose.runtime.Composable
import androidx.compose.ui.tooling.preview.Preview
import com.moviearchive.feature.presentation.detail.DetailScreen
import com.moviearchive.feature.presentation.detail.widget.DetailWidget
import org.koin.compose.koinInject

@Preview
@Composable
private fun DetailScreenPreview() {
    DetailScreen(
        movieId = "",
        onBackClicked = {}
    )
}

@Preview
@Composable
private fun DetailWidgetPreview() {
    DetailWidget(
        viewModel = koinInject()
    )
}