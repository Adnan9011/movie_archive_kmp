package com.moviearchive.navigation

import com.moviearchive.feature.model.CelebrityUiModel
import com.moviearchive.navigation.DestinationsArgs.MOVIE_CELEBRITY_FAVORITE_ARG
import com.moviearchive.navigation.DestinationsArgs.MOVIE_CELEBRITY_ID_ARG
import com.moviearchive.navigation.DestinationsArgs.MOVIE_CELEBRITY_IMAGE_ARG
import com.moviearchive.navigation.DestinationsArgs.MOVIE_CELEBRITY_NAME_ARG
import com.moviearchive.navigation.Screens.CELEBRITY_SCREEN
import com.moviearchive.navigation.Screens.DETAIL_SCREEN
import com.moviearchive.navigation.Screens.HOME_SCREEN
import com.moviearchive.navigation.Screens.SPLASH_SCREEN
import moe.tlaster.precompose.navigation.NavOptions
import moe.tlaster.precompose.navigation.Navigator
import moe.tlaster.precompose.navigation.PopUpTo

private object Screens {
    const val SPLASH_SCREEN = "/splash"
    const val HOME_SCREEN = "/home"
    const val DETAIL_SCREEN = "/detail"
    const val CELEBRITY_SCREEN = "/celebrity"
}

object DestinationsArgs {
    const val MOVIE_DETAIL_ID_ARG = "id"
    const val MOVIE_CELEBRITY_ID_ARG = "id"
    const val MOVIE_CELEBRITY_NAME_ARG = "name"
    const val MOVIE_CELEBRITY_IMAGE_ARG = "image"
    const val MOVIE_CELEBRITY_FAVORITE_ARG = "favorite"
}

object Destinations {
    const val SPLASH_ROUT = SPLASH_SCREEN
    const val HOME_ROUT = HOME_SCREEN
    const val DETAIL_ROUT =
        "$DETAIL_SCREEN/{id:[a-zA-Z0-9]+}"
    const val CELEBRITY_ROUT = CELEBRITY_SCREEN
}

class NavigationActions(private val navController: Navigator) {
    fun navigateToHome() {
        navController.navigate(HOME_SCREEN, NavOptions(popUpTo = PopUpTo.First(inclusive = true)))
    }

    fun navigateToDetail(movieId: String) {
        navController.navigate(
            DETAIL_SCREEN +
                "/${movieId}"
        )
    }

    fun navigateToCelebrity(celebrity: CelebrityUiModel) {
        navController.navigate(
            CELEBRITY_SCREEN +
                "?$MOVIE_CELEBRITY_ID_ARG=${celebrity.id}" +
                "&$MOVIE_CELEBRITY_NAME_ARG=${celebrity.name}" +
                "&$MOVIE_CELEBRITY_IMAGE_ARG=${celebrity.image}" +
                "&$MOVIE_CELEBRITY_FAVORITE_ARG=${celebrity.isFavorite}"
        )
    }
}