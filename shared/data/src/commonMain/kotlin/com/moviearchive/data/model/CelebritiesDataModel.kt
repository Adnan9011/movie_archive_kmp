package com.moviearchive.data.model

import com.moviearchive.data.source.api.model.CelebritiesApiModel

data class CelebritiesDataModel(
    val id: String,
    val name: String,
    val image: String
)

internal fun CelebritiesApiModel.toData() = CelebritiesDataModel(
    id = id,
    name = name.text,
    image = image.url
)