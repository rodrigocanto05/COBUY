package pt.iade.ei.cobuy.ui.screens.recipesingredients

import androidx.compose.runtime.Composable
import androidx.navigation.NavController
import androidx.navigation.compose.rememberNavController
import androidx.compose.ui.tooling.preview.Preview
import pt.iade.ei.cobuy.R

@Composable
fun LasanhadeCarneScreen(
    navController: NavController,
    onAddIngredientToShoppingList: (IngredientUi) -> Unit
) {
    val ingredients = listOf(
        IngredientUi(
            id = 2,
            name = "Placas de lasanha",
            quantityText = "12 un"
        ),
        // 0.50 kg → 500 g
        IngredientUi(
            id = 10,
            name = "Carne picada de vaca",
            quantityText = "500 g"
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
        // 0.20 L → 200 ml
        IngredientUi(
            id = 51,
            name = "Molho de tomate",
            quantityText = "200 ml"
        ),
        // 150 g
        IngredientUi(
            id = 21,
            name = "Queijo ralado",
            quantityText = "150 g"
        ),
        IngredientUi(
            id = 19,
            name = "Manteiga",
            quantityText = "30 g"
        ),
        IngredientUi(
            id = 43,
            name = "Farinha de trigo",
            quantityText = "20 g"
        ),
        // 0.50 L → 500 ml
        IngredientUi(
            id = 17,
            name = "Leite",
            quantityText = "500 ml"
        ),
        // 0.02 L → 20 ml
        IngredientUi(
            id = 50,
            name = "Azeite",
            quantityText = "20 ml"
        ),
        IngredientUi(
            id = 58,
            name = "Sal",
            quantityText = "8 g"
        ),
        IngredientUi(
            id = 59,
            name = "Pimenta preta",
            quantityText = "3 g"
        )
    )

    val preparationSteps = listOf(
        "Refogue cebola e alho em azeite, junte a carne picada e tempere.",
        "Adicione o molho de tomate e deixe cozinhar alguns minutos.",
        "Prepare o molho bechamel com leite, manteiga e farinha.",
        "Num tabuleiro, faça camadas alternadas de lasanha, carne, bechamel e queijo.",
        "Leve ao forno a 180 °C durante 25–30 minutos."
    )

    RecipeDetailScreen(
        navController = navController,
        title = "Lasanha de Carne",
        imageResId = R.drawable.lasanha,
        ingredients = ingredients,
        preparationSteps = preparationSteps,
        onAddIngredientToShoppingList = onAddIngredientToShoppingList
    )
}

@Preview(showBackground = true)
@Composable
fun PreviewLasanhadeCarneScreen() {
    LasanhadeCarneScreen(
        navController = rememberNavController(),
        onAddIngredientToShoppingList = {}
    )
}
