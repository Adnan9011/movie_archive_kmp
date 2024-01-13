package com.moviearchive.data.source.db

import app.cash.sqldelight.db.SqlDriver
import app.cash.sqldelight.driver.native.NativeSqliteDriver
import com.moviearchive.DatabaseSource
import com.moviearchive.data.source.db.util.Constant.DATABASE_NAME

actual class DriverFactory {
    actual fun createDriver(): SqlDriver {
        return NativeSqliteDriver(
            DatabaseSource.Schema,
            DATABASE_NAME
        )
    }
}