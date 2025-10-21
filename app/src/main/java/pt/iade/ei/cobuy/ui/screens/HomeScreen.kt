package pt.iade.ei.cobuy.ui.screens

import androidx.compose.foundation.layout.*
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.*
import androidx.compose.material3.*
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import androidx.navigation.NavController
import pt.iade.ei.cobuy.ui.navigation.NavRoutes

@Composable
fun HomeScreen(navController: NavController) {
    Scaffold(
        topBar = { TopAppBar(title = { Text("COBUY - Início") }) }
    ) { padding ->
        Column(
            modifier = Modifier
                .fillMaxSize()
                .padding(padding)
                .padding(16.dp)
        ) {
            Button(onClick = { navController.navigate(NavRoutes.ShoppingList.route) }, modifier = Modifier.fillMaxWidth()) {
                Icon(Icons.Default.List, contentDescription = null)
                Spacer(Modifier.width(8.dp))
                Text("Listas de Compras")
            }
            Spacer(Modifier.height(8.dp))
            Button(onClick = { navController.navigate(NavRoutes.Recipes.route) }, modifier = Modifier.fillMaxWidth()) {
                Icon(Icons.Default.Restaurant, contentDescription = null)
                Spacer(Modifier.width(8.dp))
                Text("Refeições Inteligentes")
            }
            Spacer(Modifier.height(8.dp))
            Button(onClick = { navController.navigate(NavRoutes.Map.route) }, modifier = Modifier.fillMaxWidth()) {
                Icon(Icons.Default.Map, contentDescription = null)
                Spacer(Modifier.width(8.dp))
                Text("Supermercados Próximos")
            }
        }
    }
}

