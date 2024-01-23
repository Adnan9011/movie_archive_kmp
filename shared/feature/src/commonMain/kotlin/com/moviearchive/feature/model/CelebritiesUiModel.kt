package com.moviearchive.feature.model

import com.moviearchive.domain.model.CelebritiesDomainModel

data class CelebritiesUiModel(
    val id: String,
    val name: String,
    val image: String
)

internal fun CelebritiesDomainModel.toUi() = CelebritiesUiModel(
    id = id,
    name = name,
    image = image
)