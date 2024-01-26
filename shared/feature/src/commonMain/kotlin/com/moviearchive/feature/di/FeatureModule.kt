package com.moviearchive.feature.di

import com.moviearchive.feature.presentation.celebrity.CelebrityViewModel
import com.moviearchive.feature.presentation.detail.DetailViewModel
import com.moviearchive.feature.presentation.home.HomeViewModel
import org.koin.dsl.module

val featureModule = module {
    viewModelDefinition { HomeViewModel(get(), get(), get(), get()) }
    viewModelDefinition { DetailViewModel(get(), get()) }
    viewModelDefinition { CelebrityViewModel(get()) }
}