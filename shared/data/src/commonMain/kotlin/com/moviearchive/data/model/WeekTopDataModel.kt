package com.moviearchive.data.model

import com.moviearchive.data.source.api.model.WeekTopApiModel
import com.moviearchive.sqldelight.WeekTopTable

data class WeekTopDataModel(
    val id: String,
    val title: String,
    val image: String,
    val rate: Double,
    val duration: Long
)

internal fun WeekTopApiModel.toData() = WeekTopDataModel(
    id = id,
    title = title.text,
    image = image.url,
    rate = rate.value,
    duration = length?.seconds ?: 0
)

internal fun WeekTopTable.toData() = WeekTopDataModel(
    id = id,
    title = title,
    image = image,
    rate = rate,
    duration = duration
)

internal fun WeekTopDataModel.toTable() = WeekTopTable(
    id = id,
    title = title,
    image = image,
    rate = rate,
    duration = duration
)