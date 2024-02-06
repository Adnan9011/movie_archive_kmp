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
import com.moviearchive.domain.usecase.home.GetFavoriteStatusUseCase
import com.moviearchive.domain.usecase.home.UpdateFavoriteStatusUseCase
import com.moviearchive.domain.usecase.movie.GetFavoriteMoviesUseCase
import com.moviearchive.domain.usecase.movie.GetMovieUseCase
import com.moviearchive.domain.usecase.movie.GetSearchMovieUseCase
import com.moviearchive.domain.usecase.movie.UpdateMoviesUseCase
import com.moviearchive.domain.usecase.weekTopTen.GetWeekTopTenMoviesUseCase
import com.moviearchive.domain.util.UseCase
import com.moviearchive.domain.util.UseCaseNoInput
import kotlinx.coroutines.flow.Flow
import org.koin.core.module.dsl.bind
import org.koin.core.module.dsl.singleOf
import org.koin.dsl.module

val domainModule = module {
    singleOf(::GetFavoriteStatusUseCase) { bind<UseCaseNoInput<Flow<Boolean>>>() }
    singleOf(::UpdateFavoriteStatusUseCase) { bind<UseCase<Boolean, Unit>>() }

    singleOf(::GetMovieUseCase) { bind<UseCase<String, Flow<Result<MovieDomainModel, Error>>>>() }
    singleOf(::GetSearchMovieUseCase) { bind<UseCase<String, Flow<Result<List<MovieDomainModel>, Error>>>>() }

    singleOf(::GetPopularCelebritiesUseCase) { bind<UseCaseNoInput<Flow<Result<PagingDomainModel<CelebrityDomainModel>, Error>>>>() }
    singleOf(::UpdatePopularCelebritiesUseCase) { bind<UseCase<UpdatePopularCelebritiesUseCase.UpdateModel, Unit>>() }
    singleOf(::GetFavoriteCelebritiesUseCase) { bind<UseCaseNoInput<Flow<Result<List<CelebrityDomainModel>, Error>>>>() }

    singleOf(::GetWeekTopTenMoviesUseCase) { bind<UseCaseNoInput<Flow<Result<List<WeekTopDomainModel>, Error>>>>() }
    singleOf(::UpdateMoviesUseCase) { bind<UseCase<UpdateMoviesUseCase.UpdateModel, Unit>>() }
    singleOf(::GetFavoriteMoviesUseCase) { bind<UseCaseNoInput<Flow<Result<List<MovieDomainModel>, Error>>>>() }
}