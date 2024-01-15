package com.moviearchive.feature.model

import com.moviearchive.domain.model.CommentDomainModel

data class CommentUiModel(
    val id: Int,
    val title: String
)

internal fun CommentDomainModel.toUi() = CommentUiModel(
    id = id,
    title = title
)