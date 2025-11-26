package pt.iade.ei.cobuy.ui.screens.recipesingredients

import androidx.compose.runtime.Composable
import androidx.navigation.NavController
import androidx.navigation.compose.rememberNavController
import androidx.compose.ui.tooling.preview.Preview
import pt.iade.ei.cobuy.R

@Composable
fun SalamedeChocolateScreen(
    navController: NavController,
    onAddIngredientToShoppingList: (IngredientUi) -> Unit
) {
    val ingredients = listOf(
        IngredientUi(
            id = 7,
            name = "Bolacha Maria",
            quantityText = "200 g"
        ),
        IngredientUi(
            id = 19,
            name = "Manteiga",
            quantityText = "125 g"
        ),
        IngredientUi(
            id = 48,
            name = "Chocolate em pó",
            quantityText = "100 g"
        ),
        IngredientUi(
            id = 44,
            name = "Açúcar",
            quantityText = "150 g"
        ),
        IngredientUi(
            id = 16,
            name = "Ovo",
            quantityText = "1 un"
        )
    )

    val preparationSteps = listOf(
        "Parta as bolachas grosseiramente, deixando pedaços irregulares.",
        "Derreta a manteiga numa tigela.",
        "Adicione o açúcar e o chocolate em pó e misture bem.",
        "Junte o ovo e mexa rapidamente.",
        "Acrescente as bolachas partidas e envolva tudo.",
        "Coloque a mistura sobre papel vegetal e molde em forma de rolo.",
        "Enrole bem, aperte as pontas e leve ao frigorífico 3–4 horas.",
        "Depois de firme, corte em fatias."
    )

    RecipeDetailScreen(
        navController = navController,
        title = "Salame de Chocolate",
        imageResId = R.drawable.salamedechocolate,
        ingredients = ingredients,
        preparationSteps = preparationSteps,
        onAddIngredientToShoppingList = onAddIngredientToShoppingList
    )
}

@Preview(showBackground = true)
@Composable
fun PreviewSalamedeChocolateScreen() {
    SalamedeChocolateScreen(
        navController = rememberNavController(),
        onAddIngredientToShoppingList = {}
    )
}
