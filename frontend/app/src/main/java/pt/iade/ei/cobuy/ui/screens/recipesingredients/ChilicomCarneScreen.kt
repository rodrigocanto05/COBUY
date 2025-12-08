package pt.iade.ei.cobuy.ui.screens.recipesingredients

import androidx.compose.runtime.Composable
import androidx.navigation.NavController
import androidx.navigation.compose.rememberNavController
import androidx.compose.ui.tooling.preview.Preview
import pt.iade.ei.cobuy.R

@Composable
fun ChilicomCarneScreen(
    navController: NavController,
    onAddIngredientToShoppingList: (IngredientUi) -> Unit
) {
    val ingredients = listOf(
        IngredientUi(
            id = 10,
            name = "Carne picada",
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
        IngredientUi(
            id = 32,
            name = "Pimento vermelho",
            quantityText = "1 un"
        ),
        IngredientUi(
            id = 41,
            name = "Feijão vermelho cozido",
            quantityText = "400 g"
        ),
        IngredientUi(
            id = 51,
            name = "Molho de tomate",
            quantityText = "400 ml"
        ),
        IngredientUi(
            id = 62,
            name = "Cominhos",
            quantityText = "5 g"
        ),
        IngredientUi(
            id = 63,
            name = "Paprika",
            quantityText = "5 g"
        ),
        IngredientUi(
            id = 64,
            name = "Malagueta",
            quantityText = "1 g"
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
        )
    )

    val preparationSteps = listOf(
        "Aqueça um fio de azeite numa panela e refogue a cebola e o alho até dourar.",
        "Adicione a carne picada e deixe cozinhar até ficar solta e ligeiramente dourada.",
        "Junte o pimento vermelho em cubos e deixe cozinhar alguns minutos.",
        "Acrescente o feijão vermelho, o molho de tomate e a polpa de tomate.",
        "Tempere com sal, pimenta preta, cominhos, paprika e malagueta.",
        "Deixe cozinhar em lume brando cerca de 20–30 minutos até apurar."
    )

    RecipeDetailScreen(
        navController = navController,
        title = "Chili com Carne",
        imageResId = R.drawable.chilicomcarne,
        ingredients = ingredients,
        preparationSteps = preparationSteps,
        onAddIngredientToShoppingList = onAddIngredientToShoppingList
    )
}

@Preview(showBackground = true)
@Composable
fun PreviewChilicomCarneScreen() {
    ChilicomCarneScreen(
        navController = rememberNavController(),
        onAddIngredientToShoppingList = {}
    )
}
