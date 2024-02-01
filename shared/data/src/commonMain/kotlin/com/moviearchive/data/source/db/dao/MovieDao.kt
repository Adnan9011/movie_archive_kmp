package com.moviearchive.data.source.db.dao

import com.moviearchive.sqldelight.MovieTable
import kotlinx.coroutines.flow.Flow

interface MovieDao {
    fun getAll(): Flow<List<MovieTable>>

    fun get(movieId: String): Flow<MovieTable>

    suspend fun insert(movie: MovieTable)

    suspend fun deleteAll()

    suspend fun delete(movieId: String)
}