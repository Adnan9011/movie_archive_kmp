package com.moviearchive.domain.model

import com.moviearchive.data.model.WeekTopDataModel

data class WeekTopDomainModel(
    val id: String,
    val title: String,
    val image: String,
    val rate: Double,
    val duration: Long,
    val isFavorite: Boolean
)

internal fun WeekTopDataModel.toDomain(isFavorite: Boolean) = WeekTopDomainModel(
    id = id,
    title = title,
    image = image,
    rate = rate,
    duration = duration,
    isFavorite = isFavorite
)

internal fun WeekTopDomainModel.toData() = WeekTopDataModel(
    id = id,
    title = title,
    image = image,
    rate = rate,
    duration = duration
)

fun MovieDomainModel.toWeekTop() = WeekTopDomainModel(
    id = id,
    title = title,
    image = image,
    rate = 0.0,
    duration = 0,
    isFavorite = isFavorite
)