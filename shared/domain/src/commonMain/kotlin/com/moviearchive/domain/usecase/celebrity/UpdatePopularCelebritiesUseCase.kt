package com.moviearchive.domain.usecase.celebrity

import com.moviearchive.data.repository.CelebrityRepository
import com.moviearchive.domain.model.CelebrityDomainModel
import com.moviearchive.domain.model.toData
import com.moviearchive.domain.util.UseCase

class UpdatePopularCelebritiesUseCase internal constructor(
    private val repository: CelebrityRepository
) : UseCase<CelebrityDomainModel, Unit> {
    override suspend fun invoke(celebrity: CelebrityDomainModel) {
        repository.favorite(celebrity.toData())
    }
}
