package com.moviearchive.feature.presentation.home.widget

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Warning
import androidx.compose.material3.Divider
import androidx.compose.material3.Icon
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import com.moviearchive.feature.util.TRY_AGAIN
import com.moviearchive.ui.theme.EmptyMovieSize
import com.moviearchive.ui.theme.EmptyTextStyle
import com.moviearchive.ui.theme.MovieItemHeight
import com.moviearchive.ui.theme.NormalPadding
import com.moviearchive.ui.widget.TryAgainButton

@Composable
fun ErrorWidget(
    onTryAgain: () -> Unit
) {
    Column(
        modifier = Modifier
            .padding(NormalPadding)
            .fillMaxWidth()
            .height(MovieItemHeight),
        verticalArrangement = Arrangement.Center,
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        Icon(
            modifier = Modifier
                .size(EmptyMovieSize),
            imageVector = Icons.Filled.Warning,
            tint = Color.DarkGray,
            contentDescription = null
        )
        Divider(
            Modifier
                .height(NormalPadding),
            color = Color.Transparent
        )
        TryAgainButton(
            onClick = { onTryAgain() },
            text = TRY_AGAIN,
            textStyle = EmptyTextStyle
        )
    }
}
