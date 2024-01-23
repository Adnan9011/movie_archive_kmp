package com.moviearchive.domain.model

import com.moviearchive.data.model.WeekTopDataModel

data class WeekTopDomainModel(
    val id: String,
    val title: String,
    val image: String,
    val rate: Double,
    val duration: Long
)

internal fun WeekTopDataModel.toDomain() = WeekTopDomainModel(
    id = id,
    title = title,
    image = image,
    rate = rate,
    duration = duration
)