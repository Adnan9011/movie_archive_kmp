package com.moviearchive.data.repository

import com.moviearchive.core.Error
import com.moviearchive.core.Result
import com.moviearchive.data.model.MovieDataModel
import kotlinx.coroutines.flow.Flow

interface MovieRepository {
    fun get(id: String): Flow<Result<MovieDataModel, Error>>
    fun search(title: String): Flow<Result<List<MovieDataModel>, Error>>

    suspend fun favorite(movie: MovieDataModel)

    suspend fun delete(movieId: String)

    fun getFavorite(movieId: String): Flow<Result<MovieDataModel, Error>>

    fun getFavorites(): Flow<Result<List<MovieDataModel>, Error>>
}