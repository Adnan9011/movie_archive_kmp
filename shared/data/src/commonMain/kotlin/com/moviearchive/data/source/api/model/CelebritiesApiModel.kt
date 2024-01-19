package com.moviearchive.data.source.api.model

import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable

@Serializable
data class CelebritiesApiModel(
    @SerialName("id") val id: String,
    @SerialName("nameText") val name: TitleApiModel,
    @SerialName("primaryImage") val image: ImageApiModel
)