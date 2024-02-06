package com.moviearchive.domain.usecase.home

import com.moviearchive.data.repository.HomeRepository
import com.moviearchive.domain.util.UseCaseNoInput
import kotlinx.coroutines.flow.Flow

class GetFavoriteStatusUseCase internal constructor(
    private val repository: HomeRepository
) : UseCaseNoInput<Flow<Boolean>> {
    override suspend fun invoke(): Flow<Boolean> =
        repository.getFavoriteStatus()
}