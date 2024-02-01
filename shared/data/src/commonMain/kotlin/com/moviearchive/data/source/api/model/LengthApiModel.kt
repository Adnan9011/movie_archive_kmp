package com.moviearchive.data.source.api.model

import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable

@Serializable
data class LengthApiModel(
    @SerialName("seconds") val seconds: Long
)