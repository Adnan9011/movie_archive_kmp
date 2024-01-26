package com.moviearchive.data.source.db.dao

import com.moviearchive.sqldelight.WeekTopTable
import kotlinx.coroutines.flow.Flow

interface WeekTopDao {
    fun getAll(): Flow<List<WeekTopTable>>

    fun get(movieId: String): Flow<WeekTopTable>

    suspend fun insert(movie: WeekTopTable)

    suspend fun deleteAll()

    suspend fun delete(movieId: String)
}