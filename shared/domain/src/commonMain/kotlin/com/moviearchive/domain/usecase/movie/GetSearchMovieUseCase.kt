package com.moviearchive.domain.usecase.movie

import com.moviearchive.core.Error
import com.moviearchive.core.Result
import com.moviearchive.core.map
import com.moviearchive.data.repository.MovieRepository
import com.moviearchive.domain.model.MovieDomainModel
import com.moviearchive.domain.model.toDomain
import com.moviearchive.domain.util.UseCase
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.map

class GetSearchMovieUseCase internal constructor(
    private val repository: MovieRepository
) : UseCase<String, Flow<Result<List<MovieDomainModel>, Error>>> {
    override suspend fun invoke(id: String): Flow<Result<List<MovieDomainModel>, Error>> {
        return repository.search(id).map { result ->
            result.map { list ->
                list.map { it.toDomain(isFavorite = false) }
            }
        }
    }
}
