package com.moviearchive.feature.presentation.home.items

import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.layout.wrapContentSize
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.text.style.TextOverflow
import com.moviearchive.feature.model.CelebrityUiModel
import com.moviearchive.ui.theme.Colors.GradientBlack
import com.moviearchive.ui.theme.Colors.GradientDarkGray
import com.moviearchive.ui.theme.Colors.GradientWhite
import com.moviearchive.ui.theme.MovieItemHeight
import com.moviearchive.ui.theme.MovieItemRound
import com.moviearchive.ui.theme.MovieItemTitleStyle
import com.moviearchive.ui.theme.MovieItemWidth
import com.moviearchive.ui.theme.NormalPadding
import com.moviearchive.ui.theme.SmallEvelation
import com.moviearchive.ui.theme.SmallPadding
import com.moviearchive.ui.widget.AsyncImagePainter
import com.moviearchive.ui.widget.VerticalGradient

@Composable
fun CelebrityItem(
    celebrity: CelebrityUiModel,
    onShowCelebrity: (celebrity: CelebrityUiModel) -> Unit
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
                .clickable { onShowCelebrity(celebrity) }
        ) {
            Box(
                modifier = Modifier
                    .size(width = MovieItemWidth, height = MovieItemHeight)
            ) {

                Image(
                    modifier = Modifier
                        .size(width = MovieItemWidth, height = MovieItemHeight),
                    painter = AsyncImagePainter(celebrity.image),
                    contentScale = ContentScale.Crop,
                    contentDescription = null
                )
                VerticalGradient(
                    modifier = Modifier
                        .width(MovieItemWidth)
                        .align(Alignment.BottomStart),
                    listColors = listOf(
                        GradientWhite,
                        GradientDarkGray,
                        GradientBlack
                    )
                ) {
                    Text(
                        text = celebrity.name,
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
            }
        }
    }
}

