package pt.iade.ei.cobuy.ui.screens.recipesingredients

import androidx.compose.runtime.Composable
import androidx.navigation.NavController
import androidx.navigation.compose.rememberNavController
import androidx.compose.ui.tooling.preview.Preview
import pt.iade.ei.cobuy.R

@Composable
fun MassaCarbonaraScreen(
    navController: NavController,
    onAddIngredientToShoppingList: (IngredientUi) -> Unit
) {
    val ingredients = listOf(
        IngredientUi(
            id = 1,
            name = "Esparguete",
            quantityText = "400 g"
        ),
        IngredientUi(
            id = 8,
            name = "Bacon em tiras",
            quantityText = "150 g"
        ),
        IngredientUi(
            id = 16,
            name = "Ovos",
            quantityText = "4 un"
        ),
        IngredientUi(
            id = 18,
            name = "Natas",
            quantityText = "200 g"
        ),
        IngredientUi(
            id = 20,
            name = "Queijo parmesão ralado",
            quantityText = "60 g"
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
            id = 58,
            name = "Sal",
            quantityText = "q.b."
        ),
        IngredientUi(
            id = 59,
            name = "Pimenta preta",
            quantityText = "5 g"
        )
    )

    val preparationSteps = listOf(
        "Coza o esparguete em água a ferver com sal até ficar al dente.",
        "Misture os ovos, as natas, o parmesão, o sal (pouco) e a pimenta, e reserve.",
        "Numa frigideira, aqueça azeite, salteie o bacon e junte o alho por alguns segundos.",
        "Adicione o esparguete cozido e envolva bem.",
        "Fora do lume, junte o molho de ovos e mexa até ficar cremoso.",
        "Finalize com pimenta e parmesão."
    )

    RecipeDetailScreen(
        navController = navController,
        title = "Massa Carbonara",
        imageResId = R.drawable.massa_carbonara,
        ingredients = ingredients,
        preparationSteps = preparationSteps,
        onAddIngredientToShoppingList = onAddIngredientToShoppingList
    )
}

@Preview(showBackground = true)
@Composable
fun PreviewMassaCarbonaraScreen() {
    MassaCarbonaraScreen(
        navController = rememberNavController(),
        onAddIngredientToShoppingList = {}
    )
}
