package com.moviearchive.ui.widget

import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Favorite
import androidx.compose.material.icons.filled.FavoriteBorder
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.MaterialTheme
import androidx.compose.runtime.Composable

@Composable
fun LikeAction(
    isLiked: Boolean,
    onFavoriteClicked: (isLiked: Boolean) -> Unit
) {
    IconButton(onClick = {
        onFavoriteClicked(!isLiked)
    }) {
        Icon(
            imageVector = if (isLiked) Icons.Filled.Favorite else Icons.Filled.FavoriteBorder,
            contentDescription = null,
            tint = MaterialTheme.colorScheme.primaryContainer
        )
    }
}