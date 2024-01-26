package com.moviearchive.domain.di

import com.moviearchive.core.Error
import com.moviearchive.core.Result
import com.moviearchive.domain.model.CelebrityDomainModel
import com.moviearchive.domain.model.MovieDomainModel
import com.moviearchive.domain.model.PagingDomainModel
import com.moviearchive.domain.model.WeekTopDomainModel
import com.moviearchive.domain.usecase.celebrity.GetFavoriteCelebritiesUseCase
import com.moviearchive.domain.usecase.celebrity.GetPopularCelebritiesUseCase
import com.moviearchive.domain.usecase.celebrity.UpdatePopularCelebritiesUseCase
import com.moviearchive.domain.usecase.movie.GetMovieUseCase
import com.moviearchive.domain.usecase.movie.GetSearchMovieUseCase
import com.moviearchive.domain.usecase.weekTopTen.GetFavoriteWeekTopTenMoviesUseCase
import com.moviearchive.domain.usecase.weekTopTen.GetWeekTopTenMoviesUseCase
import com.moviearchive.domain.usecase.weekTopTen.UpdateWeekTopTenMoviesUseCase
import com.moviearchive.domain.util.UseCase
import com.moviearchive.domain.util.UseCaseNoInput
import kotlinx.coroutines.flow.Flow
import org.koin.core.module.dsl.bind
import org.koin.core.module.dsl.singleOf
import org.koin.dsl.module

val domainModule = module {
    singleOf(::GetMovieUseCase) { bind<UseCase<String, Flow<Result<MovieDomainModel, Error>>>>() }
    singleOf(::GetSearchMovieUseCase) { bind<UseCase<String, Flow<Result<List<MovieDomainModel>, Error>>>>() }

    singleOf(::GetPopularCelebritiesUseCase) { bind<UseCaseNoInput<Flow<Result<PagingDomainModel<CelebrityDomainModel>, Error>>>>() }
    singleOf(::UpdatePopularCelebritiesUseCase) { bind<UseCase<CelebrityDomainModel, Unit>>() }
    singleOf(::GetFavoriteCelebritiesUseCase) { bind<UseCaseNoInput<Flow<Result<List<CelebrityDomainModel>, Error>>>>() }

    singleOf(::GetWeekTopTenMoviesUseCase) { bind<UseCaseNoInput<Flow<Result<List<WeekTopDomainModel>, Error>>>>() }
    singleOf(::UpdateWeekTopTenMoviesUseCase) { bind<UseCase<WeekTopDomainModel, Unit>>() }
    singleOf(::GetFavoriteWeekTopTenMoviesUseCase) { bind<UseCaseNoInput<Flow<Result<List<WeekTopDomainModel>, Error>>>>() }
}