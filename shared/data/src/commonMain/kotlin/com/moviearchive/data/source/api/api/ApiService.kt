package com.moviearchive.data.source.api.api

import com.moviearchive.core.Error
import com.moviearchive.core.Result
import com.moviearchive.data.source.api.model.CommentApiModel
import com.moviearchive.data.source.api.model.MovieApiModel
import kotlinx.coroutines.flow.Flow

interface ApiService {
    fun getMovies(): Flow<Result<List<MovieApiModel>, Error>>
    fun getComments(): Flow<Result<List<CommentApiModel>, Error>>
}