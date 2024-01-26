package com.moviearchive.domain.usecase.celebrity

import com.moviearchive.core.Error
import com.moviearchive.core.Result
import com.moviearchive.core.map
import com.moviearchive.data.repository.CelebrityRepository
import com.moviearchive.domain.model.CelebrityDomainModel
import com.moviearchive.domain.model.toDomain
import com.moviearchive.domain.util.UseCaseNoInput
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.map

class GetFavoriteCelebritiesUseCase internal constructor(
    private val repository: CelebrityRepository
) : UseCaseNoInput<Flow<Result<List<CelebrityDomainModel>, Error>>> {
    override suspend fun invoke(): Flow<Result<List<CelebrityDomainModel>, Error>> {
        return repository.getFavorites().map { result ->
            result.map { list ->
                list.map { it.toDomain() }
            }
        }
    }
}