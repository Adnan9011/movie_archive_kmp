package com.moviearchive.data.repository

import com.moviearchive.core.Error
import com.moviearchive.core.Result
import com.moviearchive.core.map
import com.moviearchive.data.model.MovieDataModel
import com.moviearchive.data.model.toData
import com.moviearchive.data.model.toTable
import com.moviearchive.data.source.api.api.ApiService
import com.moviearchive.data.source.db.dao.MovieDao
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.catch
import kotlinx.coroutines.flow.map

class MovieRepositoryImpl(
    val api: ApiService,
    val dao: MovieDao
) : MovieRepository {
    override fun get(id: String): Flow<Result<MovieDataModel, Error>> =
        api.movie(id).map { result ->
            result.map {
                it.data[0].toData()
            }
        }.catch { throwable ->
            emit(
                Result.Failure(
                    Error(message = throwable.message ?: "", throwable = throwable)
                )
            )
        }

    override fun search(title: String): Flow<Result<List<MovieDataModel>, Error>> =
        api.search(title).map { result ->
            result.map { it.data.map { it.toData() } }
        }.catch { throwable ->
            emit(
                Result.Failure(
                    Error(message = throwable.message ?: "", throwable = throwable)
                )
            )
        }

    override suspend fun favorite(movie: MovieDataModel) {
        dao.insert(movie.toTable())
    }

    override suspend fun delete(movieId: String) {
        dao.delete(movieId)
    }

    override fun getFavorite(movieId: String): Flow<Result<MovieDataModel, Error>> =
        dao.get(movieId).map {
            Result.Success(it.toData())
        }.catch { throwable ->
            emit(
                Result.Failure(
                    Error(message = throwable.message ?: "", throwable = throwable)
                )
            )
        }


    override fun getFavorites(): Flow<Result<List<MovieDataModel>, Error>> =
        dao.getAll().map { list ->
            Result.Success(list.map { it.toData() })
        }.catch { throwable ->
            emit(
                Result.Failure(
                    Error(message = throwable.message ?: "", throwable = throwable)
                )
            )
        }
}