package com.moviearchive.feature.di

import com.moviearchive.feature.presentation.detail.DetailViewModel
import com.moviearchive.feature.presentation.home.HomeViewModel
import org.koin.dsl.module

val featureModule = module {
    viewModelDefinition { HomeViewModel(get(), get()) }
    viewModelDefinition { DetailViewModel(get(), get()) }
}