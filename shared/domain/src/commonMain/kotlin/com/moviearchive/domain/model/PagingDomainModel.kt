package com.moviearchive.domain.model

import com.moviearchive.data.model.CelebritiesDataModel
import com.moviearchive.data.model.PagingDataModel

data class PagingDomainModel<T>(
    val hasNextPage: Boolean,
    val endCursor: String,
    val list: List<T>
)

internal fun PagingDataModel<CelebritiesDataModel>.toDomain() =
    PagingDomainModel<CelebritiesDomainModel>(
        hasNextPage = hasNextPage,
        endCursor = endCursor,
        list = list.map { it.toDomain() }
    )