package com.moviearchive.data.model

import com.moviearchive.data.source.api.model.MovieApiModel

data class MovieDataModel(
    val id: String,
    val title: String,
    val image: String,
    val year: Int,
    val stars: String
)

internal fun MovieApiModel.toData() = MovieDataModel(
    id = id,
    title = title,
    image = image,
    year = year,
    stars = stars
)