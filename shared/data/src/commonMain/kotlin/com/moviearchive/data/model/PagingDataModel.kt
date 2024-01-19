package com.moviearchive.data.model

import com.moviearchive.data.source.api.model.CelebritiesApiModel
import com.moviearchive.data.source.api.model.PagingApiModel

data class PagingDataModel<T>(
    val hasNextPage: Boolean,
    val endCursor: String,
    val list: List<T>
)

internal fun PagingApiModel<CelebritiesApiModel>.toData() = PagingDataModel<CelebritiesDataModel>(
    hasNextPage = page.hasNext,
    endCursor = page.cursor,
    list = list.map { it.toData() }
)