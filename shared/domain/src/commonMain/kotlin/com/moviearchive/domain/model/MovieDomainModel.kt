package com.moviearchive.domain.model

import com.moviearchive.data.model.MovieDataModel

data class MovieDomainModel(
    val id: String,
    val title: String,
    val image: String,
    val year: Int,
    val stars: String,
    val isFavorite: Boolean
)

internal fun MovieDataModel.toDomain(isFavorite: Boolean) = MovieDomainModel(
    id = id,
    title = title,
    image = image,
    year = year,
    stars = stars,
    isFavorite = isFavorite
)

internal fun MovieDomainModel.toData() = MovieDataModel(
    id = id,
    title = title,
    image = image,
    year = year,
    stars = stars
)