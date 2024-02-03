package com.moviearchive.feature.presentation.splash

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.size
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Home
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import com.moviearchive.feature.util.SPLASH_DURATION_TIME
import com.moviearchive.feature.util.SPLASH_ICON_SIZE
import com.moviearchive.feature.util.SPLASH_TEXT_TITLE
import com.moviearchive.ui.theme.SplashTextStyle
import kotlinx.coroutines.delay

@Composable
fun SplashScreen(
    viewModel: SplashViewModel,
    onSplashEnd: () -> Unit
) {
    LaunchedEffect(key1 = null) {
        delay(SPLASH_DURATION_TIME)
        onSplashEnd()
    }

    Column(
        modifier = Modifier.fillMaxSize(),
        horizontalAlignment = Alignment.CenterHorizontally,
        verticalArrangement = Arrangement.Center
    ) {
        Icon(
            modifier = Modifier.size(SPLASH_ICON_SIZE),
            imageVector = Icons.Filled.Home,
            tint = MaterialTheme.colorScheme.primary,
            contentDescription = null
        )
        Text(
            text = SPLASH_TEXT_TITLE,
            style = SplashTextStyle
        )
    }
}