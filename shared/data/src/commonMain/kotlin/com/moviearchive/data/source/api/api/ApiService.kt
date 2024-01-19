package com.moviearchive.data.source.api.api

import com.moviearchive.core.Error
import com.moviearchive.core.Result
import com.moviearchive.data.source.api.model.CelebritiesApiModel
import com.moviearchive.data.source.api.model.MovieApiModel
import com.moviearchive.data.source.api.model.PagingApiModel
import com.moviearchive.data.source.api.model.ResponseApiModel
import com.moviearchive.data.source.api.model.ResponsePagingApiModel
import com.moviearchive.data.source.api.model.WeekTopApiModel
import kotlinx.coroutines.flow.Flow

interface ApiService {
    fun movie(id: String): Flow<Result<ResponseApiModel<MovieApiModel>, Error>>
    fun search(title: String): Flow<Result<ResponseApiModel<MovieApiModel>, Error>>
    fun weekTopTen(): Flow<Result<ResponseApiModel<WeekTopApiModel>, Error>>
    fun popularCelebrities(): Flow<Result<ResponsePagingApiModel<PagingApiModel<CelebritiesApiModel>>, Error>>
}