package com.moviearchive.data.source.api.api

import com.moviearchive.core.Error
import com.moviearchive.core.Result
import com.moviearchive.data.source.api.model.CelebrityApiModel
import com.moviearchive.data.source.api.model.MovieApiModel
import com.moviearchive.data.source.api.model.PagingApiModel
import com.moviearchive.data.source.api.model.ResponseApiModel
import com.moviearchive.data.source.api.model.ResponsePagingApiModel
import com.moviearchive.data.source.api.model.WeekTopApiModel
import com.moviearchive.data.source.api.util.MessageStatus
import com.moviearchive.data.source.api.util.PARAMETER
import com.moviearchive.data.source.api.util.Rout.POPULAR_CELEBRITIES
import com.moviearchive.data.source.api.util.Rout.SEARCH
import com.moviearchive.data.source.api.util.Rout.WEEK_TOP_TEN
import io.ktor.client.HttpClient
import io.ktor.client.call.body
import io.ktor.client.request.get
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow

class ApiServiceImpl(val httpClient: HttpClient) : ApiService {
    override fun movie(id: String): Flow<Result<ResponseApiModel<MovieApiModel>, Error>> = flow {
        emit(Result.Loading)
        try {
            val responseBody = httpClient.get(SEARCH) {
                url { parameters.append(PARAMETER.QUERY, id) }
            }.body<ResponseApiModel<MovieApiModel>>()
            when (responseBody.message) {
                MessageStatus.Success -> {
                    emit(Result.Success(responseBody))
                }

                MessageStatus.Failure -> {
                    emit(Result.Failure(Error(message = responseBody.messages)))
                }
            }
        } catch (exception: Exception) {
            exception.printStackTrace()
            emit(
                Result.Failure(
                    Error(
                        message = exception.message ?: "",
                        throwable = exception
                    )
                )
            )
        }
    }

    override fun search(title: String): Flow<Result<ResponseApiModel<MovieApiModel>, Error>> =
        flow {
            emit(Result.Loading)
            try {
                val responseBody = httpClient.get(SEARCH) {
                    url { parameters.append(PARAMETER.QUERY, title) }
                }.body<ResponseApiModel<MovieApiModel>>()
                when (responseBody.message) {
                    MessageStatus.Success -> {
                        emit(Result.Success(responseBody))
                    }

                    MessageStatus.Failure -> {
                        emit(Result.Failure(Error(message = responseBody.messages)))
                    }
                }
            } catch (exception: Exception) {
                exception.printStackTrace()
                emit(
                    Result.Failure(
                        Error(
                            message = exception.message ?: "",
                            throwable = exception
                        )
                    )
                )
            }
        }

    override fun weekTopTen(): Flow<Result<ResponseApiModel<WeekTopApiModel>, Error>> = flow {
        emit(Result.Loading)
        try {
            val responseBody =
                httpClient.get(WEEK_TOP_TEN).body<ResponseApiModel<WeekTopApiModel>>()
            when (responseBody.message) {
                MessageStatus.Success -> {
                    emit(Result.Success(responseBody))
                }

                MessageStatus.Failure -> {
                    emit(Result.Failure(Error(message = responseBody.messages)))
                }
            }
        } catch (exception: Exception) {
            exception.printStackTrace()
            emit(
                Result.Failure(
                    Error(
                        message = exception.message ?: "",
                        throwable = exception
                    )
                )
            )
        }
    }

    override fun popularCelebrities(): Flow<Result<ResponsePagingApiModel<PagingApiModel<CelebrityApiModel>>, Error>> =
        flow {
            emit(Result.Loading)
            try {
                val responseBody = httpClient.get(POPULAR_CELEBRITIES)
                    .body<ResponsePagingApiModel<PagingApiModel<CelebrityApiModel>>>()
                when (responseBody.message) {
                    MessageStatus.Success -> {
                        emit(Result.Success(responseBody))
                    }

                    MessageStatus.Failure -> {
                        emit(Result.Failure(Error(message = responseBody.messages)))
                    }
                }
            } catch (exception: Exception) {
                exception.printStackTrace()
                emit(
                    Result.Failure(
                        Error(
                            message = exception.message ?: "",
                            throwable = exception
                        )
                    )
                )
            }
        }
}