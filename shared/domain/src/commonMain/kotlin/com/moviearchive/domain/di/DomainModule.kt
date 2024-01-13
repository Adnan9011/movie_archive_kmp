package com.moviearchive.domain.di

import com.moviearchive.core.Error
import com.moviearchive.core.Result
import com.moviearchive.domain.model.CommentDomainModel
import com.moviearchive.domain.model.MovieDomainModel
import com.moviearchive.domain.usecase.GetAndStoreAllMovieUseCase
import com.moviearchive.domain.usecase.GetCommentByIdUseCase
import com.moviearchive.domain.usecase.GetMovieUseCase
import com.moviearchive.domain.usecase.GetMoviesOrFavoriteMoviesUseCase
import com.moviearchive.domain.usecase.UpdateMovieUseCase
import com.moviearchive.domain.util.UseCase
import com.moviearchive.domain.util.UseCaseNoInput
import kotlinx.coroutines.flow.Flow
import org.koin.core.module.dsl.bind
import org.koin.core.module.dsl.singleOf
import org.koin.dsl.module

val domainModule = module {
    singleOf(::GetAndStoreAllMovieUseCase) { bind<UseCaseNoInput<Flow<Result<List<MovieDomainModel>, Error>>>>() }
    singleOf(::GetMoviesOrFavoriteMoviesUseCase) { bind<UseCase<Boolean, Flow<Result<List<MovieDomainModel>, Error>>>>() }
    singleOf(::GetMovieUseCase) { bind<UseCase<Int, Flow<Result<MovieDomainModel, Error>>>>() }
    singleOf(::GetCommentByIdUseCase) { bind<UseCase<Int, Flow<Result<List<CommentDomainModel>, Error>>>>() }
    singleOf(::UpdateMovieUseCase) { bind<UseCase<MovieDomainModel, Unit>>() }
}