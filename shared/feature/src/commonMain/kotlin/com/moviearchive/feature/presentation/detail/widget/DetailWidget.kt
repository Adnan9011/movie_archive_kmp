package com.moviearchive.feature.presentation.detail.widget

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.requiredHeight
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.ui.Modifier
import com.moviearchive.core.Result
import com.moviearchive.feature.model.MovieUiModel
import com.moviearchive.feature.presentation.detail.DetailViewModel
import com.moviearchive.ui.theme.SmallPadding

@Composable
fun DetailWidget(
    viewModel: DetailViewModel
) {
    val uiState by viewModel.uiState.collectAsState()

    when (uiState) {
        Result.Loading -> {
            //Todo: Implement Shimmer
        }

        is Result.Failure -> {
            //Todo: Implement Error Handling
        }

        is Result.Success -> {
            val movie = (uiState as Result.Success<MovieUiModel>).value

            Column {
                HeaderWidget(
                    viewModel = viewModel,
                    movie = movie
                )
                TitleWidget(title = movie.title)
                Spacer(modifier = Modifier.requiredHeight(SmallPadding))
                StarsWidget(movie.getStars())
            }
        }
    }
}