package pt.iade.ei.cobuy.ui.screens.recipesingredients

import androidx.compose.runtime.Composable
import androidx.navigation.NavController
import androidx.navigation.compose.rememberNavController
import androidx.compose.ui.tooling.preview.Preview
import pt.iade.ei.cobuy.R

@Composable
fun SopadeLegumesScreen(
    navController: NavController,
    onAddIngredientToShoppingList: (IngredientUi) -> Unit
) {
    val ingredients = listOf(
        IngredientUi(
            id = 29,
            name = "Cenoura",
            quantityText = "2 un"
        ),
        // 0.3 kg → 300 g
        IngredientUi(
            id = 30,
            name = "Batata",
            quantityText = "300 g"
        ),
        IngredientUi(
            id = 26,
            name = "Cebola",
            quantityText = "1 un"
        ),
        IngredientUi(
            id = 31,
            name = "Courgette",
            quantityText = "1 un"
        ),
        // 1 L → 1000 ml
        IngredientUi(
            id = 56,
            name = "Água",
            quantityText = "1000 ml"
        ),
        // 0.03 L → 30 ml
        IngredientUi(
            id = 50,
            name = "Azeite",
            quantityText = "30 ml"
        ),
        IngredientUi(
            id = 58,
            name = "Sal",
            quantityText = "q.b."
        ),
        IngredientUi(
            id = 59,
            name = "Pimenta preta",
            quantityText = "2 g"
        )
    )

    val preparationSteps = listOf(
        "Corte a cenoura, batata, courgette e cebola em pedaços pequenos.",
        "Coloque todos os legumes numa panela com a água, azeite, sal e pimenta.",
        "Cozinhe até os legumes ficarem bem macios.",
        "Triture a sopa até ficar cremosa.",
        "Ajuste o sal e sirva quente."
    )

    RecipeDetailScreen(
        navController = navController,
        title = "Sopa de Legumes",
        imageResId = R.drawable.sopadelegumes,
        ingredients = ingredients,
        preparationSteps = preparationSteps,
        onAddIngredientToShoppingList = onAddIngredientToShoppingList
    )
}

@Preview(showBackground = true)
@Composable
fun PreviewSopadeLegumesScreen() {
    SopadeLegumesScreen(
        navController = rememberNavController(),
        onAddIngredientToShoppingList = {}
    )
}
