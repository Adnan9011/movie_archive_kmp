package com.moviearchive.feature.model

import com.moviearchive.domain.model.MovieDomainModel

data class MovieUiModel(
    val id: String,
    val title: String,
    val image: String,
    val year: Int,
    private val stars: String
) {
    fun getStars(): List<String> = stars.split(",").map { it.trim() }
}

fun MovieDomainModel.toUi() = MovieUiModel(
    id = id,
    title = title,
    image = image,
    year = year,
    stars = stars
)