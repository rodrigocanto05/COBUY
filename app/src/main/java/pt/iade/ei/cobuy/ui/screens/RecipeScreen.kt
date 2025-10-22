package pt.iade.ei.cobuy.ui.screens

import androidx.compose.foundation.layout.*
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import androidx.navigation.NavController

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun RecipeScreen(navController: NavController) {
    var mealName by remember { mutableStateOf("") }
    var ingredients by remember { mutableStateOf(listOf<String>()) }

    Scaffold(
        topBar = { TopAppBar(title = { Text("Refeições Inteligentes") }) }
    ) { padding ->
        Column(
            modifier = Modifier
                .fillMaxSize()
                .padding(padding)
                .padding(16.dp)
        ) {
            OutlinedTextField(
                value = mealName,
                onValueChange = { mealName = it },
                label = { Text("Nome da refeição") },
                modifier = Modifier.fillMaxWidth()
            )
            Spacer(modifier = Modifier.height(8.dp))
            Button(onClick = {
                ingredients = listOf("Massa", "Atum", "Azeite", "Sal")
            }) {
                Text("Gerar Ingredientes")
            }
            Spacer(modifier = Modifier.height(16.dp))
            ingredients.forEach { Text("- $it") }
        }
    }
}

