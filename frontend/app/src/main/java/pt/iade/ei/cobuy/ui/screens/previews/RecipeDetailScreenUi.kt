package pt.iade.ei.cobuy.ui.screens.previews

import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.verticalScroll
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.AddShoppingCart
import androidx.compose.material3.*
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.navigation.compose.rememberNavController
import pt.iade.ei.cobuy.R
import pt.iade.ei.cobuy.network.models.RecipeIngredientDto
import pt.iade.ei.cobuy.ui.components.bottombar.CoBuyBottomBar
import pt.iade.ei.cobuy.ui.components.topbar.CoBuyTopBar
import pt.iade.ei.cobuy.ui.theme.COBUYTheme

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun RecipeDetailScreenUi(
    recipeId: Int = 3,
    title: String = "Receita #3"
) {
    val nav = rememberNavController()

    val ingredients = listOf(
        RecipeIngredientDto(id = 1, ingredient = "Azeite", qty = 0.02, unit = "L"),
        RecipeIngredientDto(id = 2, ingredient = "Sal", qty = 8.0, unit = "g"),
        RecipeIngredientDto(id = 3, ingredient = "Pimenta preta", qty = 3.0, unit = "g")
    )

    val steps = listOf(
        "Refogue cebola e alho em azeite, junte a carne picada e tempere.",
        "Adicione o molho de tomate e deixe cozinhar alguns minutos.",
        "Prepare o molho bechamel com leite, manteiga e farinha.",
        "Num tabuleiro, faça camadas alternadas de lasanha, carne, bechamel e queijo.",
        "Leve ao forno a 180 ºC durante 25–30 minutos."
    )

    Scaffold(
        topBar = {
            CoBuyTopBar(
                title = title,
                navController = nav,
                showBackButton = true
            )
        },
        bottomBar = { CoBuyBottomBar(nav) }
    ) { padding ->

        Column(
            modifier = Modifier
                .padding(padding)
                .fillMaxSize()
        ) {

            Image(
                painter = painterResource(id = R.drawable.lasanha), // troca para o drawable que tens
                contentDescription = title,
                modifier = Modifier
                    .fillMaxWidth()
                    .height(220.dp),
                contentScale = ContentScale.Crop
            )

            Column(
                modifier = Modifier
                    .fillMaxSize()
                    .padding(16.dp)
                    .verticalScroll(rememberScrollState())
            ) {
                Text(
                    text = title,
                    style = MaterialTheme.typography.headlineMedium,
                    fontWeight = FontWeight.Bold
                )

                Spacer(Modifier.height(16.dp))

                Text(
                    text = "Ingredientes",
                    style = MaterialTheme.typography.titleMedium,
                    fontWeight = FontWeight.SemiBold
                )

                Spacer(Modifier.height(8.dp))

                ingredients.forEach { ing ->
                    Row(
                        modifier = Modifier
                            .fillMaxWidth()
                            .padding(vertical = 4.dp),
                        verticalAlignment = Alignment.CenterVertically
                    ) {
                        Column(modifier = Modifier.weight(1f)) {
                            Text(text = ing.ingredient)
                            Text(text = "${ing.qty} ${ing.unit}")
                        }
                        IconButton(onClick = {}) {
                            Icon(Icons.Default.AddShoppingCart, contentDescription = "Adicionar")
                        }
                    }
                }

                Spacer(Modifier.height(16.dp))

                Text(
                    text = "Modo de preparação",
                    style = MaterialTheme.typography.titleMedium,
                    fontWeight = FontWeight.SemiBold
                )

                Spacer(Modifier.height(8.dp))

                steps.forEachIndexed { index, step ->
                    Text(text = "${index + 1}. $step")
                }
            }
        }
    }
}

@Preview(showBackground = true, device = "spec:width=411dp,height=891dp")
@Composable
fun RecipeDetailScreenUiPreview() {
    COBUYTheme {
        RecipeDetailScreenUi()
    }
}
