package com.moviearchive.domain.usecase.weekTopTen

import com.moviearchive.data.repository.WeekTopRepository
import com.moviearchive.domain.model.WeekTopDomainModel
import com.moviearchive.domain.model.toData
import com.moviearchive.domain.util.UseCase

class UpdateWeekTopTenMoviesUseCase internal constructor(
    private val repository: WeekTopRepository
) : UseCase<WeekTopDomainModel, Unit> {
    override suspend fun invoke(weekTopTen: WeekTopDomainModel) {
        repository.favorite(weekTopTen.toData())
    }
}