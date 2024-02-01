package com.moviearchive.data.source.api.util

import io.github.aakira.napier.Napier
import io.ktor.client.plugins.logging.Logger

private const val TAG = "Ktor"

class KtorLogger() : Logger {
    override fun log(message: String) {
        Napier.d(message, tag = TAG)
    }
}