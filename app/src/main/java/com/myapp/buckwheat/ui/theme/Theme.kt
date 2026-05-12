package com.myapp.buckwheat.ui.theme

import android.app.Activity
import android.os.Build
import androidx.compose.foundation.isSystemInDarkTheme
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.darkColorScheme
import androidx.compose.material3.dynamicDarkColorScheme
import androidx.compose.material3.dynamicLightColorScheme
import androidx.compose.material3.lightColorScheme
import androidx.compose.runtime.Composable
import androidx.compose.runtime.SideEffect
import androidx.compose.ui.graphics.toArgb
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.platform.LocalView
import androidx.core.view.WindowCompat

private val LightColorScheme = lightColorScheme(
    primary = md_theme_light_primary,
    onPrimary = md_theme_light_onPrimary,
    primaryContainer = md_theme_light_primaryContainer,
    onPrimaryContainer = md_theme_light_onPrimaryContainer,
    secondary = md_theme_light_secondary,
    onSecondary = md_theme_light_onSecondary,
    secondaryContainer = md_theme_light_secondaryContainer,
    onSecondaryContainer = md_theme_light_onSecondaryContainer,
    tertiary = md_theme_light_tertiary,
    onTertiary = md_theme_light_onTertiary,
    tertiaryContainer = md_theme_light_tertiaryContainer,
    onTertiaryContainer = md_theme_light_onTertiaryContainer,
    error = md_theme_light_error,
    onError = md_theme_light_onError,
    errorContainer = md_theme_light_errorContainer,
    onErrorContainer = md_theme_light_onErrorContainer,
    background = md_theme_light_background,
    onBackground = md_theme_light_onBackground,
    surface = md_theme_light_surface,
    onSurface = md_theme_light_onSurface,
    surfaceVariant = md_theme_light_surfaceVariant,
    onSurfaceVariant = md_theme_light_onSurfaceVariant,
    outline = md_theme_light_outline,
    outlineVariant = md_theme_light_outlineVariant,
    inverseSurface = md_theme_light_inverseSurface,
    inverseOnSurface = md_theme_light_inverseOnSurface,
    inversePrimary = md_theme_light_inversePrimary,
    surfaceTint = md_theme_light_surfaceTint
)

private val AlabastaColorScheme = lightColorScheme(
    primary = alabasta_primary,
    onPrimary = alabasta_onPrimary,
    primaryContainer = alabasta_primaryContainer,
    onPrimaryContainer = alabasta_onPrimaryContainer,
    secondary = alabasta_secondary,
    onSecondary = alabasta_onSecondary,
    secondaryContainer = alabasta_secondaryContainer,
    onSecondaryContainer = alabasta_onSecondaryContainer,
    tertiary = alabasta_tertiary,
    onTertiary = alabasta_onTertiary,
    tertiaryContainer = alabasta_tertiaryContainer,
    onTertiaryContainer = alabasta_onTertiaryContainer,
    error = alabasta_error,
    onError = alabasta_onError,
    errorContainer = alabasta_errorContainer,
    onErrorContainer = alabasta_onErrorContainer,
    background = alabasta_background,
    onBackground = alabasta_onBackground,
    surface = alabasta_surface,
    onSurface = alabasta_onSurface,
    surfaceVariant = alabasta_surfaceVariant,
    onSurfaceVariant = alabasta_onSurfaceVariant,
    outline = alabasta_outline,
    outlineVariant = alabasta_outlineVariant,
    inverseSurface = alabasta_inverseSurface,
    inverseOnSurface = alabasta_inverseOnSurface,
    inversePrimary = alabasta_inversePrimary,
    surfaceTint = alabasta_surfaceTint
)

private val SkypieaLightColorScheme = lightColorScheme(
    primary = skypiea_light_primary,
    onPrimary = skypiea_light_onPrimary,
    primaryContainer = skypiea_light_primaryContainer,
    onPrimaryContainer = skypiea_light_onPrimaryContainer,
    secondary = skypiea_light_secondary,
    onSecondary = skypiea_light_onSecondary,
    secondaryContainer = skypiea_light_secondaryContainer,
    onSecondaryContainer = skypiea_light_onSecondaryContainer,
    tertiary = skypiea_light_tertiary,
    onTertiary = skypiea_light_onTertiary,
    tertiaryContainer = skypiea_light_tertiaryContainer,
    onTertiaryContainer = skypiea_light_onTertiaryContainer,
    error = skypiea_light_error,
    onError = skypiea_light_onError,
    errorContainer = skypiea_light_errorContainer,
    onErrorContainer = skypiea_light_onErrorContainer,
    background = skypiea_light_background,
    onBackground = skypiea_light_onBackground,
    surface = skypiea_light_surface,
    onSurface = skypiea_light_onSurface,
    surfaceVariant = skypiea_light_surfaceVariant,
    onSurfaceVariant = skypiea_light_onSurfaceVariant,
    outline = skypiea_light_outline,
    outlineVariant = skypiea_light_outlineVariant,
    inverseSurface = skypiea_light_inverseSurface,
    inverseOnSurface = skypiea_light_inverseOnSurface,
    inversePrimary = skypiea_light_inversePrimary,
    surfaceTint = skypiea_light_surfaceTint
)

