package com.moviearchive.feature.presentation.celebrity.widget

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
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import com.moviearchive.feature.model.CelebrityUiModel
import com.moviearchive.feature.presentation.celebrity.CelebrityViewModel
import com.moviearchive.ui.theme.DetailImageAspectRatio
import com.moviearchive.ui.theme.NormalPadding
import com.moviearchive.ui.widget.AsyncImagePainter

@Composable
fun HeaderWidget(
    celebrity: CelebrityUiModel,
    viewModel: CelebrityViewModel
) {
    Box {
        var isFavorite by remember { mutableStateOf(false) }

        Image(
            painter = AsyncImagePainter(celebrity.image),
            contentDescription = null,
            modifier = Modifier
                .aspectRatio(DetailImageAspectRatio),
            contentScale = ContentScale.Crop
        )
        FloatingActionButton(
            onClick = {
                isFavorite = !isFavorite
                viewModel.updateCelebrity(
                    isFavorite = isFavorite,
                    celebrity = celebrity
                )
            },
            modifier = Modifier
                .padding(NormalPadding)
                .align(Alignment.BottomEnd),
            shape = CircleShape
        ) {
            Icon(
                if (isFavorite) Icons.Filled.Favorite else Icons.Filled.FavoriteBorder,
                tint = Color.Red,
                contentDescription = null
            )
        }
    }
}