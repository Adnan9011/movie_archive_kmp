package com.moviearchive.domain.usecase.weekTopTen

import com.moviearchive.core.Error
import com.moviearchive.core.Result
import com.moviearchive.core.map
import com.moviearchive.data.repository.WeekTopRepository
import com.moviearchive.domain.model.WeekTopDomainModel
import com.moviearchive.domain.model.toDomain
import com.moviearchive.domain.util.UseCaseNoInput
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.map

class GetFavoriteWeekTopTenMoviesUseCase internal constructor(
    private val repository: WeekTopRepository
) : UseCaseNoInput<Flow<Result<List<WeekTopDomainModel>, Error>>> {
    override suspend fun invoke(): Flow<Result<List<WeekTopDomainModel>, Error>> {
        return repository.getFavorites().map { result ->
            result.map { list ->
                list.map { it.toDomain() }
            }
        }
    }
}