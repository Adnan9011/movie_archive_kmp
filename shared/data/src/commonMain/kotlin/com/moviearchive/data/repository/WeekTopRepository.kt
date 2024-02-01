package com.moviearchive.data.repository

import com.moviearchive.core.Error
import com.moviearchive.core.Result
import com.moviearchive.data.model.WeekTopDataModel
import kotlinx.coroutines.flow.Flow

interface WeekTopRepository {
    suspend fun favorite(movie: WeekTopDataModel)

    fun getFavorites(): Flow<Result<List<WeekTopDataModel>, Error>>

    fun weekTopTen(): Flow<Result<List<WeekTopDataModel>, Error>>
}