package com.moviearchive.data.repository

import com.moviearchive.core.Error
import com.moviearchive.core.Result
import com.moviearchive.data.model.CommentDataModel
import kotlinx.coroutines.flow.Flow

interface CommentRepository {
    fun getAll(movieId: Int): Flow<Result<List<CommentDataModel>, Error>>
    fun insertAll(comments: List<CommentDataModel>)
    fun insert(comment: CommentDataModel)
    fun deleteAll()
}