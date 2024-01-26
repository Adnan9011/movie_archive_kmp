package com.moviearchive.domain.model

import com.moviearchive.data.model.CelebrityDataModel
import com.moviearchive.data.model.PagingDataModel

data class PagingDomainModel<T>(
    val hasNextPage: Boolean,
    val endCursor: String,
    val list: List<T>
)

internal fun PagingDataModel<CelebrityDataModel>.toDomain() =
    PagingDomainModel<CelebrityDomainModel>(
        hasNextPage = hasNextPage,
        endCursor = endCursor,
        list = list.map { it.toDomain() }
    )