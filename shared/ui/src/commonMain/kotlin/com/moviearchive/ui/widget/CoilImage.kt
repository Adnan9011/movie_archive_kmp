package com.moviearchive.ui.widget

import androidx.compose.runtime.Composable
import coil3.compose.LocalPlatformContext
import coil3.compose.rememberAsyncImagePainter
import coil3.request.ImageRequest

@Composable
fun AsyncImagePainter(
    imageUrl: String
) = rememberAsyncImagePainter(
    model = ImageRequest
        .Builder(LocalPlatformContext.current)
        .data(imageUrl)
        .build()
)