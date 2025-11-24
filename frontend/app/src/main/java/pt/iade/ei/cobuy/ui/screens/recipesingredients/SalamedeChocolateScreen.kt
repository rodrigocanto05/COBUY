package pt.iade.ei.cobuy.ui.screens.recipesingredients

import androidx.compose.material3.*
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.foundation.layout.padding
import androidx.compose.ui.unit.dp
import androidx.navigation.NavController
import pt.iade.ei.cobuy.ui.components.bottombar.CoBuyBottomBar

@Composable
fun SalamedeChocolateScreen(navController: NavController) {
    Scaffold(
        bottomBar = { CoBuyBottomBar(navController) }
    ) { padding ->
        Text(
            text = "Salame de Chocolate",
            modifier = Modifier
                .padding(padding)
                .padding(20.dp)
        )
    }
}
