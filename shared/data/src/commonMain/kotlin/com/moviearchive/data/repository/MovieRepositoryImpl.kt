package com.moviearchive.data.repository

import com.moviearchive.core.Error
import com.moviearchive.core.Result
import com.moviearchive.data.model.MovieDataModel
import com.moviearchive.data.model.toData
import com.moviearchive.data.model.toDatabase
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
    override suspend fun getFromApi() {
        api.getMovies().collect { result ->
            if (result is Result.Success)
                dao.insertAll(result.value.map { it.toData().toDatabase() })
        }
    }

    override fun getAll(): Flow<Result<List<MovieDataModel>, Error>> {
        return dao.getAll().map { list ->
            list.map { it.toData() }
        }
            .map { Result.Success(value = it) }
            .catch { throwable ->
                Result.Failure(Error(throwable = throwable))
            }
    }

    override fun get(id: Int): Flow<Result<MovieDataModel, Error>> =
        dao.getMovie(id)
            .map { it.toData() }
            .map { Result.Success(value = it) }
            .catch { throwable ->
                Result.Failure(Error(throwable = throwable))
            }

    override fun getAllLiked(): Flow<Result<List<MovieDataModel>, Error>> =
        dao.getAllLiked()
            .map { list ->
                list.map { it.toData() }
            }.map { Result.Success(value = it) }
            .catch { throwable ->
                Result.Failure(Error(throwable = throwable))
            }

    override suspend fun update(movie: MovieDataModel) {
        dao.update(movie.toDatabase())
    }

    override suspend fun insertAll(movies: List<MovieDataModel>) =
        dao.insertAll(
            movies.map { model ->
                model.toDatabase()
            }
        )

    override suspend fun deleteAll() {
        dao.deleteAll()
    }
}