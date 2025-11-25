package pt.iade.ei.cobuy.ui.screens.recipesingredients

import androidx.compose.runtime.Composable
import androidx.navigation.NavController
import androidx.navigation.compose.rememberNavController
import androidx.compose.ui.tooling.preview.Preview
import pt.iade.ei.cobuy.R

@Composable
fun FrangoGrelhadoScreen(
    navController: NavController,
    onAddIngredientToShoppingList: (IngredientUi) -> Unit
) {
    val ingredients = listOf(
        IngredientUi(
            id = 4,
            name = "Peito de frango",
            quantityText = "400 g"
        ),
        IngredientUi(
            id = 12,
            name = "Cebola",
            quantityText = "1 un"
        ),
        IngredientUi(
            id = 13,
            name = "Dentes de alho",
            quantityText = "2 un"
        ),
        IngredientUi(
            id = 50,
            name = "Cenoura",
            quantityText = "2 un"
        ),
        IngredientUi(
            id = 28,
            name = "Pimento",
            quantityText = "1 un"
        ),
        IngredientUi(
            id = 1,
            name = "Arroz",
            quantityText = "300 g"
        ),
        IngredientUi(
            id = 58,
            name = "Azeite",
            quantityText = "30 ml"
        ),
        IngredientUi(
            id = 59,
            name = "Sal",
            quantityText = "q.b."
        ),
        IngredientUi(
            id = 60,
            name = "Pimenta preta",
            quantityText = "5 g"
        )
    )

    val preparationSteps = listOf(
        "Tempere o frango com sal, pimenta e azeite e deixe repousar.",
        "Grelhe o frango de ambos os lados até dourar.",
        "Refogue cebola e alho, junte o arroz, mexa e adicione água e sal. Coza até ficar solto.",
        "Salteie a cenoura e o pimento em azeite até ficarem macios.",
        "Sirva o arroz com o frango fatiado e os legumes por cima."
    )

    RecipeDetailScreen(
        navController = navController,
        title = "Frango grelhado com arroz e legumes",
        imageResId = R.drawable.frangogrelhado,
        ingredients = ingredients,
        preparationSteps = preparationSteps,
        onAddIngredientToShoppingList = onAddIngredientToShoppingList
    )
}

@Preview(showBackground = true)
@Composable
fun PreviewFrangoGrelhadoScreen() {
    FrangoGrelhadoScreen(
        navController = rememberNavController(),
        onAddIngredientToShoppingList = {}
    )
}
