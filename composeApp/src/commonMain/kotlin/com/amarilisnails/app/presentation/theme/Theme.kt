package com.amarilisnails.app.presentation.theme

import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.lightColorScheme
import androidx.compose.runtime.Composable

private val AmarilisLightColorScheme = lightColorScheme(
    primary = AmarilisPrimary,
    secondary = AmarilisSecondary,
    background = AmarilisBackground,
    surface = AmarilisWhite,
    onPrimary = AmarilisWhite,
    onSecondary = AmarilisWhite,
    onBackground = AmarilisTextDark,
    onSurface = AmarilisTextDark,
    error = AmarilisError
)

@Composable
fun AmarilisNailsTheme(
    content: @Composable () -> Unit
) {
    MaterialTheme(
        colorScheme = AmarilisLightColorScheme,
        typography = AmarilisTypography,
        content = content
    )
}