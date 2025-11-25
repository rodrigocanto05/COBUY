package pt.iade.ei.cobuy.ui.screens.recipesingredients

import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.verticalScroll
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.AddShoppingCart
import androidx.compose.material3.*
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.navigation.NavController
import pt.iade.ei.cobuy.ui.components.bottombar.CoBuyBottomBar

data class IngredientUi(
    val id: Int,
    val name: String,
    val quantityText: String // já vem pronto: "400 g", "2 ovos", "Sal q.b."
)

@Composable
fun RecipeDetailScreen(
    navController: NavController,
    title: String,
    imageResId: Int,
    ingredients: List<IngredientUi>,
    preparationSteps: List<String>,
    onAddIngredientToShoppingList: (IngredientUi) -> Unit
) {
    Scaffold(
        bottomBar = { CoBuyBottomBar(navController) }
    ) { padding ->
        Column(
            modifier = Modifier
                .padding(padding)
                .fillMaxSize()
        ) {
            // Imagem no topo
            Image(
                painter = painterResource(id = imageResId),
                contentDescription = title,
                modifier = Modifier
                    .fillMaxWidth()
                    .height(220.dp),
                contentScale = ContentScale.Crop
            )

            Column(
                modifier = Modifier
                    .fillMaxSize()
                    .padding(16.dp)
                    .verticalScroll(rememberScrollState())
            ) {

                Text(
                    text = title,
                    style = MaterialTheme.typography.headlineMedium,
                    fontWeight = FontWeight.Bold
                )

                Spacer(modifier = Modifier.height(16.dp))

                // INGREDIENTES
                Text(
                    text = "Ingredientes",
                    style = MaterialTheme.typography.titleMedium,
                    fontWeight = FontWeight.SemiBold
                )

                Spacer(modifier = Modifier.height(8.dp))

                ingredients.forEach { ing ->
                    Row(
                        modifier = Modifier
                            .fillMaxWidth()
                            .padding(vertical = 4.dp),
                        verticalAlignment = Alignment.CenterVertically
                    ) {
                        Column(
                            modifier = Modifier.weight(1f)
                        ) {
                            Text(
                                text = ing.name,
                                style = MaterialTheme.typography.bodyMedium
                            )
                            Text(
                                text = ing.quantityText,
                                style = MaterialTheme.typography.bodySmall,
                                fontWeight = FontWeight.Light
                            )
                        }

                        IconButton(
                            onClick = { onAddIngredientToShoppingList(ing) }
                        ) {
                            Icon(
                                imageVector = Icons.Default.AddShoppingCart,
                                contentDescription = "Adicionar à lista de compras"
                            )
                        }
                    }
                }

                Spacer(modifier = Modifier.height(16.dp))

                // PREPARAÇÃO
                Text(
                    text = "Modo de preparação",
                    style = MaterialTheme.typography.titleMedium,
                    fontWeight = FontWeight.SemiBold
                )

                Spacer(modifier = Modifier.height(8.dp))

                preparationSteps.forEachIndexed { index, step ->
                    Text(
                        text = "${index + 1}. $step",
                        style = MaterialTheme.typography.bodyMedium,
                        modifier = Modifier.padding(bottom = 4.dp)
                    )
                }
            }
        }
    }
}
