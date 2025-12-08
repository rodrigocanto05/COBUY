package pt.iade.ei.cobuy.ui.screens.recipesingredients

import androidx.compose.runtime.Composable
import androidx.compose.ui.tooling.preview.Preview
import androidx.navigation.NavController
import androidx.navigation.compose.rememberNavController
import pt.iade.ei.cobuy.R

@Composable
fun PizzaCaseiraScreen(
    navController: NavController,
    onAddIngredientToShoppingList: (IngredientUi) -> Unit
) {

    val ingredients = listOf(
        IngredientUi(
            id = 43,
            name = "Farinha de trigo",
            quantityText = "200 g"
        ),
        IngredientUi(
            id = 47,
            name = "Fermento de padeiro seco",
            quantityText = "5 g"
        ),
        IngredientUi(
            id = 56,
            name = "Água",
            quantityText = "120 ml"
        ),
        IngredientUi(
            id = 50,
            name = "Azeite",
            quantityText = "10 ml"
        ),
        IngredientUi(
            id = 51,
            name = "Molho de tomate",
            quantityText = "80 ml"
        ),
        IngredientUi(
            id = 23,
            name = "Queijo mozzarella ralado",
            quantityText = "120 g"
        ),
        IngredientUi(
            id = 34,
            name = "Tomate",
            quantityText = "1 un"
        ),
        IngredientUi(
            id = 60,
            name = "Orégãos secos",
            quantityText = "3 g"
        ),
        IngredientUi(
            id = 58,
            name = "Sal",
            quantityText = "1 pitada"
        )
    )

    val preparationSteps = listOf(
        "Numa taça misture a farinha de trigo, o sal e o fermento de padeiro seco.",
        "Adicione a água morna e o azeite aos poucos, mexendo até formar uma massa homogénea.",
        "Amasse durante alguns minutos e deixe levedar cerca de 30–40 minutos.",
        "Estenda a massa numa forma de pizza ou tabuleiro.",
        "Espalhe o molho de tomate por cima da massa.",
        "Cubra com o queijo mozzarella ralado, o tomate em rodelas e polvilhe com orégãos secos.",
        "Leve ao forno pré-aquecido a 200 ºC até a massa ficar dourada e o queijo derretido."
    )

    RecipeDetailScreen(
        navController = navController,
        title = "Pizza Caseira",
        imageResId = R.drawable.pizza,
        ingredients = ingredients,
        preparationSteps = preparationSteps,
        onAddIngredientToShoppingList = onAddIngredientToShoppingList
    )
}

@Preview(showBackground = true)
@Composable
fun PreviewPizzaCaseiraScreen() {
    PizzaCaseiraScreen(
        navController = rememberNavController(),
        onAddIngredientToShoppingList = {}
    )
}
