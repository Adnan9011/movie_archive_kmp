package com.moviearchive.data.model

import com.moviearchive.data.source.api.model.CommentApiModel
import com.moviearchive.sqldelight.CommentTable

data class CommentDataModel(
    val id: Int,
    val title: String,
    val movieId: Int
)

internal fun CommentApiModel.toData() = CommentDataModel(
    id = id,
    title = title,
    movieId = movieId
)

internal fun CommentTable.toData() = CommentDataModel(
    id = id.toInt(),
    title = title,
    movieId = movieId.toInt()
)