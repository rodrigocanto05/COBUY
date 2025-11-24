package pt.iade.ei.cobuy.ui.screens.recipesingredients

import androidx.compose.material3.*
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.foundation.layout.padding
import androidx.compose.ui.unit.dp          // ← IMPORT NECESSÁRIO
import androidx.navigation.NavController
import pt.iade.ei.cobuy.ui.components.bottombar.CoBuyBottomBar

@Composable
fun BacalhaaBrasScreen(navController: NavController) {
    Scaffold(
        bottomBar = { CoBuyBottomBar(navController) }
    ) { padding ->
        Text(
            text = "Bacalhau à Brás",
            modifier = Modifier
                .padding(padding)
                .padding(20.dp)
        )
    }
}