private val SkypieaDarkColorScheme = darkColorScheme(
    primary = skypiea_dark_primary,
    onPrimary = skypiea_dark_onPrimary,
    primaryContainer = skypiea_dark_primaryContainer,
    onPrimaryContainer = skypiea_dark_onPrimaryContainer,
    secondary = skypiea_dark_secondary,
    onSecondary = skypiea_dark_onSecondary,
    secondaryContainer = skypiea_dark_secondaryContainer,
    onSecondaryContainer = skypiea_dark_onSecondaryContainer,
    tertiary = skypiea_dark_tertiary,
    onTertiary = skypiea_dark_onTertiary,
    tertiaryContainer = skypiea_dark_tertiaryContainer,
    onTertiaryContainer = skypiea_dark_onTertiaryContainer,
    error = skypiea_dark_error,
    onError = skypiea_dark_onError,
    errorContainer = skypiea_dark_errorContainer,
    onErrorContainer = skypiea_dark_onErrorContainer,
    background = skypiea_dark_background,
    onBackground = skypiea_dark_onBackground,
    surface = skypiea_dark_surface,
    onSurface = skypiea_dark_onSurface,
    surfaceVariant = skypiea_dark_surfaceVariant,
    onSurfaceVariant = skypiea_dark_onSurfaceVariant,
    outline = skypiea_dark_outline,
    outlineVariant = skypiea_dark_outlineVariant,
    inverseSurface = skypiea_dark_inverseSurface,
    inverseOnSurface = skypiea_dark_inverseOnSurface,
    inversePrimary = skypiea_dark_inversePrimary,
    surfaceTint = skypiea_dark_surfaceTint
)

private val DarkColorScheme = darkColorScheme(
    primary = md_theme_dark_primary,
    onPrimary = md_theme_dark_onPrimary,
    primaryContainer = md_theme_dark_primaryContainer,
    onPrimaryContainer = md_theme_dark_onPrimaryContainer,
    secondary = md_theme_dark_secondary,
    onSecondary = md_theme_dark_onSecondary,
    secondaryContainer = md_theme_dark_secondaryContainer,
    onSecondaryContainer = md_theme_dark_onSecondaryContainer,
    tertiary = md_theme_dark_tertiary,
    onTertiary = md_theme_dark_onTertiary,
    tertiaryContainer = md_theme_dark_tertiaryContainer,
    onTertiaryContainer = md_theme_dark_onTertiaryContainer,
    error = md_theme_dark_error,
    onError = md_theme_dark_onError,
    errorContainer = md_theme_dark_errorContainer,
    onErrorContainer = md_theme_dark_onErrorContainer,
    background = md_theme_dark_background,
    onBackground = md_theme_dark_onBackground,
    surface = md_theme_dark_surface,
    onSurface = md_theme_dark_onSurface,
    surfaceVariant = md_theme_dark_surfaceVariant,
    onSurfaceVariant = md_theme_dark_onSurfaceVariant,
    outline = md_theme_dark_outline,
    outlineVariant = md_theme_dark_outlineVariant,
    inverseSurface = md_theme_dark_inverseSurface,
    inverseOnSurface = md_theme_dark_inverseOnSurface,
    inversePrimary = md_theme_dark_inversePrimary,
    surfaceTint = md_theme_dark_surfaceTint
)

@Composable
fun BerryTheme(
    themeMode: String = "system",
    content: @Composable () -> Unit
) {
    val colorScheme = when (themeMode) {
        "alabasta" -> AlabastaColorScheme
        "skypiea" -> {
            if (isSystemInDarkTheme()) SkypieaDarkColorScheme else SkypieaLightColorScheme
        }
        "dark" -> {
            if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.S) {
                dynamicDarkColorScheme(LocalContext.current)
            } else DarkColorScheme
        }
        "light" -> {
            if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.S) {
                dynamicLightColorScheme(LocalContext.current)
            } else LightColorScheme
        }
        else -> { // "system"
            val dark = isSystemInDarkTheme()
            if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.S) {
                val context = LocalContext.current
                if (dark) dynamicDarkColorScheme(context) else dynamicLightColorScheme(context)
            } else {
                if (dark) DarkColorScheme else LightColorScheme
            }
        }
    }

    val darkTheme = when (themeMode) {
        "dark" -> true
        "light", "alabasta" -> false
        "skypiea" -> isSystemInDarkTheme()
        else -> isSystemInDarkTheme()
    }

    val view = LocalView.current
    if (!view.isInEditMode) {
        SideEffect {
            val window = (view.context as Activity).window
            window.statusBarColor = colorScheme.surface.toArgb()
            WindowCompat.getInsetsController(window, view).isAppearanceLightStatusBars = !darkTheme
        }
    }

    MaterialTheme(
        colorScheme = colorScheme,
        typography = BerryTypography,
        shapes = BerryShapes,
        content = content
    )
}
