package com.moviearchive.data.model

import com.moviearchive.data.source.api.model.CelebrityApiModel
import com.moviearchive.sqldelight.CelebrityTable

data class CelebrityDataModel(
    var id: String,
    val name: String,
    val image: String
)

internal fun CelebrityApiModel.toData() = CelebrityDataModel(
    id = id,
    name = name.text,
    image = image.url
)

internal fun CelebrityTable.toData() = CelebrityDataModel(
    id = id,
    name = name,
    image = image
)

internal fun CelebrityDataModel.toTable() = CelebrityTable(
    id = id,
    name = name,
    image = image
)