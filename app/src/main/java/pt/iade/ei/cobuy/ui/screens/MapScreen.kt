package pt.iade.ei.cobuy.ui.screens

import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.*
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.navigation.NavController

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun MapScreen(navController: NavController) {
    Scaffold(
        topBar = { TopAppBar(title = { Text("Mapa de Supermercados") }) }
    ) { padding ->
        Box(modifier = Modifier.padding(padding)) {
            Text("Mapa será aqui (Google Maps API)")
        }
    }
}

