package com.moviearchive.domain.usecase

import com.moviearchive.core.Error
import com.moviearchive.core.Result
import com.moviearchive.core.map
import com.moviearchive.data.repository.MovieRepository
import com.moviearchive.domain.model.MovieDomainModel
import com.moviearchive.domain.model.toDomain
import com.moviearchive.domain.util.UseCaseNoInput
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.map

class GetAndStoreAllMovieUseCase internal constructor(
    private val repository: MovieRepository
) : UseCaseNoInput<Flow<Result<List<MovieDomainModel>, Error>>> {
    override suspend fun invoke(): Flow<Result<List<MovieDomainModel>, Error>> {
        repository.getFromApi()
        return repository.getAll().map { result ->
            result.map { list ->
                list.map { it.toDomain() }
            }
        }
    }
}
