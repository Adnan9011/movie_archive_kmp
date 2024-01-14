package com.moviearchive.ui.widget

import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Favorite
import androidx.compose.material.icons.filled.FavoriteBorder
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.MaterialTheme
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue

@Composable
fun LikeAction(
    onFavoriteClicked: (isLiked: Boolean) -> Unit
) {
    var isLikeClicked by remember { mutableStateOf(false) }
    IconButton(onClick = {
        isLikeClicked = !isLikeClicked
        onFavoriteClicked(isLikeClicked)
    }) {
        Icon(
            imageVector = if (isLikeClicked) Icons.Filled.Favorite else Icons.Filled.FavoriteBorder,
            contentDescription = null,
            tint = MaterialTheme.colorScheme.primaryContainer
        )
    }
}