package com.moviearchive.data.source.api.util

import io.github.aakira.napier.Napier
import io.ktor.client.plugins.logging.Logger

class CustomHttpLogger() : Logger {
    override fun log(message: String) {
        Napier.d(tag = "Ktor") { message }
    }
}