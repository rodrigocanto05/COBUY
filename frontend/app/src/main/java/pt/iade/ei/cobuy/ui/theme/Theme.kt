package pt.iade.ei.cobuy.ui.theme

import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.darkColorScheme
import androidx.compose.material3.lightColorScheme
import androidx.compose.runtime.Composable

private val LightColors = lightColorScheme(
    primary = OrangePrimary,
    onPrimary = TextLight,
    background = BackgroundLight,
    onBackground = TextDark
)

private val DarkColors = darkColorScheme(
    primary = OrangePrimary,
    onPrimary = TextLight,
    background = BackgroundDark,
    onBackground = TextLight
)

@Composable
fun COBUYTheme(
    darkTheme: Boolean = false,
    content: @Composable () -> Unit
) {
    val colors = if (darkTheme) DarkColors else LightColors

    MaterialTheme(
        colorScheme = colors,
        typography = COBUYTypography,
        content = content
    )
}
