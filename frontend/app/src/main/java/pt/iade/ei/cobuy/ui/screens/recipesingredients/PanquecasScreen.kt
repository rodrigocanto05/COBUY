package pt.iade.ei.cobuy.ui.screens.recipesingredients

import androidx.compose.runtime.Composable
import androidx.navigation.NavController
import androidx.navigation.compose.rememberNavController
import androidx.compose.ui.tooling.preview.Preview
import pt.iade.ei.cobuy.R

@Composable
fun PanquecasScreen(
    navController: NavController,
    onAddIngredientToShoppingList: (IngredientUi) -> Unit
) {
    val ingredients = listOf(
        IngredientUi(
            id = 43,
            name = "Farinha de trigo",
            quantityText = "150 g"
        ),
        IngredientUi(
            id = 44,
            name = "Açúcar",
            quantityText = "30 g"
        ),
        IngredientUi(
            id = 46,
            name = "Fermento em pó",
            quantityText = "10 g"
        ),
        IngredientUi(
            id = 16,
            name = "Ovos",
            quantityText = "2 un"
        ),
        IngredientUi(
            id = 17,
            name = "Leite",
            quantityText = "200 ml"
        ),
        IngredientUi(
            id = 19,
            name = "Manteiga derretida",
            quantityText = "20 g"
        )
    )

    val preparationSteps = listOf(
        "Numa tigela, misture a farinha, o fermento e o açúcar.",
        "Adicione os ovos e o leite, mexendo até obter uma massa lisa.",
        "Junte a manteiga derretida e envolva tudo.",
        "Aqueça uma frigideira levemente untada.",
        "Deite pequenas porções de massa e deixe cozinhar até aparecerem bolhas.",
        "Vire a panqueca e cozinhe mais alguns segundos.",
        "Sirva com mel, fruta ou chocolate."
    )

    RecipeDetailScreen(
        navController = navController,
        title = "Panquecas",
        imageResId = R.drawable.panquecas,
        ingredients = ingredients,
        preparationSteps = preparationSteps,
        onAddIngredientToShoppingList = onAddIngredientToShoppingList
    )
}

@Preview(showBackground = true)
@Composable
fun PreviewPanquecasScreen() {
    PanquecasScreen(
        navController = rememberNavController(),
        onAddIngredientToShoppingList = {}
    )
}
