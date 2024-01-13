package com.moviearchive.data.source.db

import android.content.Context
import app.cash.sqldelight.db.SqlDriver
import app.cash.sqldelight.driver.android.AndroidSqliteDriver
import com.moviearchive.DatabaseSource
import com.moviearchive.data.source.db.util.Constant.DATABASE_NAME

actual class DriverFactory(private val context: Context) {
    actual fun createDriver(): SqlDriver {
        return AndroidSqliteDriver(
            DatabaseSource.Schema,
            context,
            DATABASE_NAME
        )
    }
}