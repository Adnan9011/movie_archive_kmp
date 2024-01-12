package com.moviearchive.app

interface Platform {
    val name: String
}

expect fun getPlatform(): Platform