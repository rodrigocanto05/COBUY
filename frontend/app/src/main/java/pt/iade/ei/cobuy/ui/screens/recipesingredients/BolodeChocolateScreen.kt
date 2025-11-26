package pt.iade.ei.cobuy.ui.screens.recipesingredients

import androidx.compose.runtime.Composable
import androidx.navigation.NavController
import androidx.navigation.compose.rememberNavController
import androidx.compose.ui.tooling.preview.Preview
import pt.iade.ei.cobuy.R

@Composable
fun BolodeChocolateScreen(
    navController: NavController,
    onAddIngredientToShoppingList: (IngredientUi) -> Unit
) {
    val ingredients = listOf(
        IngredientUi(
            id = 43,
            name = "Farinha de trigo",
            quantityText = "200 g"
        ),
        IngredientUi(
            id = 48,
            name = "Chocolate em pó",
            quantityText = "80 g"
        ),
        IngredientUi(
            id = 44,
            name = "Açúcar",
            quantityText = "150 g"
        ),
        IngredientUi(
            id = 16,
            name = "Ovos",
            quantityText = "3 un"
        ),
        // 200 ml
        IngredientUi(
            id = 17,
            name = "Leite",
            quantityText = "200 ml"
        ),
        IngredientUi(
            id = 19,
            name = "Manteiga",
            quantityText = "50 g"
        ),
        IngredientUi(
            id = 46,
            name = "Fermento em pó",
            quantityText = "10 g"
        )
    )

    val preparationSteps = listOf(
        "Misture a farinha, o cacau em pó, o açúcar e o fermento numa tigela.",
        "Adicione os ovos, o leite e a manteiga derretida.",
        "Bata até obter uma massa homogénea.",
        "Verta para uma forma untada.",
        "Leve ao forno a 180 °C por 30–35 minutos.",
        "Deixe arrefecer antes de servir."
    )

    RecipeDetailScreen(
        navController = navController,
        title = "Bolo de Chocolate",
        imageResId = R.drawable.bolodechocolate,
        ingredients = ingredients,
        preparationSteps = preparationSteps,
        onAddIngredientToShoppingList = onAddIngredientToShoppingList
    )
}

@Preview(showBackground = true)
@Composable
fun PreviewBolodeChocolateScreen() {
    BolodeChocolateScreen(
        navController = rememberNavController(),
        onAddIngredientToShoppingList = {}
    )
}
