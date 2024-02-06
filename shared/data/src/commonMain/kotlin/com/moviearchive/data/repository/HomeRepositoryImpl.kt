package com.moviearchive.data.repository

import com.moviearchive.data.source.datastore.DataStoreSource
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.map

class HomeRepositoryImpl(
    val dataStore: DataStoreSource
) : HomeRepository {
    override fun getFavoriteStatus(): Flow<Boolean> =
        dataStore.enableFavorite.map { it ?: false }

    override suspend fun updateFavoriteStatus(isFavorite: Boolean) {
        dataStore.updateEnableFavorite(isFavorite)
    }
}