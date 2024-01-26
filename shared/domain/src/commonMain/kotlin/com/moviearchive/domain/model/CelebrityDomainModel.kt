package com.moviearchive.domain.model

import com.moviearchive.data.model.CelebrityDataModel

data class CelebrityDomainModel(
    val id: String,
    val name: String,
    val image: String
)

internal fun CelebrityDataModel.toDomain() = CelebrityDomainModel(
    id = id,
    name = name,
    image = image
)

internal fun CelebrityDomainModel.toData() = CelebrityDataModel(
    id = id,
    name = name,
    image = image
)