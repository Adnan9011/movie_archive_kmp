package com.moviearchive.feature.presentation.home

import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.lazy.LazyRow
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.verticalScroll
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Warning
import androidx.compose.material3.Divider
import androidx.compose.material3.Icon
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import coil3.compose.LocalPlatformContext
import coil3.compose.rememberAsyncImagePainter
import coil3.request.ImageRequest
import com.moviearchive.core.Result.Failure
import com.moviearchive.core.Result.Loading
import com.moviearchive.core.Result.Success
import com.moviearchive.feature.model.MovieUiModel
import com.moviearchive.feature.util.BANNER_IMAGE_URL
import com.moviearchive.feature.util.ShimmerMovieItemCount
import com.moviearchive.ui.theme.EmptyMovieSize
import com.moviearchive.ui.theme.EmptyTextStyle
import com.moviearchive.ui.theme.HighPadding
import com.moviearchive.ui.theme.HomeBannerHeight
import com.moviearchive.ui.theme.HomeTextTitleStyle
import com.moviearchive.ui.theme.MovieItemHeight
import com.moviearchive.ui.theme.MovieItemRound
import com.moviearchive.ui.theme.NormalPadding
import com.moviearchive.ui.theme.SmallPadding
import com.moviearchive.ui.widget.AppBarHome
import kotlinx.collections.immutable.PersistentList
import org.koin.compose.koinInject

@Composable
fun HomeScreen(
    modifier: Modifier,
    viewModel: HomeViewModel = koinInject(),
    onShowDetail: (movieId: Int) -> Unit
) {
    Scaffold(topBar = {
        AppBarHome(
            title = "Movies",
            onFavoriteClicked = { isFavorite ->
                viewModel.getFavoriteMovies(isFavorite)
            }
        )
    }) { paddingValues ->
        HomeContent(
            viewModel = viewModel,
            modifier = modifier.padding(paddingValues),
            onShowDetail = onShowDetail
        )
    }
}

@Composable
fun HomeContent(
    viewModel: HomeViewModel,
    modifier: Modifier,
    onShowDetail: (movieId: Int) -> Unit
) {
    val uiState by viewModel.uiMovies.collectAsState()
    var isEnableShimmer by remember { mutableStateOf(false) }

    LaunchedEffect(
        key1 = true,
    ) {
        viewModel.getMovies()
    }

    Column(
        modifier = modifier
            .fillMaxWidth()
    ) {
        TopBanner()

        Text(
            text = "List of Movies",
            style = HomeTextTitleStyle,
            modifier = Modifier.padding(
                top = NormalPadding,
                start = HighPadding,
                bottom = NormalPadding
            )
        )

        when (uiState) {
            Loading -> {
                isEnableShimmer = true

                ShowShimmerMovies(
                    isEnableShimmer = true,
                    count = ShimmerMovieItemCount
                )
            }

            is Failure -> {
                //Todo: Implement Error Handling
            }

            is Success -> {
                isEnableShimmer = false

                val movies = (uiState as Success<PersistentList<MovieUiModel>>).value

                if (movies.isEmpty()) {
                    EmptyMovies()
                } else {
                    ShowMovies(
                        movies = movies,
                        onShowDetail = onShowDetail
                    )
                }
            }
        }
    }
}

@Composable
fun TopBanner() {

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
            painter = rememberAsyncImagePainter(
                model = ImageRequest.Builder(LocalPlatformContext.current).data(BANNER_IMAGE_URL)
                    .build()
            ),
            contentScale = ContentScale.Crop,
            contentDescription = null
        )
    }
}

@Composable
fun EmptyMovies() {
    Column(
        modifier = Modifier
            .padding(NormalPadding)
            .fillMaxWidth()
            .height(MovieItemHeight),
        verticalArrangement = Arrangement.Center,
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        Icon(
            modifier = Modifier
                .size(EmptyMovieSize),
            imageVector = Icons.Filled.Warning,
            tint = Color.DarkGray,
            contentDescription = null
        )
        Divider(
            Modifier
                .height(NormalPadding),
            color = Color.Transparent
        )
        Text(
            text = "Movie Not Found",
            style = EmptyTextStyle
        )
    }
}

@Composable
fun ShowMovies(
    movies: PersistentList<MovieUiModel>,
    onShowDetail: (movieId: Int) -> Unit
) {
    val scrollState = rememberScrollState()

    LazyRow(
        Modifier
            .verticalScroll(scrollState)
    ) {
        items(
            items = movies,
            key = { movie -> movie.id }
        ) { movie ->
            MovieItem(
                movie = movie,
                onShowDetail = onShowDetail
            )
        }
    }
}

@Composable
fun ShowShimmerMovies(
    isEnableShimmer: Boolean,
    count: Int
) {
    LazyRow() {
        items(count = count) {
            ShimmerMovieItem(isEnableShimmer = isEnableShimmer)
        }
    }
}

