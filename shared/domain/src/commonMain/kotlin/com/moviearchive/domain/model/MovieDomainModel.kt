package com.moviearchive.domain.model

import com.moviearchive.data.model.MovieDataModel

data class MovieDomainModel(
    val id: String,
    val title: String,
    val image: String,
    val year: Int,
    val stars: String
)

internal fun MovieDataModel.toDomain() = MovieDomainModel(
    id = id,
    title = title,
    image = image,
    year = year,
    stars = stars
)