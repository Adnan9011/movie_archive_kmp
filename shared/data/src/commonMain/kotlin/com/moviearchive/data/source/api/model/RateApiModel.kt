package com.moviearchive.data.source.api.model

import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable

@Serializable
data class RateApiModel(
    @SerialName("aggregateRating") val value: Double
)