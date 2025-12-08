package pt.iade.ei.cobuy.ui.screens.recipesingredients

import androidx.compose.runtime.Composable
import androidx.navigation.NavController
import androidx.navigation.compose.rememberNavController
import androidx.compose.ui.tooling.preview.Preview
import pt.iade.ei.cobuy.R

@Composable
fun BacalhaaBrasScreen(
    navController: NavController,
    onAddIngredientToShoppingList: (IngredientUi) -> Unit
) {
    val ingredients = listOf(
        IngredientUi(
            id = 11,
            name = "Bacalhau desfiado",
            quantityText = "500 g"
        ),
        IngredientUi(
            id = 69,
            name = "Batata palha",
            quantityText = "200 g"
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
        IngredientUi(
            id = 50,
            name = "Azeite",
            quantityText = "30 ml"
        ),
        IngredientUi(
            id = 16,
            name = "Ovos",
            quantityText = "4 un"
        ),
        IngredientUi(
            id = 38,
            name = "Salsa fresca",
            quantityText = "5 g"
        ),
        IngredientUi(
            id = 58,
            name = "Sal",
            quantityText = "q.b."
        ),
        IngredientUi(
            id = 59,
            name = "Pimenta preta",
            quantityText = "3 g"
        ),
        IngredientUi(
            id = 40,
            name = "Azeitonas pretas fatiadas",
            quantityText = "10 g"
        )
    )

    val preparationSteps = listOf(
        "Coza o bacalhau, escorra e desfie-o.",
        "Refogue cebola e alho em azeite, junte o bacalhau e envolva.",
        "Adicione a batata palha e misture bem.",
        "Junte os ovos batidos e mexa até ficarem cremosos.",
        "Tempere, adicione salsa e finalize com azeitonas."
    )

    RecipeDetailScreen(
        navController = navController,
        title = "Bacalhau à Brás",
        imageResId = R.drawable.bacalhauabras,
        ingredients = ingredients,
        preparationSteps = preparationSteps,
        onAddIngredientToShoppingList = onAddIngredientToShoppingList
    )
}

@Preview(showBackground = true)
@Composable
fun PreviewBacalhaaBrasScreen() {
    BacalhaaBrasScreen(
        navController = rememberNavController(),
        onAddIngredientToShoppingList = {}
    )
}
