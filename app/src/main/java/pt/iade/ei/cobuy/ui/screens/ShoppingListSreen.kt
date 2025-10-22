package pt.iade.ei.cobuy.ui.screens

import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.*
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import androidx.navigation.NavController

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun ShoppingListScreen(navController: NavController) {
    val products = remember { mutableStateListOf("Leite", "Pão", "Arroz") }

    Scaffold(
        topBar = { TopAppBar(title = { Text("Lista de Compras") }) },
        floatingActionButton = {
            FloatingActionButton(onClick = { products.add("Novo Produto") }) {
                Icon(Icons.Default.Add, contentDescription = "Adicionar produto")
            }
        }
    ) { padding ->
        LazyColumn(modifier = Modifier.padding(padding)) {
            items(products) { item ->
                ListItem(
                    headlineContent = { Text(item) },
                    leadingContent = { Icon(Icons.Default.ShoppingCart, contentDescription = null) }
                )
                Divider()
            }
        }
    }
}
