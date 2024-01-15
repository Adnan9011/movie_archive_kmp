package com.moviearchive.feature.presentation.detail

import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.aspectRatio
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.requiredHeight
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.rememberLazyListState
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
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.RectangleShape
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.text.style.TextOverflow
import coil3.compose.rememberAsyncImagePainter
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
import org.koin.compose.koinInject

@Composable
fun DetailScreen(
    modifier: Modifier,
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
            ) { paddingValues ->
                DetailContent(
                    modifier = modifier.padding(paddingValues),
                    movie = movie,
                    viewModel = viewModel
                )
            }
        }
    }
}

@Composable
fun DetailContent(
    modifier: Modifier,
    movie: MovieUiModel,
    viewModel: DetailViewModel
) {
    val listState = rememberLazyListState()

    DetailScaffold(
        modifier = modifier
    ) {
        LazyColumn(state = listState) {
            item {
                Header(movie = movie, viewModel = viewModel)
            }
            item {
                Spacer(modifier = Modifier.requiredHeight(SmallPadding))
            }
            item {
                Text(
                    text = movie.title,
                    style = MovieDetailTextStyle,
                    modifier = Modifier.padding(NormalPadding)
                )
            }
        }
    }
}

@Composable
fun DetailScaffold(
    modifier: Modifier,
    content: @Composable () -> Unit,
) {
    val backgroundColor = Color.Transparent
    Box(modifier = modifier.fillMaxSize()) {
        Surface(modifier = Modifier.fillMaxSize(), content = content)
    }
}

@Composable
fun Header(
    movie: MovieUiModel,
    viewModel: DetailViewModel
) {


    Image(
        painter = rememberAsyncImagePainter(
            model = movie.imageUrl
        ),
        contentDescription = null,
        modifier = Modifier
            .aspectRatio(DetailImageAspectRatio),
        contentScale = ContentScale.Crop
    )
    Card(
        modifier = Modifier
            .fillMaxWidth()
            .requiredHeight(DetailCardHeight),
        shape = RectangleShape
    ) {}

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

    Box(
        modifier = Modifier
    ) {
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
            modifier = Modifier.padding(NormalPadding),
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