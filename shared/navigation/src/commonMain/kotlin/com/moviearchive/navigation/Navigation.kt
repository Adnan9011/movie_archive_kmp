package com.moviearchive.navigation

import com.moviearchive.navigation.Screens.DETAIL_SCREEN
import com.moviearchive.navigation.Screens.HOME_SCREEN
import moe.tlaster.precompose.navigation.Navigator

private object Screens {
    const val HOME_SCREEN = "/home"
    const val DETAIL_SCREEN = "/detail"
}

object DestinationsArgs {
    const val MOVIE_DETAIL_ID_ARG = "id"
}

object Destinations {
    const val HOME_ROUT = HOME_SCREEN
    const val DETAIL_ROUT =
        "$DETAIL_SCREEN/{id:[0-9]+}"
}

class NavigationActions(private val navController: Navigator) {
    fun navigateToHome() {
        navController.navigate(HOME_SCREEN)
    }

    fun navigateToDetail(movieId: String) {
        navController.navigate(
            DETAIL_SCREEN +
                "/${movieId}"
        )
    }
}