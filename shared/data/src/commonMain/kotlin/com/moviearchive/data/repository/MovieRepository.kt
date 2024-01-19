package com.moviearchive.data.repository

import com.moviearchive.core.Error
import com.moviearchive.core.Result
import com.moviearchive.data.model.CelebritiesDataModel
import com.moviearchive.data.model.MovieDataModel
import com.moviearchive.data.model.PagingDataModel
import com.moviearchive.data.model.WeekTopDataModel
import kotlinx.coroutines.flow.Flow

interface MovieRepository {
    fun get(id: String): Flow<Result<MovieDataModel, Error>>
    fun search(title: String): Flow<Result<List<MovieDataModel>, Error>>
    fun weekTopTen(): Flow<Result<List<WeekTopDataModel>, Error>>
    fun popularCelebrities(): Flow<Result<PagingDataModel<CelebritiesDataModel>, Error>>
}