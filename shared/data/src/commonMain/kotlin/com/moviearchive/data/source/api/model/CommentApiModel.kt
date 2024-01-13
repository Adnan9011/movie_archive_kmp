package com.moviearchive.data.source.api.model

import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable

@Serializable
data class CommentApiModel(
    @SerialName("comment_id") val id: Int,
    val title: String,
    @SerialName("movie_id") val movieId: Int
)