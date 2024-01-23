package com.moviearchive.domain.usecase

import com.moviearchive.core.Error
import com.moviearchive.core.Result
import com.moviearchive.core.map
import com.moviearchive.data.repository.MovieRepository
import com.moviearchive.domain.model.CelebritiesDomainModel
import com.moviearchive.domain.model.PagingDomainModel
import com.moviearchive.domain.model.toDomain
import com.moviearchive.domain.util.UseCaseNoInput
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.map

class GetPopularCelebritiesUseCase internal constructor(
    private val repository: MovieRepository
) : UseCaseNoInput<Flow<Result<PagingDomainModel<CelebritiesDomainModel>, Error>>> {
    override suspend fun invoke(): Flow<Result<PagingDomainModel<CelebritiesDomainModel>, Error>> {
        return repository.popularCelebrities().map { result ->
            result.map { it.toDomain() }
        }
    }
}
