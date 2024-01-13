package com.moviearchive.data.repository

import com.moviearchive.core.Error
import com.moviearchive.core.Result
import com.moviearchive.data.model.MovieDataModel
import kotlinx.coroutines.flow.Flow

interface MovieRepository {
    suspend fun getFromApi()
    fun getAll(): Flow<Result<List<MovieDataModel>, Error>>
    fun get(id: Int): Flow<Result<MovieDataModel, Error>>
    fun getAllLiked(): Flow<Result<List<MovieDataModel>, Error>>

    suspend fun update(movie: MovieDataModel)
    suspend fun insertAll(movies: List<MovieDataModel>)
    suspend fun deleteAll()
}