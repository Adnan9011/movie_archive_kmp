package com.moviearchive.data.source.api.util

object Rout {
    const val BASE_URL = "https://imdb188.p.rapidapi.com/api/v1/"

    const val SEARCH = "searchIMDB"
    const val WEEK_TOP_TEN = "getWeekTop10"
    const val POPULAR_CELEBRITIES = "getPopularCelebrities"
}

object HEADER {
    const val KEY = "X-RapidAPI-Key"
    const val KEY_TOKEN = "READ README.MD FILE"
    const val HOST = "X-RapidAPI-Host"
    const val HOST_URL = "imdb188.p.rapidapi.com"
}

object PARAMETER {
    const val QUERY = "query"
}