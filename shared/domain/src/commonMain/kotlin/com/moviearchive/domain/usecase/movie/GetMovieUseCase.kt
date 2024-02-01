package com.moviearchive.domain.usecase.movie

import com.moviearchive.core.Error
import com.moviearchive.core.Result
import com.moviearchive.core.map
import com.moviearchive.data.model.MovieDataModel
import com.moviearchive.data.repository.MovieRepository
import com.moviearchive.domain.model.MovieDomainModel
import com.moviearchive.domain.model.toDomain
import com.moviearchive.domain.util.UseCase
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.firstOrNull
import kotlinx.coroutines.flow.map

class GetMovieUseCase internal constructor(
    private val repository: MovieRepository
) : UseCase<String, Flow<Result<MovieDomainModel, Error>>> {
    override suspend fun invoke(id: String): Flow<Result<MovieDomainModel, Error>> {
        val favoriteMovie = repository.getFavorite(id).firstOrNull()

        return repository.get(id).map { result ->
            result.map { movie ->
                movie.toDomain(
                    isFavorite = if (favoriteMovie is Result.Success) {
                        findFavoriteById(
                            favoriteMovie = favoriteMovie.value,
                            id = movie.id
                        )
                    } else false
                )
            }
        }
    }

    private fun findFavoriteById(
        favoriteMovie: MovieDataModel,
        id: String
    ): Boolean =
        favoriteMovie.id == id
}
