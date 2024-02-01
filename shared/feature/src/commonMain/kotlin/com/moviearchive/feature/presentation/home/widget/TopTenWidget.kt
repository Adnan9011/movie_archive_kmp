package com.moviearchive.feature.presentation.home.widget

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.wrapContentHeight
import androidx.compose.material3.SnackbarHostState
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import com.moviearchive.core.Result
import com.moviearchive.feature.model.WeekTopUiModel
import com.moviearchive.feature.presentation.home.HomeViewModel
import com.moviearchive.feature.presentation.home.items.ShowMovies
import com.moviearchive.feature.presentation.home.items.ShowShimmerMovies
import com.moviearchive.feature.util.HOME_TOP_TEN_WEEK
import com.moviearchive.feature.util.ShimmerMovieItemCount
import com.moviearchive.ui.theme.HighPadding
import com.moviearchive.ui.theme.HomeTextTitleStyle
import com.moviearchive.ui.theme.NormalPadding
import com.moviearchive.ui.widget.ShowSnackbar
import kotlinx.collections.immutable.PersistentList

@Composable
fun TopTenWidget(
    viewModel: HomeViewModel,
    snackbarHost: SnackbarHostState,
    onShowDetail: (movieId: String) -> Unit
) {
    val uiWeekTopTen by viewModel.uiWeekTopTen.collectAsState()
    var isEnableWeekTopTenShimmer by remember { mutableStateOf(false) }

    Column(
        modifier = Modifier
            .fillMaxWidth()
            .wrapContentHeight()
    ) {
        Text(
            text = HOME_TOP_TEN_WEEK,
            style = HomeTextTitleStyle,
            modifier = Modifier.padding(
                top = NormalPadding,
                start = HighPadding,
                bottom = NormalPadding
            )
        )

        when (uiWeekTopTen) {
            Result.Loading -> {
                isEnableWeekTopTenShimmer = true
                ShowShimmerMovies(
                    isEnableShimmer = true,
                    count = ShimmerMovieItemCount
                )
            }

            is Result.Failure -> {
                ErrorWidget()
                ShowSnackbar(
                    snackbarHostState = snackbarHost,
                    scope = rememberCoroutineScope(),
                    message = (uiWeekTopTen as Result.Failure).error.message
                )
            }

            is Result.Success -> {
                isEnableWeekTopTenShimmer = false
                val movies = (uiWeekTopTen as Result.Success<PersistentList<WeekTopUiModel>>).value
                if (movies.isEmpty()) {
                    EmptyTopTenWidget()
                } else {
                    ShowMovies(
                        movies = movies,
                        onShowDetail = onShowDetail
                    )
                }
            }
        }
    }
}