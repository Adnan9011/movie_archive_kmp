package com.moviearchive.feature.presentation.celebrity

import androidx.compose.material3.Scaffold
import androidx.compose.runtime.Composable
import com.moviearchive.feature.model.CelebrityUiModel
import com.moviearchive.feature.presentation.celebrity.widget.CelebrityWidget
import com.moviearchive.ui.widget.AppBarDetail

@Composable
fun CelebrityScreen(
    celebrity: CelebrityUiModel,
    viewModel: CelebrityViewModel,
    onBackClicked: () -> Unit
) {
    Scaffold(
        topBar = {
            AppBarDetail(
                title = celebrity.name,
                onBackClicked = onBackClicked
            )
        }
    ) {
        CelebrityContent(
            celebrity = celebrity,
            viewModel = viewModel
        )
    }
}

@Composable
fun CelebrityContent(
    celebrity: CelebrityUiModel,
    viewModel: CelebrityViewModel
) {
    CelebrityWidget(
        celebrity = celebrity,
        viewModel = viewModel
    )
}