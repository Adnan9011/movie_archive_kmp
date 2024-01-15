package com.moviearchive.feature.android.presentation.home

import androidx.compose.runtime.Composable
import androidx.compose.ui.tooling.preview.Preview
import com.moviearchive.feature.presentation.home.EmptyMovies
import com.moviearchive.feature.presentation.home.ShowShimmerMovies
import com.moviearchive.feature.presentation.home.TopBanner
import com.moviearchive.feature.util.ShimmerMovieItemCount

@Preview
@Composable
private fun TopBannerPreview() {
    TopBanner()
}

@Preview
@Composable
private fun EmptyMoviesPreview() {
    EmptyMovies()
}

@Preview
@Composable
private fun ShowShimmerMoviesPreview() {
    ShowShimmerMovies(
        isEnableShimmer = true,
        count = ShimmerMovieItemCount
    )
}