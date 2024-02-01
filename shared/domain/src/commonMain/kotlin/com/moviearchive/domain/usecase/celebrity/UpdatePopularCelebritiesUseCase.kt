package com.moviearchive.domain.usecase.celebrity

import com.moviearchive.data.repository.CelebrityRepository
import com.moviearchive.domain.model.CelebrityDomainModel
import com.moviearchive.domain.model.toData
import com.moviearchive.domain.util.UseCase

class UpdatePopularCelebritiesUseCase internal constructor(
    private val repository: CelebrityRepository
) : UseCase<UpdatePopularCelebritiesUseCase.UpdateModel, Unit> {
    override suspend fun invoke(input: UpdateModel) {
        if (input.isFavorite)
            repository.favorite(input.celebrity.toData())
        else
            repository.delete(input.celebrity.id)
    }

    data class UpdateModel(
        val isFavorite: Boolean,
        val celebrity: CelebrityDomainModel
    )
}
