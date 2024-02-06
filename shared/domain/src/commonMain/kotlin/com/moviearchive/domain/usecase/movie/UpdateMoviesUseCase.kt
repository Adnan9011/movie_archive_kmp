package com.moviearchive.domain.usecase.movie

import com.moviearchive.data.repository.MovieRepository
import com.moviearchive.domain.model.MovieDomainModel
import com.moviearchive.domain.model.toData
import com.moviearchive.domain.util.UseCase

class UpdateMoviesUseCase internal constructor(
    private val repository: MovieRepository
) : UseCase<UpdateMoviesUseCase.UpdateModel, Unit> {
    override suspend fun invoke(input: UpdateModel) {
        if (input.isFavorite)
            repository.favorite(input.movie.toData())
        else
            repository.delete(input.movie.id)
    }

    data class UpdateModel(
        val isFavorite: Boolean,
        val movie: MovieDomainModel
    )
}