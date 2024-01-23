package com.moviearchive.feature.android.presentation.home

import androidx.compose.runtime.Composable
import androidx.compose.ui.tooling.preview.Preview
import com.moviearchive.feature.presentation.home.items.ShowShimmerMovies
import com.moviearchive.feature.presentation.home.widget.EmptyTopTenWidget
import com.moviearchive.feature.presentation.home.widget.TopBannerWidget
import com.moviearchive.feature.util.ShimmerMovieItemCount

@Preview
@Composable
private fun TopBannerWidgetPreview() {
    TopBannerWidget()
}

@Preview
@Composable
private fun EmptyTopTenWidgetPreview() {
    EmptyTopTenWidget()
}

@Preview
@Composable
private fun ShowShimmerMoviesPreview() {
    ShowShimmerMovies(
        isEnableShimmer = true,
        count = ShimmerMovieItemCount
    )
}