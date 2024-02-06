package com.moviearchive.data.util

import androidx.datastore.preferences.core.booleanPreferencesKey

object Constant {
    const val TIME_OUT = 60_000L
    internal const val DATA_STORE_FILE_NAME = "movie_archive.preferences_pb"

    val ENABLE_FAVORITE_CONTENT = booleanPreferencesKey("ENABLE_FAVORITE_CONTENT")

}