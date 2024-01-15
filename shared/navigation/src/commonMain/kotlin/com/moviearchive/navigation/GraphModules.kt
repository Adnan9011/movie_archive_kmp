package com.moviearchive.navigation

import cafe.adriel.voyager.core.registry.screenModule
import com.moviearchive.navigation.graph.DetailGraph
import com.moviearchive.navigation.graph.HomeGraph

val graphScreenModule = screenModule {
    register<SharedGraphScreen.Home> {
        HomeGraph()
    }
    register<SharedGraphScreen.Detail> { provider ->
        DetailGraph(id = provider.id)
    }
}