package com.moviearchive.data.repository

import com.moviearchive.core.Error
import com.moviearchive.core.Result
import com.moviearchive.core.map
import com.moviearchive.data.model.WeekTopDataModel
import com.moviearchive.data.model.toData
import com.moviearchive.data.source.api.api.ApiService
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.catch
import kotlinx.coroutines.flow.map

class WeekTopRepositoryImpl(
    val api: ApiService
) : WeekTopRepository {
    override fun weekTopTen(): Flow<Result<List<WeekTopDataModel>, Error>> =
        api.weekTopTen().map { result ->
            result.map { it.data.map { it.toData() } }
        }.catch { throwable ->
            Result.Failure(Error(message = throwable.message ?: "", throwable = throwable))
        }
}