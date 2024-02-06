package com.moviearchive.data.source.datastore

import android.content.Context
import androidx.datastore.core.DataStore
import androidx.datastore.preferences.core.Preferences
import com.moviearchive.data.util.Constant.DATA_STORE_FILE_NAME

fun dataStore(context: Context): DataStore<Preferences> =
    createDataStore(
        producePath = { context.filesDir.resolve(DATA_STORE_FILE_NAME).absolutePath }
    )
