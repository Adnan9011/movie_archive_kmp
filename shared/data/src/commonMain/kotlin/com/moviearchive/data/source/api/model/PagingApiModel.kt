package com.moviearchive.data.source.api.model

import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable

@Serializable
data class PagingApiModel<T>(
    @SerialName("pageInfo") val page: PageApiModel,
    @SerialName("list") val list: List<T>
) {
    @Serializable
    data class PageApiModel(
        @SerialName("hasNextPage") val hasNext: Boolean,
        @SerialName("endCursor") val cursor: String
    )
}