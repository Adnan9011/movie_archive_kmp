package com.moviearchive.data.di

import com.moviearchive.core.platform.AppContext
import com.moviearchive.data.source.datastore.dataStore
import org.koin.core.module.Module
import org.koin.dsl.module

actual fun platformModule(appContext: AppContext): Module = module {
    // TODO: add database driver when available
    single { dataStore() }
}
