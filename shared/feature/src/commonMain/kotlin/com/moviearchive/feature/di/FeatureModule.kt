package com.moviearchive.feature.di

import com.moviearchive.feature.presentation.celebrity.CelebrityViewModel
import com.moviearchive.feature.presentation.detail.DetailViewModel
import com.moviearchive.feature.presentation.home.HomeViewModel
import com.moviearchive.feature.presentation.splash.SplashViewModel
import org.koin.dsl.module

val featureModule = module {
    viewModelDefinition { SplashViewModel() }
    viewModelDefinition { HomeViewModel(get(), get(), get(), get(), get(), get()) }
    viewModelDefinition { DetailViewModel(get(), get()) }
    viewModelDefinition { CelebrityViewModel(get()) }
}