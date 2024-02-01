package com.moviearchive.data.repository

import com.moviearchive.core.Error
import com.moviearchive.core.Result
import com.moviearchive.data.model.CelebrityDataModel
import com.moviearchive.data.model.PagingDataModel
import kotlinx.coroutines.flow.Flow

interface CelebrityRepository {

    suspend fun favorite(movie: CelebrityDataModel)

    fun getFavorites(): Flow<Result<List<CelebrityDataModel>, Error>>
    fun popularCelebrities(): Flow<Result<PagingDataModel<CelebrityDataModel>, Error>>
}