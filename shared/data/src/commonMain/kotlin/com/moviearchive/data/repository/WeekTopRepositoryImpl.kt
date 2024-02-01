package com.moviearchive.data.repository

import com.moviearchive.core.Error
import com.moviearchive.core.Result
import com.moviearchive.core.map
import com.moviearchive.data.model.WeekTopDataModel
import com.moviearchive.data.model.toData
import com.moviearchive.data.model.toTable
import com.moviearchive.data.source.api.api.ApiService
import com.moviearchive.data.source.db.dao.WeekTopDao
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.catch
import kotlinx.coroutines.flow.map

class WeekTopRepositoryImpl(
    val api: ApiService,
    val dao: WeekTopDao
) : WeekTopRepository {
    override fun weekTopTen(): Flow<Result<List<WeekTopDataModel>, Error>> =
        api.weekTopTen().map { result ->
            result.map { it.data.map { it.toData() } }
        }.catch { throwable ->
            Result.Failure(Error(message = throwable.message ?: "", throwable = throwable))
        }

    override suspend fun favorite(movie: WeekTopDataModel) {
        dao.insert(movie.toTable())
    }

    override fun getFavorites(): Flow<Result<List<WeekTopDataModel>, Error>> =
        dao.getAll().map { list ->
            Result.Success(list.map { it.toData() })
        }
}