package com.moviearchive.ui.theme

import androidx.compose.material3.darkColorScheme
import androidx.compose.material3.lightColorScheme
import com.moviearchive.ui.theme.Colors.Pink40
import com.moviearchive.ui.theme.Colors.Pink80
import com.moviearchive.ui.theme.Colors.Purple40
import com.moviearchive.ui.theme.Colors.Purple80
import com.moviearchive.ui.theme.Colors.PurpleGrey40
import com.moviearchive.ui.theme.Colors.PurpleGrey80

public val DarkColorScheme = darkColorScheme(
    primary = Purple80,
    secondary = PurpleGrey80,
    tertiary = Pink80
)

public val LightColorScheme = lightColorScheme(
    primary = Purple40,
    secondary = PurpleGrey40,
    tertiary = Pink40

    /* Other default colors to override
    background = Color(0xFFFFFBFE),
    surface = Color(0xFFFFFBFE),
    onPrimary = Color.White,
    onSecondary = Color.White,
    onTertiary = Color.White,
    onBackground = Color(0xFF1C1B1F),
    onSurface = Color(0xFF1C1B1F),
    */
)