package com.moviearchive.data.source.api.util

import io.ktor.client.plugins.logging.Logger

class CustomHttpLogger() : Logger {
    override fun log(message: String) {
        //TODO: Fix Logging in Ktor
//        Log.d("Ktor", message)
    }
}