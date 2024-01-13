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


    override fun getMovie(movieId: Int): Flow<MovieTable> =
        query.getMovie(movieId.toLong())
            .asFlow()
            .mapToOne(Dispatchers.IO)


    override fun getAllLiked(): Flow<List<MovieTable>> =
        query.getAllLiked()
            .asFlow()
            .mapToList(Dispatchers.IO)

    override suspend fun update(movie: MovieTable) {
        query.update(
            id = movie.id,
            title = movie.title,
            imageUrl = movie.imageUrl,
            numComments = movie.numComments,
            numLikes = movie.numLikes,
            isLiked = movie.isLiked
        )
    }

    override suspend fun insertAll(movies: List<MovieTable>) {
        query.transaction {
            afterRollback { throw Exception(THROW_QUERY_INSERT_MOVIE_EXCEPTION) }

            movies.forEach { movie ->
                query.insert(
                    id = movie.id,
                    title = movie.title,
                    imageUrl = movie.imageUrl,
                    numComments = movie.numComments,
                    numLikes = movie.numLikes,
                    isLiked = movie.isLiked
                )
            }
        }
    }

    override suspend fun deleteAll() {
        query.deleteAll()
    }
}