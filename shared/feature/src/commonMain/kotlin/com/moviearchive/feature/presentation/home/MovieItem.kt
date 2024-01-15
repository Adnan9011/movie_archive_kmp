package com.moviearchive.feature.presentation.home

import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.layout.wrapContentSize
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Favorite
import androidx.compose.material.icons.filled.FavoriteBorder
import androidx.compose.material.icons.filled.MailOutline
import androidx.compose.material.icons.outlined.Favorite
import androidx.compose.material3.Icon
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.text.style.TextOverflow
import coil3.compose.LocalPlatformContext
import coil3.compose.rememberAsyncImagePainter
import coil3.request.ImageRequest
import com.moviearchive.feature.model.MovieUiModel
import com.moviearchive.ui.theme.DetailIcon
import com.moviearchive.ui.theme.GradientBlack
import com.moviearchive.ui.theme.GradientDarkGray
import com.moviearchive.ui.theme.GradientWhite
import com.moviearchive.ui.theme.HighPadding
import com.moviearchive.ui.theme.MovieDetailItemTextStyle
import com.moviearchive.ui.theme.MovieItemHeight
import com.moviearchive.ui.theme.MovieItemRound
import com.moviearchive.ui.theme.MovieItemTitleStyle
import com.moviearchive.ui.theme.MovieItemWidth
import com.moviearchive.ui.theme.NormalPadding
import com.moviearchive.ui.theme.SmallEvelation
import com.moviearchive.ui.theme.SmallPadding
import com.moviearchive.ui.widget.VerticalGradiant

@Composable
fun MovieItem(
    movie: MovieUiModel,
    onShowDetail: (movieId: Int) -> Unit
) {
    Surface(
        modifier = Modifier
            .background(Color.White)
            .padding(all = SmallPadding)
            .wrapContentSize()
            .clip(RoundedCornerShape(size = MovieItemRound)),
        shadowElevation = SmallEvelation,
        tonalElevation = SmallEvelation
    ) {
        Column(
            modifier = Modifier
                .wrapContentSize()
                .clickable { onShowDetail(movie.id) }
        ) {
            Image(
                modifier = Modifier
                    .size(width = MovieItemWidth, height = MovieItemHeight),
                painter = rememberAsyncImagePainter(
                    model = ImageRequest.Builder(LocalPlatformContext.current).data(movie.imageUrl)
                        .build()
                ),
                contentScale = ContentScale.Crop,
                contentDescription = null
            )
            VerticalGradiant(
                modifier = Modifier
                    .width(MovieItemWidth),
                listColors = listOf(
                    GradientWhite,
                    GradientDarkGray,
                    GradientBlack
                )
            ) {
                Text(
                    text = movie.title,
                    style = MovieItemTitleStyle,
                    modifier = Modifier
                        .fillMaxWidth()
                        .padding(
                            top = SmallPadding,
                            bottom = SmallPadding,
                            start = NormalPadding,
                            end = NormalPadding
                        ),
                    overflow = TextOverflow.Ellipsis,
                    maxLines = 1
                )
            }

            Icon(
                modifier = Modifier
                    .padding(start = NormalPadding, top = SmallPadding)
                    .size(DetailIcon),
                imageVector = Icons.Filled.MailOutline,
                tint = Color.Blue,
                contentDescription = ""
            )
            Text(
                text = movie.numComments.toString(),
                overflow = TextOverflow.Ellipsis,
                maxLines = 1,
                style = MovieDetailItemTextStyle,
                color = Color.Blue,
                modifier = Modifier
                    .padding(start = NormalPadding, top = SmallPadding)
            )
            Icon(
                imageVector = Icons.Outlined.Favorite,
                contentDescription = "",
                tint = Color.Red,
                modifier = Modifier
                    .padding(start = HighPadding, top = SmallPadding, bottom = SmallPadding)
                    .size(DetailIcon)
            )
            Text(
                text = movie.numLikes.toString(),
                overflow = TextOverflow.Ellipsis,
                maxLines = 1,
                style = MovieDetailItemTextStyle,
                color = Color.Red,
                modifier = Modifier
                    .padding(start = NormalPadding, top = SmallPadding, bottom = SmallPadding)
            )
            Icon(
                if (movie.isLiked) Icons.Filled.Favorite else Icons.Filled.FavoriteBorder,
                tint = Color.Red,
                modifier = Modifier
                    .padding(NormalPadding),
                contentDescription = null
            )

        }
    }
}

