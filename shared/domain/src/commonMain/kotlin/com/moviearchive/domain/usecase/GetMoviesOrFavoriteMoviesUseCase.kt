package com.moviearchive.domain.usecase

import com.moviearchive.core.Error
import com.moviearchive.core.Result
import com.moviearchive.core.map
import com.moviearchive.data.repository.MovieRepository
import com.moviearchive.domain.model.MovieDomainModel
import com.moviearchive.domain.model.toDomain
import com.moviearchive.domain.util.UseCase
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.map

class GetMoviesOrFavoriteMoviesUseCase internal constructor(
    private val repository: MovieRepository
) : UseCase<Boolean, Flow<Result<List<MovieDomainModel>, Error>>> {
    override suspend fun invoke(isFavorite: Boolean): Flow<Result<List<MovieDomainModel>, Error>> {
        return if (isFavorite) repository.getAllLiked().map { result ->
            result.map { list ->
                list.map { it.toDomain() }
            }
        } else repository.getAll().map { result ->
            result.map { list ->
                list.map { it.toDomain() }
            }
        }
    }
}
