package com.moviearchive.data.repository

import kotlinx.coroutines.flow.Flow

interface HomeRepository {
    fun getFavoriteStatus(): Flow<Boolean>

    suspend fun updateFavoriteStatus(isFavorite: Boolean)
}