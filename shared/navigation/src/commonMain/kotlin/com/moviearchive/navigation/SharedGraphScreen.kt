package com.moviearchive.navigation

import cafe.adriel.voyager.core.registry.ScreenProvider

sealed class SharedGraphScreen : ScreenProvider {
    object Home : SharedGraphScreen()
    data class Detail(val id: Int) : SharedGraphScreen()
}