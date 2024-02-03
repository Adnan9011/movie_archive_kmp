package com.moviearchive.feature.android.presentation.splash

import androidx.compose.runtime.Composable
import androidx.compose.ui.tooling.preview.Preview
import com.moviearchive.feature.presentation.splash.SplashScreen
import com.moviearchive.feature.presentation.splash.SplashViewModel

@Preview
@Composable
fun SplashScreenPreview() {
    SplashScreen(
        viewModel = SplashViewModel(),
        onSplashEnd = {}
    )
}