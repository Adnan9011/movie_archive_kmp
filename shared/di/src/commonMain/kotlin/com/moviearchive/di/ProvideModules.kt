package com.moviearchive.di

import com.moviearchive.data.di.dataModule
import com.moviearchive.domain.di.domainModule
import com.moviearchive.feature.di.featureModule
import org.koin.dsl.module

object ProvideModules {

    fun getModules() = module {
        includes(
            dataModule,
            domainModule,
            featureModule
        )
    }
}