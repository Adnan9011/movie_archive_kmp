package com.moviearchive.feature.model

import com.moviearchive.domain.model.CelebrityDomainModel

data class CelebrityUiModel(
    val id: String,
    val name: String,
    val image: String
)

internal fun CelebrityDomainModel.toUi() = CelebrityUiModel(
    id = id,
    name = name,
    image = image
)

internal fun CelebrityUiModel.toDomain() = CelebrityDomainModel(
    id = id,
    name = name,
    image = image
)