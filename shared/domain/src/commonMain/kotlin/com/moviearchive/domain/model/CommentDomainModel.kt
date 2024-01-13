package com.moviearchive.domain.model

import com.moviearchive.data.model.CommentDataModel

data class CommentDomainModel(
    val id: Int,
    val title: String
)

internal fun CommentDataModel.toDomain() = CommentDomainModel(
    id = id,
    title = title
)