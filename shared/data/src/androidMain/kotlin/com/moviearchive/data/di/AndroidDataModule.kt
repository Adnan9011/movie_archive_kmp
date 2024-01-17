package com.moviearchive.data.di

import com.moviearchive.DatabaseSource
import com.moviearchive.core.platform.AppContext
import com.moviearchive.data.source.db.DriverFactory
import org.koin.core.module.Module
import org.koin.dsl.module

actual fun platformModule(appContext: AppContext): Module = module {
    single {

        val driverFactory = DriverFactory(
            appContext
        )
        val sqlDriver = driverFactory.createDriver()
        DatabaseSource(sqlDriver)
    }
}