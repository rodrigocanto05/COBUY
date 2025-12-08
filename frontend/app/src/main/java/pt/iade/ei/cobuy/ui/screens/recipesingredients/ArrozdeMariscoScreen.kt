package pt.iade.ei.cobuy.ui.screens.recipesingredients

import androidx.compose.runtime.Composable
import androidx.navigation.NavController
import androidx.navigation.compose.rememberNavController
import androidx.compose.ui.tooling.preview.Preview
import pt.iade.ei.cobuy.R

@Composable
fun ArrozdeMarisco(
    navController: NavController,
    onAddIngredientToShoppingList: (IngredientUi) -> Unit
) {
    val ingredients = listOf(
        IngredientUi(
            id = 3,
            name = "Arroz carolino",
            quantityText = "350 g"
        ),
        IngredientUi(
            id = 13,
            name = "Miolo de camarão",
            quantityText = "300 g"
        ),
        IngredientUi(
            id = 14,
            name = "Miolo de mexilhão",
            quantityText = "250 g"
        ),
        IngredientUi(
            id = 15,
            name = "Amêijoas",
            quantityText = "250 g"
        ),
        IngredientUi(
            id = 26,
            name = "Cebola",
            quantityText = "1 un"
        ),
        IngredientUi(
            id = 28,
            name = "Dentes de alho",
            quantityText = "3 un"
        ),
        IngredientUi(
            id = 32,
            name = "Pimento vermelho",
            quantityText = "1 un"
        ),
        IngredientUi(
            id = 51,
            name = "Molho de tomate",
            quantityText = "200 ml"
        ),
        IngredientUi(
            id = 57,
            name = "Caldo de peixe",
            quantityText = "800 ml"
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
            quantityText = "3 g"
        ),
        IngredientUi(
            id = 37,
            name = "Coentros frescos",
            quantityText = "2 raminhos"
        )
    )

    val preparationSteps = listOf(
        "Refogue cebola e alho em azeite até dourar.",
        "Adicione o tomate e o pimento e deixe cozinhar alguns minutos.",
        "Junte o arroz e envolva no refogado.",
        "Acrescente o caldo e o vinho branco, tempere e deixe cozer.",
        "Adicione o marisco quando o arroz estiver quase pronto.",
        "Finalize com coentros e sirva com o arroz ainda cremoso."
    )

    RecipeDetailScreen(
        navController = navController,
        title = "Arroz de Marisco",
        imageResId = R.drawable.arroz_de_marsico,
        ingredients = ingredients,
        preparationSteps = preparationSteps,
        onAddIngredientToShoppingList = onAddIngredientToShoppingList
    )
}

@Preview(showBackground = true)
@Composable
fun PreviewArrozdeMarisco() {
    ArrozdeMarisco(
        navController = rememberNavController(),
        onAddIngredientToShoppingList = {}
    )
}
