package com.moviearchive.data.repository

import com.moviearchive.core.Error
import com.moviearchive.core.Result
import com.moviearchive.core.map
import com.moviearchive.data.model.CelebritiesDataModel
import com.moviearchive.data.model.MovieDataModel
import com.moviearchive.data.model.PagingDataModel
import com.moviearchive.data.model.WeekTopDataModel
import com.moviearchive.data.model.toData
import com.moviearchive.data.source.api.api.ApiService
import com.moviearchive.data.source.datastore.DataStoreSource
import com.moviearchive.data.source.db.dao.MovieDao
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.catch
import kotlinx.coroutines.flow.map

class MovieRepositoryImpl(
    val api: ApiService,
    val dao: MovieDao,
    val dataStore: DataStoreSource
) : MovieRepository {
    override fun get(id: String): Flow<Result<MovieDataModel, Error>> =
        api.movie(id).map { result ->
            result.map {
                it.data[0].toData()
            }
        }.catch { throwable ->
            Result.Failure(Error(message = throwable.message ?: "", throwable = throwable))
        }

    override fun search(title: String): Flow<Result<List<MovieDataModel>, Error>> =
        api.search(title).map { result ->
            result.map { it.data.map { it.toData() } }
        }.catch { throwable ->
            Result.Failure(Error(message = throwable.message ?: "", throwable = throwable))
        }

    override fun weekTopTen(): Flow<Result<List<WeekTopDataModel>, Error>> =
        api.weekTopTen().map { result ->
            result.map { it.data.map { it.toData() } }
        }.catch { throwable ->
            Result.Failure(Error(message = throwable.message ?: "", throwable = throwable))
        }

    override fun popularCelebrities(): Flow<Result<PagingDataModel<CelebritiesDataModel>, Error>> =
        api.popularCelebrities().map { result ->
            result.map { it.data!!.toData() }
        }.catch { throwable ->
            Result.Failure(Error(message = throwable.message ?: "", throwable = throwable))
        }
}