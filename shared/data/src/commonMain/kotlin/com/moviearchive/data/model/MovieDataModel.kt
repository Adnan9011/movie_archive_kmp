package com.moviearchive.data.model

import com.moviearchive.data.source.api.model.MovieApiModel
import com.moviearchive.sqldelight.MovieTable

data class MovieDataModel(
    val id: Int,
    val title: String,
    val imageUrl: String,
    val numComments: Int,
    val numLikes: Int,
    val isLiked: Boolean
)

internal fun MovieApiModel.toData() = MovieDataModel(
    id = id,
    title = title,
    imageUrl = imageUrl,
    numComments = numComments,
    numLikes = numLikes,
    isLiked = isLiked
)

internal fun MovieTable.toData() = MovieDataModel(
    id = id.toInt(),
    title = title,
    imageUrl = imageUrl,
    numComments = numComments.toInt(),
    numLikes = numLikes.toInt(),
    isLiked = when {
        (isLiked > 0) -> true
        else -> false
    }
)

internal fun MovieDataModel.toDatabase() = MovieTable(
    id = id.toLong(),
    title = title,
    imageUrl = imageUrl,
    numComments = numComments.toLong(),
    numLikes = numLikes.toLong(),
    isLiked = if (isLiked) 1 else 0
)