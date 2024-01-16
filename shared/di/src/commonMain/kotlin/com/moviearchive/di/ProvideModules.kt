package com.moviearchive.di

import com.moviearchive.core.platform.AppContext
import com.moviearchive.data.di.dataModule
import com.moviearchive.data.di.platformModule
import com.moviearchive.domain.di.domainModule
import com.moviearchive.feature.di.featureModule
import org.koin.dsl.module

object ProvideModules {

    fun getModules(appContext: AppContext) = module {
        includes(
            dataModule,
            platformModule(appContext),
            domainModule,
            featureModule
        )
    }
}