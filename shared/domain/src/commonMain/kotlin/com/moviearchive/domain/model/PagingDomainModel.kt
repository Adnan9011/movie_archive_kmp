package com.moviearchive.domain.model

import com.moviearchive.data.model.CelebrityDataModel
import com.moviearchive.data.model.PagingDataModel

data class PagingDomainModel<T>(
    val hasNextPage: Boolean,
    val endCursor: String,
    val list: List<T>
)

internal fun PagingDataModel<CelebrityDataModel>.toDomain(map: (CelebrityDataModel) -> CelebrityDomainModel) =
    PagingDomainModel(
        hasNextPage = hasNextPage,
        endCursor = endCursor,
        list = list.map { map(it) }
    )