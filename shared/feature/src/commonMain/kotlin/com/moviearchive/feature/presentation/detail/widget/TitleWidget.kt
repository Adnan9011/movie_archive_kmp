package com.moviearchive.feature.presentation.detail.widget

import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.requiredHeight
import androidx.compose.material3.Card
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.RectangleShape
import androidx.compose.ui.text.style.TextOverflow
import com.moviearchive.ui.theme.DetailCardHeight
import com.moviearchive.ui.theme.MovieDetailItemTextStyle
import com.moviearchive.ui.theme.NormalPadding
import com.moviearchive.ui.theme.SmallPadding

@Composable
fun TitleWidget(title: String) {
    Card(
        modifier = Modifier
            .fillMaxWidth()
            .requiredHeight(DetailCardHeight),
        shape = RectangleShape
    ) {
        Row(
            modifier = Modifier.fillMaxSize(),
            verticalAlignment = Alignment.CenterVertically
        ) {

            Text(
                text = title,
                overflow = TextOverflow.Ellipsis,
                maxLines = 1,
                style = MovieDetailItemTextStyle,
                color = Color.Blue,
                modifier = Modifier
                    .padding(start = NormalPadding, top = SmallPadding)
            )
        }
    }
}