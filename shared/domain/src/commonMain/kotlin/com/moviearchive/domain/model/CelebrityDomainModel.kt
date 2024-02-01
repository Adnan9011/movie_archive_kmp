package com.moviearchive.domain.model

import com.moviearchive.data.model.CelebrityDataModel

data class CelebrityDomainModel(
    val id: String,
    val name: String,
    val image: String,
    val isFavorite: Boolean
)

internal fun CelebrityDataModel.toDomain(isFavorite: Boolean) = CelebrityDomainModel(
    id = id,
    name = name,
    image = image,
    isFavorite = isFavorite
)

internal fun CelebrityDomainModel.toData() = CelebrityDataModel(
    id = id,
    name = name,
    image = image
)