package com.moviearchive.domain.usecase.weekTopTen

import com.moviearchive.core.Error
import com.moviearchive.core.Result
import com.moviearchive.core.map
import com.moviearchive.data.model.MovieDataModel
import com.moviearchive.data.repository.MovieRepository
import com.moviearchive.data.repository.WeekTopRepository
import com.moviearchive.domain.model.WeekTopDomainModel
import com.moviearchive.domain.model.toDomain
import com.moviearchive.domain.util.UseCaseNoInput
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.firstOrNull
import kotlinx.coroutines.flow.map

class GetWeekTopTenMoviesUseCase internal constructor(
    private val repositoryWeekTop: WeekTopRepository,
    private val repositoryMovie: MovieRepository
) : UseCaseNoInput<Flow<Result<List<WeekTopDomainModel>, Error>>> {
    override suspend fun invoke(): Flow<Result<List<WeekTopDomainModel>, Error>> {
        val favoriteMovies = repositoryMovie.getFavorites().firstOrNull()

        return repositoryWeekTop.weekTopTen().map { result ->
            result.map { list ->
                list.map { weekTopDataModel ->
                    weekTopDataModel.toDomain(
                        isFavorite = if (favoriteMovies is Result.Success) {
                            findFavoriteById(
                                favoriteMovies = favoriteMovies.value,
                                id = weekTopDataModel.id
                            )
                        } else false
                    )
                }
            }
        }
    }

    private fun findFavoriteById(
        favoriteMovies: List<MovieDataModel>,
        id: String
    ): Boolean =
        favoriteMovies.any {
            it.id == id
        }
}
