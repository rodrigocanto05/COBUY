package pt.iade.ei.cobuy.ui.components.topbar

import androidx.compose.foundation.layout.RowScope
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.automirrored.filled.ArrowBack
import androidx.compose.material3.CenterAlignedTopAppBar
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.Text
import androidx.compose.material3.TopAppBarDefaults
import androidx.compose.runtime.Composable
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.sp
import androidx.navigation.NavController
import androidx.navigation.compose.rememberNavController
import pt.iade.ei.cobuy.ui.theme.COBUYTheme
import pt.iade.ei.cobuy.ui.theme.OrangePrimary

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun CoBuyTopBar(
    title: String,
    navController: NavController,
    showBackButton: Boolean = true,
    actions: @Composable RowScope.() -> Unit = {}
) {
    CenterAlignedTopAppBar(
        title = { Text(title, color = Color.Black, fontWeight = FontWeight.SemiBold, fontSize = 18.sp) },
        navigationIcon = {
            if (showBackButton) {
                IconButton(onClick = { navController.popBackStack() }) {
                    Icon(
                        imageVector = Icons.AutoMirrored.Filled.ArrowBack,
                        contentDescription = "Voltar",
                        tint = OrangePrimary
                    )
                }
            }
        },
        actions = actions,
        colors = TopAppBarDefaults.centerAlignedTopAppBarColors(containerColor = Color.White)
    )
}
@Preview(showBackground = true)
@Composable
fun CoBuyTopBarNoBackPreview() {
    val navController = rememberNavController()

    COBUYTheme {
        CoBuyTopBar(
            title = "Os meus Grupos",
            navController = navController,
            showBackButton = true
        )
    }
}
