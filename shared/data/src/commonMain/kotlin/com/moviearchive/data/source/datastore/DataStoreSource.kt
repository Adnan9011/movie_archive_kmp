package com.moviearchive.data.source.datastore

import androidx.datastore.core.DataStore
import androidx.datastore.preferences.core.Preferences
import androidx.datastore.preferences.core.edit
import com.moviearchive.data.util.Constant.ENABLE_FAVORITE_CONTENT
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.map

class DataStoreSource(private val dataStore: DataStore<Preferences>) {
    suspend fun updateEnableFavorite(isFavorite: Boolean) {
        dataStore.edit { preferences ->
            preferences[ENABLE_FAVORITE_CONTENT] = isFavorite
        }
    }

    val enableFavorite: Flow<Boolean?> = dataStore.data
        .map { preferences ->
            preferences[ENABLE_FAVORITE_CONTENT]
        }
}
