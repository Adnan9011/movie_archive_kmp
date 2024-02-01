package com.moviearchive.feature.model

import com.moviearchive.domain.model.WeekTopDomainModel

data class WeekTopUiModel(
    val id: String,
    val title: String,
    val image: String,
    val rate: Double,
    private val duration: Long
) {
    fun getDuration() = "${duration / 3600}H ${(duration % 3600) / 60}M ${(duration % 60)}S"
}

internal fun WeekTopDomainModel.toUi() = WeekTopUiModel(
    id = id,
    title = title,
    image = image,
    rate = rate,
    duration = duration
)