package pt.iade.ei.cobuy.ui.theme

import androidx.compose.material3.*
import androidx.compose.runtime.Composable
import androidx.compose.ui.graphics.Color

@Composable
fun COBUYTheme(content: @Composable () -> Unit) {
    val colors = lightColorScheme(
        primary = Color(0xFF2E7D32),
        secondary = Color(0xFF81C784),
        background = Color(0xFFF1F8E9)
    )

    MaterialTheme(
        colorScheme = colors,
        typography = Typography(),
        content = content
    )
}
