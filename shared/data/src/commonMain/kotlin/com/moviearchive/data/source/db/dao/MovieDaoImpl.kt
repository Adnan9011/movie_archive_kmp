package com.moviearchive.data.source.db.dao

import app.cash.sqldelight.coroutines.asFlow
import app.cash.sqldelight.coroutines.mapToList
import app.cash.sqldelight.coroutines.mapToOne
import com.moviearchive.DatabaseSource
import com.moviearchive.data.source.db.util.Constant.THROW_QUERY_INSERT_MOVIE_EXCEPTION
import com.moviearchive.sqldelight.MovieTable
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.IO
import kotlinx.coroutines.flow.Flow

class MovieDaoImpl constructor(
    db: DatabaseSource
) : MovieDao {

    private val query = db.movie_tableQueries
    override fun getAll(): Flow<List<MovieTable>> =
        query.getAll()
            .asFlow()
            .mapToList(Dispatchers.IO)


    override fun get(movieId: String): Flow<MovieTable> =
        query.getMovie(movieId)
            .asFlow()
            .mapToOne(Dispatchers.IO)

    override suspend fun insert(movie: MovieTable) {
        query.transaction {
            afterRollback { throw Exception(THROW_QUERY_INSERT_MOVIE_EXCEPTION) }

            query.insert(
                id = movie.id,
                title = movie.title,
                image = movie.image,
                year = movie.year,
                stars = movie.stars
            )
        }
    }

    override suspend fun deleteAll() {
        query.deleteAll()
    }

    override suspend fun delete(movieId: String) {
        query.delete(movieId)
    }
}