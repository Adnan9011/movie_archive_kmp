package com.moviearchive.data.di

import com.moviearchive.data.repository.MovieRepository
import com.moviearchive.data.repository.MovieRepositoryImpl
import com.moviearchive.data.source.api.api.ApiService
import com.moviearchive.data.source.api.api.ApiServiceImpl
import com.moviearchive.data.source.api.util.CustomHttpLogger
import com.moviearchive.data.source.api.util.Rout
import com.moviearchive.data.source.datastore.DataStoreSource
import com.moviearchive.data.source.db.dao.CommentDao
import com.moviearchive.data.source.db.dao.CommentDaoImpl
import com.moviearchive.data.source.db.dao.MovieDao
import com.moviearchive.data.source.db.dao.MovieDaoImpl
import io.ktor.client.HttpClient
import io.ktor.client.plugins.DefaultRequest
import io.ktor.client.plugins.contentnegotiation.ContentNegotiation
import io.ktor.client.plugins.logging.LogLevel
import io.ktor.client.plugins.logging.Logging
import io.ktor.client.request.header
import io.ktor.http.ContentType
import io.ktor.http.HttpHeaders
import io.ktor.serialization.kotlinx.json.json
import kotlinx.coroutines.Dispatchers
import kotlinx.serialization.json.Json
import org.koin.dsl.module

val dataModule = module {
    single<MovieRepository> { MovieRepositoryImpl(api = get(), dao = get(), dataStore = get()) }

    single { DataStoreSource() }

    single {
        HttpClient() {
            install(Logging) {
                logger = CustomHttpLogger()
                level = LogLevel.ALL
            }
            install(DefaultRequest) {
                url(Rout.BASE_URL)
                header(HttpHeaders.ContentType, ContentType.Application.Json)
            }
            install(ContentNegotiation) {
                json(Json {
                    ignoreUnknownKeys = true
                })
            }
        }
    }
    single<MovieDao> { MovieDaoImpl(db = get()) }
    single<CommentDao> { CommentDaoImpl(db = get()) }

    single { Dispatchers.Default }
    single<ApiService> { ApiServiceImpl(httpClient = get()) }
}