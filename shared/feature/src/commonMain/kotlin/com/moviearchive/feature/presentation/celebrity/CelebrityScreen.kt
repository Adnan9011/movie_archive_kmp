package com.moviearchive.feature.presentation.celebrity

import androidx.compose.material3.Scaffold
import androidx.compose.runtime.Composable
import com.moviearchive.feature.model.CelebritiesUiModel
import com.moviearchive.feature.presentation.celebrity.widget.CelebrityWidget
import com.moviearchive.ui.widget.AppBarDetail

@Composable
fun CelebrityScreen(
    celebrity: CelebritiesUiModel,
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
        CelebrityContent(celebrity)
    }
}

@Composable
fun CelebrityContent(
    celebrity: CelebritiesUiModel
) {
    CelebrityWidget(celebrity = celebrity)
}