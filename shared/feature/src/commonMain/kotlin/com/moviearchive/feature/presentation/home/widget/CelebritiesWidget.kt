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
import com.moviearchive.feature.model.CelebritiesUiModel
import com.moviearchive.feature.presentation.home.HomeViewModel
import com.moviearchive.feature.presentation.home.items.ShowCelebrities
import com.moviearchive.feature.presentation.home.items.ShowShimmerCelebrities
import com.moviearchive.feature.util.ShimmerMovieItemCount
import com.moviearchive.ui.theme.HighPadding
import com.moviearchive.ui.theme.HomeTextTitleStyle
import com.moviearchive.ui.theme.NormalPadding
import com.moviearchive.ui.widget.ShowSnackbar
import kotlinx.collections.immutable.PersistentList

@Composable
fun CelebritiesWidget(
    viewModel: HomeViewModel,
    snackbarHost: SnackbarHostState,
    onShowCelebrity: (celebrity: CelebritiesUiModel) -> Unit
) {
    val uiPopularCelebrities by viewModel.uiPopularCelebrities.collectAsState()
    var isEnablePopularCelebritiesShimmer by remember { mutableStateOf(false) }

    Column(
        modifier = Modifier
            .fillMaxWidth()
            .wrapContentHeight()
    ) {
        Text(
            text = "Top Popular Celebrities",
            style = HomeTextTitleStyle,
            modifier = Modifier.padding(
                top = NormalPadding,
                start = HighPadding,
                bottom = NormalPadding
            )
        )

        when (uiPopularCelebrities) {
            Result.Loading -> {
                isEnablePopularCelebritiesShimmer = true
                ShowShimmerCelebrities(
                    isEnableShimmer = true,
                    count = ShimmerMovieItemCount
                )
            }

            is Result.Failure -> {
                ShowSnackbar(
                    snackbarHostState = snackbarHost,
                    scope = rememberCoroutineScope(),
                    message = (uiPopularCelebrities as Result.Failure).error.message
                )
            }

            is Result.Success -> {
                isEnablePopularCelebritiesShimmer = false
                val celebrities =
                    (uiPopularCelebrities as Result.Success<PersistentList<CelebritiesUiModel>>).value
                if (celebrities.isEmpty()) {
                    EmptyCelebrity()
                } else {
                    ShowCelebrities(
                        celebrities = celebrities,
                        onShowCelebrity = onShowCelebrity
                    )
                }
            }
        }
    }
}