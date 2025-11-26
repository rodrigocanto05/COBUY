package pt.iade.ei.cobuy.ui.screens.recipesingredients

import androidx.compose.runtime.Composable
import androidx.navigation.NavController
import androidx.navigation.compose.rememberNavController
import androidx.compose.ui.tooling.preview.Preview
import pt.iade.ei.cobuy.R

@Composable
fun WrapdeFrangocomAlfaceScreen(
    navController: NavController,
    onAddIngredientToShoppingList: (IngredientUi) -> Unit
) {
    val ingredients = listOf(
        IngredientUi(
            id = 6,
            name = "Tortilhas de trigo",
            quantityText = "2 un"
        ),
        // 0.20 kg → 200 g
        IngredientUi(
            id = 9,
            name = "Peito de frango",
            quantityText = "200 g"
        ),
        IngredientUi(
            id = 33,
            name = "Alface",
            quantityText = "4 folhas"
        ),
        IngredientUi(
            id = 34,
            name = "Tomate",
            quantityText = "1 un"
        ),
        IngredientUi(
            id = 54,
            name = "Maionese",
            quantityText = "15 ml"
        ),
        IngredientUi(
            id = 59,
            name = "Pimenta preta",
            quantityText = "1 g"
        ),
        IngredientUi(
            id = 58,
            name = "Sal",
            quantityText = "1 pitada"
        )
    )

    val preparationSteps = listOf(
        "Tempere o peito de frango com sal e pimenta e grelhe até dourar.",
        "Corte o frango em tiras finas.",
        "Espalhe a maionese no centro das tortilhas.",
        "Adicione as folhas de alface, o tomate fatiado e o frango.",
        "Enrole o wrap apertando bem as pontas.",
        "Pode cortar ao meio para servir mais facilmente."
    )

    RecipeDetailScreen(
        navController = navController,
        title = "Wrap de Frango com Alface",
        imageResId = R.drawable.wrapdefrango,
        ingredients = ingredients,
        preparationSteps = preparationSteps,
        onAddIngredientToShoppingList = onAddIngredientToShoppingList
    )
}

@Preview(showBackground = true)
@Composable
fun PreviewWrapdeFrangocomAlfaceScreen() {
    WrapdeFrangocomAlfaceScreen(
        navController = rememberNavController(),
        onAddIngredientToShoppingList = {}
    )
}
