package com.moviearchive.app

import android.app.Application
import androidx.compose.runtime.Composable
import com.moviearchive.navigation.App

@Composable
fun MainView(application: Application) {
    App(application)
}