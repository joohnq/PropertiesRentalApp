package com.joohnq.propertiesrentalapp.view.activities.ui.theme

import android.app.Activity
import androidx.compose.foundation.isSystemInDarkTheme
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.darkColorScheme
import androidx.compose.material3.lightColorScheme
import androidx.compose.runtime.Composable
import androidx.compose.runtime.SideEffect
import androidx.compose.ui.graphics.toArgb
import androidx.compose.ui.platform.LocalView
import androidx.core.view.WindowCompat
import com.joohnq.propertiesrentalapp.view.theme.GrayFCFCFC

private val DarkColorScheme = darkColorScheme(
    primary = Purple80,
    secondary = PurpleGrey80,
    tertiary = Pink80,
    background = GrayFCFCFC,
    surfaceContainer = GrayFCFCFC,
    surface = GrayFCFCFC
)

private val LightColorScheme = lightColorScheme(
    primary = Purple40,
    secondary = PurpleGrey40,
    tertiary = Pink40,
    background = GrayFCFCFC,
    surfaceContainer = GrayFCFCFC,
    surface = GrayFCFCFC,
    surfaceVariant = GrayFCFCFC,
    surfaceTint = GrayFCFCFC,
    surfaceBright = GrayFCFCFC,
    surfaceContainerHigh = GrayFCFCFC,
    surfaceContainerHighest = GrayFCFCFC,
    surfaceContainerLow = GrayFCFCFC,
    surfaceContainerLowest = GrayFCFCFC,
    surfaceDim = GrayFCFCFC,
)

@Composable
fun PropertiesRentalAppTheme(
    darkTheme: Boolean = isSystemInDarkTheme(),
    content: @Composable () -> Unit
) {
    val colorScheme = when {
        darkTheme -> DarkColorScheme
        else -> LightColorScheme
    }
    val view = LocalView.current
    if (!view.isInEditMode) {
        SideEffect {
            val window = (view.context as Activity).window
            window.statusBarColor = colorScheme.primary.toArgb()
            WindowCompat.getInsetsController(window, view).isAppearanceLightStatusBars = !darkTheme
            window.statusBarColor = GrayFCFCFC.toArgb()
            window.navigationBarColor = GrayFCFCFC.toArgb()
        }
    }

    MaterialTheme(
        colorScheme = colorScheme,
        typography = Typography,
        content = content
    )
}