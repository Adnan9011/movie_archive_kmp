package com.moviearchive.domain.model

import com.moviearchive.data.model.MovieDataModel

data class MovieDomainModel(
    val id: Int,
    val title: String,
    val imageUrl: String,
    val numComments: Int,
    val numLikes: Int,
    val isLiked: Boolean
)

internal fun MovieDataModel.toDomain() = MovieDomainModel(
    id = id,
    title = title,
    imageUrl = imageUrl,
    numComments = numComments,
    numLikes = numLikes,
    isLiked = isLiked
)

internal fun MovieDomainModel.toData() = MovieDataModel(
    id = id,
    title = title,
    imageUrl = imageUrl,
    numComments = numComments,
    numLikes = numLikes,
    isLiked = isLiked
)