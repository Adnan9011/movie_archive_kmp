package com.moviearchive.data.repository

import com.moviearchive.core.Error
import com.moviearchive.core.Result
import com.moviearchive.core.map
import com.moviearchive.data.model.CelebrityDataModel
import com.moviearchive.data.model.PagingDataModel
import com.moviearchive.data.model.toData
import com.moviearchive.data.model.toTable
import com.moviearchive.data.source.api.api.ApiService
import com.moviearchive.data.source.db.dao.CelebrityDao
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.catch
import kotlinx.coroutines.flow.map

class CelebrityRepositoryImpl(
    val api: ApiService,
    val dao: CelebrityDao
) : CelebrityRepository {

    override fun getFavorites(): Flow<Result<List<CelebrityDataModel>, Error>> =
        dao.getAll().map { list ->
            Result.Success(list.map { it.toData() })
        }

    override suspend fun favorite(celebrity: CelebrityDataModel) {
        dao.insert(celebrity.toTable())
    }

    override suspend fun delete(celebrityId: String) {
        dao.delete(celebrityId)
    }

    override fun popularCelebrities(): Flow<Result<PagingDataModel<CelebrityDataModel>, Error>> =
        api.popularCelebrities().map { result ->
            result.map { it.data!!.toData() }
        }.catch { throwable ->
            Result.Failure(Error(message = throwable.message ?: "", throwable = throwable))
        }
}