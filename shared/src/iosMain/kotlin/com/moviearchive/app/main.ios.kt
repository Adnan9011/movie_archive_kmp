package com.moviearchive.app

import androidx.compose.ui.window.ComposeUIViewController
import com.moviearchive.core.platform.AppContext
import com.moviearchive.di.App

fun MainViewController() = ComposeUIViewController { App(AppContext()) }
