package com.moviearchive.data.di

import com.moviearchive.DatabaseSource
import com.moviearchive.data.source.db.DriverFactory
import org.koin.android.ext.koin.androidContext
import org.koin.dsl.module

val androidDataModule = module {
    single {

        val driverFactory = DriverFactory(
            androidContext()
        )
        val sqlDriver = driverFactory.createDriver()
        DatabaseSource(sqlDriver)
    }
}