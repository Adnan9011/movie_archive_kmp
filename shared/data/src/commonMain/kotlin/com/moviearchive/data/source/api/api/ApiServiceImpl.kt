package com.moviearchive.data.source.api.api

import com.moviearchive.core.Error
import com.moviearchive.core.Result
import com.moviearchive.data.source.api.model.CommentApiModel
import com.moviearchive.data.source.api.model.MovieApiModel
import com.moviearchive.data.source.api.util.Rout.COMMENTS_URL
import com.moviearchive.data.source.api.util.Rout.MOVIES_URL
import io.ktor.client.HttpClient
import io.ktor.client.call.body
import io.ktor.client.request.get
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow

class ApiServiceImpl(val httpClient: HttpClient) : ApiService {

    override fun getMovies(): Flow<Result<List<MovieApiModel>, Error>> = flow {
        emit(Result.Loading)
        try {
            emit(
                Result.Success(
                    httpClient.get(MOVIES_URL).body()
                )
            )
        } catch (exception: Exception) {
            exception.printStackTrace()
            emit(
                Result.Failure(Error(message = exception.message ?: "", throwable = exception))
            )
        }
    }

    override fun getComments(): Flow<Result<List<CommentApiModel>, Error>> = flow {
        emit(Result.Loading)
        try {
            emit(
                Result.Success(
                    httpClient.get(COMMENTS_URL).body()
                )
            )
        } catch (exception: Exception) {
            emit(
                Result.Failure(Error(message = exception.message ?: "", throwable = exception))
            )
        }
    }
}