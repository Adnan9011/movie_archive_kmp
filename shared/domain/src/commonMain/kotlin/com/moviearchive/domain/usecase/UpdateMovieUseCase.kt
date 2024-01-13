package com.moviearchive.domain.usecase

import com.moviearchive.data.repository.MovieRepository
import com.moviearchive.domain.model.MovieDomainModel
import com.moviearchive.domain.model.toData
import com.moviearchive.domain.util.UseCase

class UpdateMovieUseCase internal constructor(
    private val repository: MovieRepository
) : UseCase<MovieDomainModel, Unit> {
    override suspend fun invoke(movie: MovieDomainModel) =
        repository.update(movie.toData())
}