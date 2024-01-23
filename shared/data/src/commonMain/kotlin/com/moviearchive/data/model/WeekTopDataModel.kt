package com.moviearchive.data.model

import com.moviearchive.data.source.api.model.WeekTopApiModel

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