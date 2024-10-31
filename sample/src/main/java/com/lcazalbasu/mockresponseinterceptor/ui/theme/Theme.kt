package com.lcazalbasu.mockresponseinterceptor.ui.theme

import androidx.compose.foundation.isSystemInDarkTheme
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.darkColorScheme
import androidx.compose.runtime.Composable

private val DarkColorScheme = darkColorScheme(
    primary = Gray_20,
    secondary = Core_Red,
    tertiary = Core_White,
    background = Gray_6,
    surface = Core_White,
    onPrimary = Core_Gray,
    onSecondary = Gray_20,
    onTertiary = Gray_15,
    onBackground = Gray_10,
//    onSurface = Gray_30,
    onError = Core_Red,
    error = Light_Red,
    onSurfaceVariant = Gray_40,
)

private val LightColorScheme = DarkColorScheme

@Composable
fun MockResponseInterceptorTheme(
    darkTheme: Boolean = isSystemInDarkTheme(),
    content: @Composable () -> Unit,
) {
    val colorScheme = when {
        darkTheme -> DarkColorScheme
        else -> LightColorScheme
    }

    MaterialTheme(
        colorScheme = colorScheme,
        typography = Typography,
        content = content,
    )
}
