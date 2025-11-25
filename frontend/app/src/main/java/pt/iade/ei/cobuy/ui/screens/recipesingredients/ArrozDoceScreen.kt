package pt.iade.ei.cobuy.ui.screens.recipesingredients

import androidx.compose.runtime.Composable
import androidx.navigation.NavController
import androidx.navigation.compose.rememberNavController
import androidx.compose.ui.tooling.preview.Preview
import pt.iade.ei.cobuy.R

@Composable
fun ArrozDoceScreen(
    navController: NavController,
    onAddIngredientToShoppingList: (IngredientUi) -> Unit
) {
    val ingredients = listOf(
        // 0.20 kg → 200 g
        IngredientUi(
            id = 3,
            name = "Arroz carolino",
            quantityText = "200 g"
        ),
        // 1 L → 1000 ml
        IngredientUi(
            id = 17,
            name = "Leite",
            quantityText = "1000 ml"
        ),
        IngredientUi(
            id = 44,
            name = "Açúcar",
            quantityText = "120 g"
        ),
        IngredientUi(
            id = 65,
            name = "Pau de canela",
            quantityText = "1 un"
        ),
        IngredientUi(
            id = 16,
            name = "Gemas de ovo",
            quantityText = "2 un"
        ),
        IngredientUi(
            id = 58,
            name = "Sal",
            quantityText = "1 pitada"
        )
    )

    val preparationSteps = listOf(
        "Aqueça o leite com o pau de canela e uma pitada de sal.",
        "Junte o arroz e cozinhe em lume brando, mexendo sempre.",
        "Quando o arroz estiver quase cozido, adicione o açúcar e misture bem.",
        "Retire um pouco do leite quente, misture com as gemas e incorpore lentamente no tacho.",
        "Cozinhe mais 2–3 minutos até engrossar ligeiramente.",
        "Sirva ainda quente ou deixe arrefecer."
    )

    RecipeDetailScreen(
        navController = navController,
        title = "Arroz Doce",
        imageResId = R.drawable.arrozdoce,
        ingredients = ingredients,
        preparationSteps = preparationSteps,
        onAddIngredientToShoppingList = onAddIngredientToShoppingList
    )
}

@Preview(showBackground = true)
@Composable
fun PreviewArrozDoceScreen() {
    ArrozDoceScreen(
        navController = rememberNavController(),
        onAddIngredientToShoppingList = {}
    )
}
