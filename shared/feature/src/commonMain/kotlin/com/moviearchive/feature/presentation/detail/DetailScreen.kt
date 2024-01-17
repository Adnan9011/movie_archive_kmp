package com.moviearchive.feature.presentation.detail

import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.aspectRatio
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.requiredHeight
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Favorite
import androidx.compose.material.icons.filled.FavoriteBorder
import androidx.compose.material.icons.filled.MailOutline
import androidx.compose.material.icons.outlined.Favorite
import androidx.compose.material3.Card
import androidx.compose.material3.FloatingActionButton
import androidx.compose.material3.Icon
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.RectangleShape
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.text.style.TextOverflow
import com.moviearchive.core.Result
import com.moviearchive.feature.model.MovieUiModel
import com.moviearchive.ui.theme.DetailCardHeight
import com.moviearchive.ui.theme.DetailIcon
import com.moviearchive.ui.theme.DetailImageAspectRatio
import com.moviearchive.ui.theme.MovieDetailItemTextStyle
import com.moviearchive.ui.theme.MovieDetailTextStyle
import com.moviearchive.ui.theme.NormalPadding
import com.moviearchive.ui.theme.SmallPadding
import com.moviearchive.ui.widget.AppBarDetail
import com.moviearchive.ui.widget.AsyncImagePainter
import org.koin.compose.koinInject

@Composable
fun DetailScreen(
    viewModel: DetailViewModel = koinInject(),
    movieId: Int,
    onBackClicked: () -> Unit
) {
    val uiState by viewModel.uiState.collectAsState()

    LaunchedEffect(
        key1 = true,
    ) {
        viewModel.getMovie(movieId)
    }

    when (uiState) {
        Result.Loading -> {
            //Todo: Implement Shimmer
        }

        is Result.Failure -> {
            //Todo: Implement Error Handling
        }

        is Result.Success -> {
            val movie = (uiState as Result.Success<MovieUiModel>).value
            Scaffold(
                topBar = {
                    AppBarDetail(
                        title = movie.title,
                        onBackClicked = onBackClicked
                    )
                }
            ) {
                DetailContent(
                    movie = movie,
                    viewModel = viewModel
                )
            }
        }
    }
}

@Composable
fun DetailContent(
    movie: MovieUiModel,
    viewModel: DetailViewModel
) {
    Column() {
        Header(
            movie = movie,
            viewModel = viewModel
        )
        Detail(movie = movie)
        Spacer(modifier = Modifier.requiredHeight(SmallPadding))
        Text(
            text = movie.title,
            style = MovieDetailTextStyle,
            modifier = Modifier.padding(NormalPadding)
        )
    }
}

@Composable
fun Header(
    movie: MovieUiModel,
    viewModel: DetailViewModel
) {
    Box {
        Image(
            painter = AsyncImagePainter(movie.imageUrl),
            contentDescription = null,
            modifier = Modifier
                .aspectRatio(DetailImageAspectRatio),
            contentScale = ContentScale.Crop
        )
        FloatingActionButton(
            onClick = {
                viewModel.updateMovie(
                    movie.copy(
                        isLiked = !movie.isLiked,
                        numLikes = movie.numLikes + when (!movie.isLiked) {
                            true -> 1
                            false -> -1
                        }
                    )
                )
            },
            modifier = Modifier
                .padding(NormalPadding)
                .align(Alignment.BottomEnd),
            shape = CircleShape
        ) {
            Icon(
                if (movie.isLiked) Icons.Filled.Favorite else Icons.Filled.FavoriteBorder,
                tint = Color.Red,
                contentDescription = null
            )
        }
    }
}

@Composable
fun Detail(movie: MovieUiModel) {
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
                    .padding(start = NormalPadding, top = SmallPadding)
                    .size(DetailIcon),
            )
            Text(
                text = movie.numLikes.toString(),
                overflow = TextOverflow.Ellipsis,
                maxLines = 1,
                style = MovieDetailItemTextStyle,
                color = Color.Red,
                modifier = Modifier
                    .padding(start = NormalPadding, top = SmallPadding)
            )
        }
    }
}