package com.moviearchive.domain.usecase.home

import com.moviearchive.data.repository.HomeRepository
import com.moviearchive.domain.util.UseCase

class UpdateFavoriteStatusUseCase internal constructor(
    private val repository: HomeRepository
) : UseCase<Boolean, Unit> {
    override suspend fun invoke(isFavorite: Boolean) {
        repository.updateFavoriteStatus(isFavorite)
    }
}