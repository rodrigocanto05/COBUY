package pt.iade.ei.cobuy.ui.screens.recipesingredients

import androidx.compose.runtime.Composable
import androidx.navigation.NavController
import androidx.navigation.compose.rememberNavController
import androidx.compose.ui.tooling.preview.Preview
import pt.iade.ei.cobuy.R

@Composable
fun SalmãonoFornocomBatatasScreen(
    navController: NavController,
    onAddIngredientToShoppingList: (IngredientUi) -> Unit
) {
    val ingredients = listOf(
        // 0.60 kg → 600 g
        IngredientUi(
            id = 12,
            name = "Salmão",
            quantityText = "600 g"
        ),
        // 2 kg → 2000 g
        IngredientUi(
            id = 30,
            name = "Batatas",
            quantityText = "2000 g"
        ),
        IngredientUi(
            id = 26,
            name = "Cebola",
            quantityText = "1 un"
        ),
        IngredientUi(
            id = 28,
            name = "Dentes de alho",
            quantityText = "2 un"
        ),
        // 0.04 L → 40 ml
        IngredientUi(
            id = 50,
            name = "Azeite",
            quantityText = "40 ml"
        ),
        IngredientUi(
            id = 39,
            name = "Alecrim",
            quantityText = "3 raminhos"
        ),
        IngredientUi(
            id = 59,
            name = "Pimenta preta",
            quantityText = "3 g"
        ),
        IngredientUi(
            id = 58,
            name = "Sal",
            quantityText = "8 g"
        )
    )

    val preparationSteps = listOf(
        "Tempere o salmão com sal, pimenta, alho e azeite.",
        "Corte as batatas em rodelas finas e disponha num tabuleiro, temperando com sal, pimenta e azeite.",
        "Coloque o salmão por cima das batatas e regue com sumo de limão.",
        "Adicione a cebola às rodelas e ervas aromáticas.",
        "Leve ao forno a 180 ºC por 25–30 minutos, até o salmão estar cozido.",
        "Finalize com salsa fresca ou limão."
    )

    RecipeDetailScreen(
        navController = navController,
        title = "Salmão no Forno com Batatas",
        imageResId = R.drawable.salmaonoforno,
        ingredients = ingredients,
        preparationSteps = preparationSteps,
        onAddIngredientToShoppingList = onAddIngredientToShoppingList
    )
}

@Preview(showBackground = true)
@Composable
fun PreviewSalmãonoFornocomBatatasScreen() {
    SalmãonoFornocomBatatasScreen(
        navController = rememberNavController(),
        onAddIngredientToShoppingList = {}
    )
}
