package pt.iade.ei.cobuy.ui.screens.recipesingredients

import androidx.compose.runtime.Composable
import androidx.navigation.NavController
import androidx.navigation.compose.rememberNavController
import androidx.compose.ui.tooling.preview.Preview
import pt.iade.ei.cobuy.R

@Composable
fun TostaMistaScreen(
    navController: NavController,
    onAddIngredientToShoppingList: (IngredientUi) -> Unit
) {
    val ingredients = listOf(
        IngredientUi(
            id = 4,
            name = "Pão de forma",
            quantityText = "2 un"
        ),
        IngredientUi(
            id = 70,
            name = "Fiambre",
            quantityText = "50 g"
        ),
        IngredientUi(
            id = 22,
            name = "Queijo fatiado",
            quantityText = "40 g"
        ),
        IngredientUi(
            id = 19,
            name = "Manteiga",
            quantityText = "5 g"
        )
    )

    val preparationSteps = listOf(
        "Barre ligeiramente o exterior das fatias de pão com manteiga.",
        "No interior, coloque as fatias de queijo e fiambre.",
        "Feche a tosta e coloque numa sanduicheira ou frigideira.",
        "Deixe dourar de ambos os lados até o queijo derreter.",
        "Sirva quente."
    )

    RecipeDetailScreen(
        navController = navController,
        title = "Tosta Mista",
        imageResId = R.drawable.tostamista,
        ingredients = ingredients,
        preparationSteps = preparationSteps,
        onAddIngredientToShoppingList = onAddIngredientToShoppingList
    )
}

@Preview(showBackground = true)
@Composable
fun PreviewTostaMistaScreen() {
    TostaMistaScreen(
        navController = rememberNavController(),
        onAddIngredientToShoppingList = {}
    )
}
