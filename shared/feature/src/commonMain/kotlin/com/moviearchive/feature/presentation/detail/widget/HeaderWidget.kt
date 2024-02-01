package com.moviearchive.feature.presentation.detail.widget

import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.aspectRatio
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Favorite
import androidx.compose.material.icons.filled.FavoriteBorder
import androidx.compose.material3.FloatingActionButton
import androidx.compose.material3.Icon
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import com.moviearchive.feature.model.MovieUiModel
import com.moviearchive.ui.theme.DetailImageAspectRatio
import com.moviearchive.ui.theme.NormalPadding
import com.moviearchive.ui.widget.AsyncImagePainter

@Composable
fun HeaderWidget(
    movie: MovieUiModel,
) {
    Box {
        Image(
            painter = AsyncImagePainter(movie.image),
            contentDescription = null,
            modifier = Modifier
                .aspectRatio(DetailImageAspectRatio),
            contentScale = ContentScale.Crop
        )
        FloatingActionButton(
            onClick = {
                //Todo: Implement it
            },
            modifier = Modifier
                .padding(NormalPadding)
                .align(Alignment.BottomEnd),
            shape = CircleShape
        ) {
            Icon(//Todo: Implement isLike
                if (false) Icons.Filled.Favorite else Icons.Filled.FavoriteBorder,
                tint = Color.Red,
                contentDescription = null
            )
        }
    }
}