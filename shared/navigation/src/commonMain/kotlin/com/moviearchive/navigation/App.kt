package com.moviearchive.navigation

import androidx.compose.runtime.Composable
import coil3.ImageLoader
import coil3.annotation.ExperimentalCoilApi
import coil3.compose.setSingletonImageLoaderFactory
import coil3.fetch.NetworkFetcher
import com.moviearchive.core.platform.AppContext
import com.moviearchive.feature.model.CelebrityUiModel
import com.moviearchive.feature.presentation.celebrity.CelebrityScreen
import com.moviearchive.feature.presentation.detail.DetailScreen
import com.moviearchive.feature.presentation.home.HomeScreen
import com.moviearchive.feature.presentation.splash.SplashScreen
import com.moviearchive.navigation.DestinationsArgs.MOVIE_CELEBRITY_FAVORITE_ARG
import com.moviearchive.navigation.DestinationsArgs.MOVIE_CELEBRITY_ID_ARG
import com.moviearchive.navigation.DestinationsArgs.MOVIE_CELEBRITY_IMAGE_ARG
import com.moviearchive.navigation.DestinationsArgs.MOVIE_CELEBRITY_NAME_ARG
import com.moviearchive.navigation.DestinationsArgs.MOVIE_DETAIL_ID_ARG
import com.moviearchive.navigation.di.ProvideModules
import moe.tlaster.precompose.PreComposeApp
import moe.tlaster.precompose.navigation.NavHost
import moe.tlaster.precompose.navigation.path
import moe.tlaster.precompose.navigation.query
import moe.tlaster.precompose.navigation.rememberNavigator
import moe.tlaster.precompose.navigation.BrowserNavigationController
import org.koin.compose.KoinApplication
import org.koin.compose.koinInject

@OptIn(ExperimentalCoilApi::class)
@Composable
fun App(appContext: AppContext) {
    KoinApplication(
        application = {
            modules(ProvideModules.getModules(appContext))
        }
    ) {
        setSingletonImageLoaderFactory { context ->
            ImageLoader.Builder(context)
                .components {
                    add(NetworkFetcher.Factory())
                }
                .build()
        }

        PreComposeApp {
            val navigator = rememberNavigator(controller = BrowserNavigationController())
            val navigationActions = NavigationActions(navigator)
            NavHost(
                navigator = navigator,
                initialRoute = Destinations.SPLASH_ROUT,
            ) {
                scene(
                    route = Destinations.SPLASH_ROUT
                ) {
                    SplashScreen(
                        viewModel = koinInject(),
                        onSplashEnd = {
                            navigationActions.navigateToHome()
                        }
                    )
                }
                scene(
                    route = Destinations.HOME_ROUT
                ) {
                    HomeScreen(
                        viewModel = koinInject(),
                        onShowDetail = { movieId ->
                            navigationActions.navigateToDetail(movieId)
                        },
                        onShowCelebrity = { celebrity ->
                            navigationActions.navigateToCelebrity(celebrity)
                        }
                    )
                }
                scene(
                    route = Destinations.DETAIL_ROUT
                ) { backStackEntry ->
                    backStackEntry.path<String>(MOVIE_DETAIL_ID_ARG)?.let { movieId ->
                        DetailScreen(
                            viewModel = koinInject(),
                            movieId = movieId,
                            onBackClicked = {
                                navigationActions.navigateToHome()
                            }
                        )
                    }
                }
                scene(
                    route = Destinations.CELEBRITY_ROUT
                ) { backStackEntry ->
                    val celebrity = CelebrityUiModel(
                        id = backStackEntry.query(name = MOVIE_CELEBRITY_ID_ARG, "") ?: "",
                        name = backStackEntry.query(name = MOVIE_CELEBRITY_NAME_ARG, "") ?: "",
                        image = backStackEntry.query(name = MOVIE_CELEBRITY_IMAGE_ARG, "") ?: "",
                        isFavorite = backStackEntry.query(
                            name = MOVIE_CELEBRITY_FAVORITE_ARG,
                            false
                        ) ?: false
                    )
                    CelebrityScreen(
                        viewModel = koinInject(),
                        celebrity = celebrity,
                        onBackClicked = {
                            navigationActions.navigateToHome()
                        }
                    )
                }
            }
        }
    }
}