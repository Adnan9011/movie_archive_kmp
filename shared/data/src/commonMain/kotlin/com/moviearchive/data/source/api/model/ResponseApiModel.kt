package com.moviearchive.data.source.api.model

import com.moviearchive.data.source.api.util.MessageStatus
import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable

@Serializable
data class ResponseApiModel<T>(
    @SerialName("status") val status: Boolean,
    @SerialName("message") val message: MessageStatus,
    @SerialName("data") val data: List<T>
)