package com.moviearchive.data.source.db.dao

import app.cash.sqldelight.coroutines.asFlow
import app.cash.sqldelight.coroutines.mapToList
import com.moviearchive.DatabaseSource
import com.moviearchive.data.source.db.util.Constant
import com.moviearchive.sqldelight.CelebrityTable
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.IO
import kotlinx.coroutines.flow.Flow

class CelebrityDaoImpl(
    db: DatabaseSource
) : CelebrityDao {

    private val query = db.celebrity_tableQueries
    override fun getAll(): Flow<List<CelebrityTable>> =
        query.getAll()
            .asFlow()
            .mapToList(Dispatchers.IO)

    override suspend fun insert(celebrity: CelebrityTable) {
        query.transaction {
            afterRollback { throw Exception(Constant.THROW_QUERY_INSERT_CELEBRITY_EXCEPTION) }
            query.insert(
                id = celebrity.id,
                name = celebrity.name,
                image = celebrity.image
            )
        }
    }

    override suspend fun deleteAll() {
        query.deleteAll()
    }

    override suspend fun delete(celebrityId: String) {
        query.delete(celebrityId)
    }
}