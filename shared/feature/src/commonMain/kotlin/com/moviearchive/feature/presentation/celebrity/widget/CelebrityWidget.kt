package com.moviearchive.feature.presentation.celebrity.widget

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import com.moviearchive.feature.model.CelebritiesUiModel
import com.moviearchive.ui.theme.MovieDetailTextStyle
import com.moviearchive.ui.theme.NormalPadding

@Composable
fun CelebrityWidget(celebrity: CelebritiesUiModel) {
    Column {
        HeaderWidget(
            celebrity = celebrity
        )

        Text(
            text = celebrity.name,
            style = MovieDetailTextStyle,
            modifier = Modifier
                .padding(NormalPadding)
        )
    }
}