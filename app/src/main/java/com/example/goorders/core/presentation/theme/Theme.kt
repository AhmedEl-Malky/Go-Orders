package com.example.goorders.core.presentation.theme

import android.app.Activity
import android.os.Build
import androidx.compose.foundation.isSystemInDarkTheme
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.darkColorScheme
import androidx.compose.material3.dynamicDarkColorScheme
import androidx.compose.material3.dynamicLightColorScheme
import androidx.compose.material3.lightColorScheme
import androidx.compose.runtime.Composable
import androidx.compose.ui.platform.LocalContext
private val DarkColorScheme = darkColorScheme(
    background = BackgroundDark,
    onBackground = ForegroundDark,
    surface = CardDark,
    onSurface = CardForegroundDark,
    surfaceVariant = InputDark,
    primary = PrimaryDark,
    onPrimary = PrimaryForeGroundDark,
    primaryContainer = PrimaryContainerDark,
    onPrimaryContainer = OnPrimaryContainerDark,
    surfaceContainerLow = Chart_2Dark,
    surfaceContainerHigh = Chart_1Dark,
    secondary = SecondaryDark,
    onSecondary = SecondaryForeGroundDark,
    tertiary = MutedDark,
    onTertiary = MutedForeGroundDark,
    error = DestructiveDark,
    outline = BorderDark,
    outlineVariant = CartPrice
)

private val LightColorScheme = lightColorScheme(
    background = BackgroundLight,
    onBackground = ForegroundLight,
    surface = CardLight,
    onSurface = CardForegroundLight,
    surfaceVariant = InputLight,
    primary = PrimaryLight,
    onPrimary = PrimaryForeGroundLight,
    primaryContainer = PrimaryContainerLight,
    onPrimaryContainer = BackgroundLight,
    surfaceContainerLow = Chart_2Light,
    surfaceContainerHigh = Chart_1Light,
    secondary = SecondaryLight,
    onSecondary = SecondaryForeGroundLight,
    tertiary = MutedLight,
    onTertiary = MutedForeGroundLight,
    error = DestructiveLight,
    outline = BorderLight,
    outlineVariant = CartPrice
)

@Composable
fun GoOrdersTheme(
    darkTheme: Boolean = isSystemInDarkTheme(),
    // Dynamic color is available on Android 12+
    dynamicColor: Boolean = false,
    content: @Composable () -> Unit
) {
    val colorScheme = when {
        dynamicColor && Build.VERSION.SDK_INT >= Build.VERSION_CODES.S -> {
            val context = LocalContext.current
            if (darkTheme) dynamicDarkColorScheme(context) else dynamicLightColorScheme(context)
        }

        darkTheme -> DarkColorScheme
        else -> LightColorScheme
    }

    MaterialTheme(
        colorScheme = colorScheme,
        typography = Typography,
        content = content
    )
}