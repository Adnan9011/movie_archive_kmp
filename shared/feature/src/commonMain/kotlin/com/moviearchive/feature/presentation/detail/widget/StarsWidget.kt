package com.moviearchive.feature.presentation.detail.widget

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import com.moviearchive.ui.theme.HighPadding
import com.moviearchive.ui.theme.MovieDetailTextStyle
import com.moviearchive.ui.theme.NormalPadding
import com.moviearchive.ui.theme.SmallPadding
import kotlinx.collections.immutable.PersistentList

@Composable
fun StarsWidget(
    stars: PersistentList<String>
) {
    Column {
        Text(
            text = "Stars :",
            style = MovieDetailTextStyle,
            modifier = Modifier.padding(NormalPadding)
        )
        stars.forEach { star ->
            Text(
                text = star,
                style = MovieDetailTextStyle,
                modifier = Modifier.padding(
                    start = HighPadding,
                    top = SmallPadding,
                    bottom = SmallPadding
                )
            )
        }
    }
}