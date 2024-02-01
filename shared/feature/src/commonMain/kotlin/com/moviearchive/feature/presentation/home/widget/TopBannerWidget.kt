package com.moviearchive.feature.presentation.home.widget

import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.layout.ContentScale
import com.moviearchive.feature.util.BANNER_IMAGE_URL
import com.moviearchive.ui.theme.HomeBannerHeight
import com.moviearchive.ui.theme.MovieItemRound
import com.moviearchive.ui.theme.NormalPadding
import com.moviearchive.ui.theme.SmallPadding
import com.moviearchive.ui.widget.AsyncImagePainter

@Composable
fun TopBannerWidget() {

    Box(
        modifier = Modifier
            .padding(
                start = SmallPadding,
                end = SmallPadding,
                top = NormalPadding,
                bottom = SmallPadding,
            )
            .fillMaxWidth()
    ) {
        Image(
            modifier = Modifier
                .fillMaxWidth()
                .height(HomeBannerHeight)
                .clip(RoundedCornerShape(size = MovieItemRound)),
            painter = AsyncImagePainter(BANNER_IMAGE_URL),
            contentScale = ContentScale.Crop,
            contentDescription = null
        )
    }
}