package com.moviearchive.di

import androidx.compose.runtime.Composable
import coil3.ImageLoader
import coil3.annotation.ExperimentalCoilApi
import coil3.compose.setSingletonImageLoaderFactory
import coil3.fetch.NetworkFetcher
import com.moviearchive.core.platform.AppContext
import com.moviearchive.feature.presentation.detail.DetailScreen
import com.moviearchive.feature.presentation.home.HomeScreen
import com.moviearchive.navigation.Destinations
import com.moviearchive.navigation.DestinationsArgs.MOVIE_DETAIL_ID_ARG
import com.moviearchive.navigation.NavigationActions
import moe.tlaster.precompose.PreComposeApp
import moe.tlaster.precompose.navigation.NavHost
import moe.tlaster.precompose.navigation.path
import moe.tlaster.precompose.navigation.rememberNavigator
import org.koin.compose.KoinApplication

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
            val navigator = rememberNavigator()
            val navigationActions = NavigationActions(navigator)
            NavHost(
                navigator = navigator,
                initialRoute = Destinations.HOME_ROUT,
            ) {
                scene(
                    route = Destinations.HOME_ROUT
                ) {
                    HomeScreen(
                        onShowDetail = { movieId ->
                            navigationActions.navigateToDetail(movieId)
                        },
                        onShowCelebrity = { celebrityId ->
                            //Todo: Create Celebrity Screen
                        }
                    )
                }
                scene(
                    route = Destinations.DETAIL_ROUT
                ) { backStackEntry ->
                    backStackEntry.path<String>(MOVIE_DETAIL_ID_ARG)?.let { movieId ->
                        DetailScreen(
                            movieId = movieId,
                            onBackClicked = {
                                navigationActions.navigateToHome()
                            }
                        )
                    }
                }
            }
        }
    }
}