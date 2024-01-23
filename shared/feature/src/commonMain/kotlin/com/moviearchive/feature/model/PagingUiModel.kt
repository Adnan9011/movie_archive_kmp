package com.moviearchive.feature.model

import com.moviearchive.domain.model.CelebritiesDomainModel
import com.moviearchive.domain.model.PagingDomainModel

data class PagingUiModel<T>(
    val hasNextPage: Boolean,
    val endCursor: String,
    val list: List<T>
)

internal fun PagingDomainModel<CelebritiesDomainModel>.toUi() = PagingUiModel<CelebritiesUiModel>(
    hasNextPage = hasNextPage,
    endCursor = endCursor,
    list = list.map { it.toUi() }
)