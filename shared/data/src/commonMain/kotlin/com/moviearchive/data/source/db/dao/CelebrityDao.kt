package com.moviearchive.data.source.db.dao

import com.moviearchive.sqldelight.CelebrityTable
import kotlinx.coroutines.flow.Flow

interface CelebrityDao {
    fun getAll(): Flow<List<CelebrityTable>>

    suspend fun insert(celebrity: CelebrityTable)

    suspend fun deleteAll()

    suspend fun delete(celebrityId: String)
}