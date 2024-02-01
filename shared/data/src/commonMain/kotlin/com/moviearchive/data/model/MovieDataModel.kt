package com.moviearchive.data.model

import com.moviearchive.data.source.api.model.MovieApiModel
import com.moviearchive.sqldelight.MovieTable

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

internal fun MovieTable.toData() = MovieDataModel(
    id = id,
    title = title,
    image = image,
    year = year.toInt(),
    stars = stars
)

internal fun MovieDataModel.toTable() = MovieTable(
    id = id,
    title = title,
    image = image,
    year = year.toLong(),
    stars = stars
)