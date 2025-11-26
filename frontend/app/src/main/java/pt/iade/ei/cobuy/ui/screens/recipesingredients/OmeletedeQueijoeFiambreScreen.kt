package pt.iade.ei.cobuy.ui.screens.recipesingredients

import androidx.compose.runtime.Composable
import androidx.navigation.NavController
import androidx.navigation.compose.rememberNavController
import androidx.compose.ui.tooling.preview.Preview
import pt.iade.ei.cobuy.R

@Composable
fun OmeletedeQueijoeFiambreScreen(
    navController: NavController,
    onAddIngredientToShoppingList: (IngredientUi) -> Unit
) {
    val ingredients = listOf(
        IngredientUi(
            id = 16,
            name = "Ovos",
            quantityText = "3 un"
        ),
        IngredientUi(
            id = 70,
            name = "Fiambre",
            quantityText = "60 g"
        ),
        IngredientUi(
            id = 22,
            name = "Queijo fatiado",
            quantityText = "40 g"
        ),
        IngredientUi(
            id = 19,
            name = "Manteiga",
            quantityText = "10 g"
        ),
        IngredientUi(
            id = 58,
            name = "Sal",
            quantityText = "1 pitada"
        ),
        IngredientUi(
            id = 59,
            name = "Pimenta preta",
            quantityText = "1 g"
        )
    )

    val preparationSteps = listOf(
        "Bata os ovos com uma pitada de sal e pimenta.",
        "Junte o fiambre em pedaços e o queijo ralado, mexendo ligeiramente.",
        "Aqueça uma frigideira antiaderente e derreta a manteiga.",
        "Deite a mistura na frigideira e deixe cozinhar em lume médio-baixo.",
        "Quando estiver quase firme, dobre a omelete.",
        "Deixe terminar de cozinhar até o interior ficar cremoso."
    )

    RecipeDetailScreen(
        navController = navController,
        title = "Omelete de Queijo e Fiambre",
        imageResId = R.drawable.omelete,
        ingredients = ingredients,
        preparationSteps = preparationSteps,
        onAddIngredientToShoppingList = onAddIngredientToShoppingList
    )
}

@Preview(showBackground = true)
@Composable
fun PreviewOmeletedeQueijoeFiambreScreen() {
    OmeletedeQueijoeFiambreScreen(
        navController = rememberNavController(),
        onAddIngredientToShoppingList = {}
    )
}
