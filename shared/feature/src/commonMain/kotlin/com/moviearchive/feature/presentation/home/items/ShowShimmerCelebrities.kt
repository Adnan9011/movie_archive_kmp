package com.moviearchive.feature.presentation.home.items

import androidx.compose.foundation.lazy.LazyRow
import androidx.compose.runtime.Composable

@Composable
fun ShowShimmerCelebrities(
    isEnableShimmer: Boolean,
    count: Int
) {
    LazyRow() {
        items(count = count) {
            ShimmerMovieItem(isEnableShimmer = isEnableShimmer)
        }
    }
}