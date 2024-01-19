package com.moviearchive.data.source.api.model

import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable

@Serializable
data class WeekTopApiModel(
    @SerialName("id") val id: String,
    @SerialName("titleText") val title: TitleApiModel,
    @SerialName("primaryImage") val image: ImageApiModel,
    @SerialName("ratingsSummary") val rate: RateApiModel,
    @SerialName("titleRuntime") val length: LengthApiModel
)