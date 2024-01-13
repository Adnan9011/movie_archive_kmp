package com.moviearchive.data.source.db.dao

import com.moviearchive.sqldelight.CommentTable
import kotlinx.coroutines.flow.Flow

interface CommentDao {
    fun getAll(movieId: Int): Flow<List<CommentTable>>

    suspend fun insertAll(comments: List<CommentTable>)

    suspend fun insert(comment: CommentTable)

    suspend fun deleteAll()
}