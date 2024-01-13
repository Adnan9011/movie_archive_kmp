package com.moviearchive.data.repository

import com.moviearchive.core.Error
import com.moviearchive.core.Result
import com.moviearchive.core.map
import com.moviearchive.data.model.CommentDataModel
import com.moviearchive.data.model.toData
import com.moviearchive.data.source.api.api.ApiServiceImpl
import com.moviearchive.data.source.db.dao.CommentDao
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.map

class CommentRepositoryImpl constructor(
    val api: ApiServiceImpl,
    val dao: CommentDao
) : CommentRepository {

    override fun getAll(movieId: Int): Flow<Result<List<CommentDataModel>, Error>> =
        api.getComments().map { result ->
            result.map { list ->
                list.map { it.toData() }
            }
        }

    override fun insertAll(comments: List<CommentDataModel>) {
    }

    override fun insert(comment: CommentDataModel) {
    }

    override fun deleteAll() {
    }
}