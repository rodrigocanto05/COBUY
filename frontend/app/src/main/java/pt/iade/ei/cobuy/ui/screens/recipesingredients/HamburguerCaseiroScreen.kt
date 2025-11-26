package pt.iade.ei.cobuy.ui.screens.recipesingredients

import androidx.compose.runtime.Composable
import androidx.navigation.NavController
import androidx.navigation.compose.rememberNavController
import androidx.compose.ui.tooling.preview.Preview
import pt.iade.ei.cobuy.R

@Composable
fun HamburguerCaseiroScreen(
    navController: NavController,
    onAddIngredientToShoppingList: (IngredientUi) -> Unit
) {
    val ingredients = listOf(
        // 0.40 kg → 400 g
        IngredientUi(
            id = 10,
            name = "Carne picada de vaca",
            quantityText = "400 g"
        ),
        IngredientUi(
            id = 5,
            name = "Pão de hambúrguer",
            quantityText = "2 un"
        ),
        IngredientUi(
            id = 22,
            name = "Queijo fatiado",
            quantityText = "40 g"
        ),
        IngredientUi(
            id = 33,
            name = "Alface",
            quantityText = "2 folhas"
        ),
        IngredientUi(
            id = 34,
            name = "Tomate",
            quantityText = "1 un"
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
        ),
        IngredientUi(
            id = 53,
            name = "Ketchup",
            quantityText = "15 ml"
        ),
        IngredientUi(
            id = 54,
            name = "Maionese",
            quantityText = "15 ml"
        )
    )

    val preparationSteps = listOf(
        "Tempere a carne picada com sal e pimenta e molde dois hambúrgueres.",
        "Grelhe ou frite os hambúrgueres até ficarem dourados de ambos os lados.",
        "Coloque o queijo por cima para derreter ligeiramente.",
        "Torre levemente o pão de hambúrguer.",
        "Monte: pão → alface → tomate → hambúrguer → molhos → pão.",
        "Sirva ainda quente."
    )

    RecipeDetailScreen(
        navController = navController,
        title = "Hambúrguer Caseiro",
        imageResId = R.drawable.haumburguer,
        ingredients = ingredients,
        preparationSteps = preparationSteps,
        onAddIngredientToShoppingList = onAddIngredientToShoppingList
    )
}

@Preview(showBackground = true)
@Composable
fun PreviewHamburguerCaseiroScreen() {
    HamburguerCaseiroScreen(
        navController = rememberNavController(),
        onAddIngredientToShoppingList = {}
    )
}
