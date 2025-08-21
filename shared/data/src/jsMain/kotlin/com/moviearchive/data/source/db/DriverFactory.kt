package com.moviearchive.data.source.db

import app.cash.sqldelight.db.SqlDriver

actual class DriverFactory {
    actual fun createDriver(): SqlDriver {
        // TODO: provide SQLDelight driver using IndexedDB or Web SQL
        throw NotImplementedError("JS driver not implemented")
    }
}
