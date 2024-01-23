package com.moviearchive.domain.model

import com.moviearchive.data.model.CelebritiesDataModel

data class CelebritiesDomainModel(
    val id: String,
    val name: String,
    val image: String
)

internal fun CelebritiesDataModel.toDomain() = CelebritiesDomainModel(
    id = id,
    name = name,
    image = image
)