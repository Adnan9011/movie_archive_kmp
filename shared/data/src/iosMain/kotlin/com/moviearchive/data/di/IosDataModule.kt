package com.moviearchive.data.di

import com.moviearchive.DatabaseSource
import com.moviearchive.data.source.db.DriverFactory
import org.koin.dsl.module

val iosDataModule = module {
    single {

        val driverFactory = DriverFactory()
        val sqlDriver = driverFactory.createDriver()
        DatabaseSource(sqlDriver)
    }
}