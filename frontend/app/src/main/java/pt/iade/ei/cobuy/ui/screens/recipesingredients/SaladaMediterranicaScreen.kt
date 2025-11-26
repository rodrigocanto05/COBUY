package pt.iade.ei.cobuy.ui.screens.recipesingredients

import androidx.compose.runtime.Composable
import androidx.navigation.NavController
import androidx.navigation.compose.rememberNavController
import androidx.compose.ui.tooling.preview.Preview
import pt.iade.ei.cobuy.R

@Composable
fun SaladaMediterranicaScreen(
    navController: NavController,
    onAddIngredientToShoppingList: (IngredientUi) -> Unit
) {
    val ingredients = listOf(
        IngredientUi(
            id = 33,
            name = "Alface",
            quantityText = "4 folhas"
        ),
        IngredientUi(
            id = 34,
            name = "Tomate",
            quantityText = "2 un"
        ),
        IngredientUi(
            id = 35,
            name = "Pepino",
            quantityText = "1 un"
        ),
        IngredientUi(
            id = 40,
            name = "Azeitonas pretas",
            quantityText = "50 g"
        ),
        IngredientUi(
            id = 24,
            name = "Queijo feta",
            quantityText = "80 g"
        ),
        IngredientUi(
            id = 55,
            name = "Vinagre",
            quantityText = "10 ml"
        ),
        IngredientUi(
            id = 50,
            name = "Azeite",
            quantityText = "20 ml"
        ),
        IngredientUi(
            id = 60,
            name = "Orégãos secos",
            quantityText = "2 g"
        )
    )

    val preparationSteps = listOf(
        "Corte o tomate e o pepino em pedaços médios.",
        "Misture numa taça a alface, tomate, pepino e azeitonas.",
        "Adicione o queijo feta em cubos.",
        "Regue com azeite e vinagre.",
        "Polvilhe com orégãos e envolva suavemente.",
        "Sirva fresca."
    )

    RecipeDetailScreen(
        navController = navController,
        title = "Salada Mediterrânica",
        imageResId = R.drawable.saladamediterranea,
        ingredients = ingredients,
        preparationSteps = preparationSteps,
        onAddIngredientToShoppingList = onAddIngredientToShoppingList
    )
}

@Preview(showBackground = true)
@Composable
fun PreviewSaladaMediterranicaScreen() {
    SaladaMediterranicaScreen(
        navController = rememberNavController(),
        onAddIngredientToShoppingList = {}
    )
}
