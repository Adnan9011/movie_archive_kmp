package com.moviearchive.feature.model

import com.moviearchive.domain.model.CelebrityDomainModel

data class CelebrityUiModel(
    val id: String,
    val name: String,
    val image: String,
    val isFavorite: Boolean
)

internal fun CelebrityDomainModel.toUi() = CelebrityUiModel(
    id = id,
    name = name,
    image = image,
    isFavorite = isFavorite
)

internal fun CelebrityUiModel.toDomain() = CelebrityDomainModel(
    id = id,
    name = name,
    image = image,
    isFavorite = isFavorite
)