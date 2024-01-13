package com.moviearchive.data.source.api.model

import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable

@Serializable
data class MovieApiModel(
    val id: Int,
    val title: String,
    @SerialName("image_url") val imageUrl: String,
    @SerialName("num_comments") val numComments: Int,
    @SerialName("num_likes") val numLikes: Int,
    @SerialName("is_liked") val isLiked: Boolean
)
