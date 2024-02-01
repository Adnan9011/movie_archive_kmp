package com.moviearchive.data.model

import com.moviearchive.data.source.api.model.CelebrityApiModel
import com.moviearchive.data.source.api.model.PagingApiModel

data class PagingDataModel<T>(
    val hasNextPage: Boolean,
    val endCursor: String,
    val list: List<T>
)

internal fun PagingApiModel<CelebrityApiModel>.toData() = PagingDataModel<CelebrityDataModel>(
    hasNextPage = page.hasNext,
    endCursor = page.cursor,
    list = list.map { it.toData() }
)