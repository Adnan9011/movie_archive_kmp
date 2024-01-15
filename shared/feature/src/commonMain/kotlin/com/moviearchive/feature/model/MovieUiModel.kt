package com.moviearchive.feature.model

import com.moviearchive.domain.model.MovieDomainModel

data class MovieUiModel(
    val id: Int,
    val title: String,
    val imageUrl: String,
    val numComments: Int = 0,
    val numLikes: Int = 0,
    val isLiked: Boolean = false
)

internal fun MovieDomainModel.toUi() = MovieUiModel(
    id = id,
    title = title,
    imageUrl = imageUrl,
    numComments = numComments,
    numLikes = numLikes,
    isLiked = isLiked
)

internal fun MovieUiModel.toDomain() = MovieDomainModel(
    id = id,
    title = title,
    imageUrl = imageUrl,
    numComments = numComments,
    numLikes = numLikes,
    isLiked = isLiked
)