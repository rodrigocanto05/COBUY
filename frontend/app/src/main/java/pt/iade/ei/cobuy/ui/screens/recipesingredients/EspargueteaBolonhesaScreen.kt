package pt.iade.ei.cobuy.ui.screens.recipesingredients

import androidx.compose.runtime.Composable
import androidx.navigation.NavController
import androidx.navigation.compose.rememberNavController
import androidx.compose.ui.tooling.preview.Preview
import pt.iade.ei.cobuy.R

@Composable
fun EspargueteaBolonhesa(
    navController: NavController,
    onAddIngredientToShoppingList: (IngredientUi) -> Unit
) {
    val ingredients = listOf(
        IngredientUi(
            id = 1,
            name = "Esparguete",
            quantityText = "300 g"
        ),
        IngredientUi(
            id = 10,
            name = "Carne picada de vaca",
            quantityText = "400 g"
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
            id = 51,
            name = "Molho de tomate",
            quantityText = "100 ml"
        ),
        IngredientUi(
            id = 52,
            name = "Polpa de tomate",
            quantityText = "50 ml"
        ),
        IngredientUi(
            id = 59,
            name = "Pimenta preta",
            quantityText = "1 g"
        ),
        IngredientUi(
            id = 58,
            name = "Sal",
            quantityText = "q.b."
        )
    )

    val preparationSteps = listOf(
        "Coza o esparguete em água a ferver com sal até ficar al dente.",
        "Num tacho, refogue a cebola picada e o alho em azeite.",
        "Adicione a carne picada e cozinhe até ganhar cor.",
        "Junte o molho de tomate, a polpa, sal e pimenta.",
        "Deixe cozinhar em lume brando durante 10–15 minutos.",
        "Envolva o esparguete com o molho e sirva quente."
    )

    RecipeDetailScreen(
        navController = navController,
        title = "Esparguete à Bolonhesa",
        imageResId = R.drawable.bolonhesa,
        ingredients = ingredients,
        preparationSteps = preparationSteps,
        onAddIngredientToShoppingList = onAddIngredientToShoppingList
    )
}

@Preview(showBackground = true)
@Composable
fun PreviewEspargueteaBolonhesa() {
    EspargueteaBolonhesa(
        navController = rememberNavController(),
        onAddIngredientToShoppingList = {}
    )
}
