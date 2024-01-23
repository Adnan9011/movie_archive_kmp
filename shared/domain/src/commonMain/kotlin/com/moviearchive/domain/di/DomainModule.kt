package com.moviearchive.domain.di

import com.moviearchive.core.Error
import com.moviearchive.core.Result
import com.moviearchive.domain.model.CelebritiesDomainModel
import com.moviearchive.domain.model.MovieDomainModel
import com.moviearchive.domain.model.PagingDomainModel
import com.moviearchive.domain.model.WeekTopDomainModel
import com.moviearchive.domain.usecase.GetMovieUseCase
import com.moviearchive.domain.usecase.GetPopularCelebritiesUseCase
import com.moviearchive.domain.usecase.GetSearchMovieUseCase
import com.moviearchive.domain.usecase.GetWeekTopTenMoviesUseCase
import com.moviearchive.domain.util.UseCase
import com.moviearchive.domain.util.UseCaseNoInput
import kotlinx.coroutines.flow.Flow
import org.koin.core.module.dsl.bind
import org.koin.core.module.dsl.singleOf
import org.koin.dsl.module

val domainModule = module {
    singleOf(::GetMovieUseCase) { bind<UseCase<String, Flow<Result<MovieDomainModel, Error>>>>() }
    singleOf(::GetPopularCelebritiesUseCase) { bind<UseCaseNoInput<Flow<Result<PagingDomainModel<CelebritiesDomainModel>, Error>>>>() }
    singleOf(::GetSearchMovieUseCase) { bind<UseCase<String, Flow<Result<List<MovieDomainModel>, Error>>>>() }
    singleOf(::GetWeekTopTenMoviesUseCase) { bind<UseCaseNoInput<Flow<Result<List<WeekTopDomainModel>, Error>>>>() }
}