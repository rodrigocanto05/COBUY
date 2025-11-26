package pt.iade.ei.cobuy.ui.screens.recipesingredients

import androidx.compose.runtime.Composable
import androidx.navigation.NavController
import androidx.navigation.compose.rememberNavController
import androidx.compose.ui.tooling.preview.Preview
import pt.iade.ei.cobuy.R

@Composable
fun GelatinacomIogurteScreen(
    navController: NavController,
    onAddIngredientToShoppingList: (IngredientUi) -> Unit
) {
    val ingredients = listOf(
        IngredientUi(
            id = 49,
            name = "Gelatina em pó",
            quantityText = "20 g"
        ),
        IngredientUi(
            id = 56,
            name = "Água",
            quantityText = "200 ml"
        ),
        IngredientUi(
            id = 25,
            name = "Iogurte natural",
            quantityText = "200 g"
        ),
        IngredientUi(
            id = 67,
            name = "Morangos (opcional)",
            quantityText = "50 g"
        )
    )

    val preparationSteps = listOf(
        "Dissolva a gelatina em pó na água quente e mexa até ficar homogénea.",
        "Deixe arrefecer ligeiramente, mas sem solidificar.",
        "Misture a gelatina morna com o iogurte natural até obter um creme uniforme.",
        "Distribua por taças e leve ao frigorífico durante 2 a 3 horas.",
        "Decore com morangos antes de servir (opcional)."
    )

    RecipeDetailScreen(
        navController = navController,
        title = "Gelatina com Iogurte",
        imageResId = R.drawable.gelatinacomiogurte,
        ingredients = ingredients,
        preparationSteps = preparationSteps,
        onAddIngredientToShoppingList = onAddIngredientToShoppingList
    )
}

@Preview(showBackground = true)
@Composable
fun PreviewGelatinacomIogurteScreen() {
    GelatinacomIogurteScreen(
        navController = rememberNavController(),
        onAddIngredientToShoppingList = {}
    )
}
