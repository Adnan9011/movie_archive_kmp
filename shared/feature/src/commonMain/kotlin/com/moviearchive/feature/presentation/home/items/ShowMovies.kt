package com.moviearchive.feature.presentation.home.items

import androidx.compose.foundation.lazy.LazyRow
import androidx.compose.foundation.lazy.items
import androidx.compose.runtime.Composable
import com.moviearchive.feature.model.WeekTopUiModel
import kotlinx.collections.immutable.PersistentList

@Composable
fun ShowMovies(
    movies: PersistentList<WeekTopUiModel>,
    onShowDetail: (movieId: String) -> Unit
) {
    LazyRow {
        items(
            items = movies,
            key = { movie -> movie.id }
        ) { movie ->
            MovieItem(
                movie = movie,
                onShowDetail = onShowDetail
            )
        }
    }
}