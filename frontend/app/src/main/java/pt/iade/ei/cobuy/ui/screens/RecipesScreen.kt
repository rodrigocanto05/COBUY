package pt.iade.ei.cobuy.ui.screens

import androidx.compose.foundation.ExperimentalFoundationApi
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.grid.GridCells
import androidx.compose.foundation.lazy.grid.LazyVerticalGrid
import androidx.compose.foundation.lazy.grid.items
import androidx.compose.foundation.shape.RoundedCornerShape
import pt.iade.ei.cobuy.ui.components.bottombar.CoBuyBottomBar
import androidx.compose.ui.tooling.preview.Preview
import androidx.navigation.compose.rememberNavController
import androidx.compose.material3.*
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavController
import pt.iade.ei.cobuy.R

// Modelo de Receita (podes depois mover para outro ficheiro)
data class Recipe(
    val id: Int,
    val name: String,
    val imageRes: Int
)

// Lista das 20 receitas (substitui as imagens pelas tuas)
private val recipeList = listOf(
    Recipe(1, "Massa Carbonara", R.drawable.massa_carbonara),
    Recipe(2, "Frango Grelhado", R.drawable.frangogrelhado),
    Recipe(3, "Lasanha de Carne", R.drawable.lasanha),
    Recipe(4, "Arroz de Marisco", R.drawable.arroz_de_marsico),
    Recipe(5, "Bacalhau à Brás", R.drawable.bacalhauabras),
    Recipe(6, "Salmão no Forno com Batatas", R.drawable.salmaonoforno),
    Recipe(7, "Salame de Chocolate", R.drawable.salamedechocolate),
    Recipe(8, "Chili com Carne", R.drawable.chilicomcarne),
    Recipe(9, "Panquecas", R.drawable.panquecas),
    Recipe(10, "Omelete de Queijo e Fiambre", R.drawable.omelete),
    Recipe(11, "Sopa de Legumes", R.drawable.sopadelegumes),
    Recipe(12, "Tosta Mista", R.drawable.tostamista),
    Recipe(13, "Wrap de Frango com Alface", R.drawable.wrapdefrango),
    Recipe(14, "Hambúrguer Caseiro", R.drawable.haumburguer),
    Recipe(15, "Pizza Caseira", R.drawable.pizza),
    Recipe(16, "Esparguete à Bolonhesa", R.drawable.bolonhesa),
    Recipe(17, "Arroz Doce", R.drawable.arrozdoce),
    Recipe(18, "Gelatina com Iogurte", R.drawable.gelatinacomiogurte),
    Recipe(19, "Salada Mediterrânia", R.drawable.saladamediterranea),
    Recipe(20, "Bolo de Chocolate", R.drawable.bolodechocolate)
)

@OptIn(ExperimentalMaterial3Api::class, ExperimentalFoundationApi::class)
@Composable
fun RecipesScreen(navController: NavController) {
    Scaffold(
        topBar = {
            CenterAlignedTopAppBar(
                title = { Text("Receitas") }
            )
        },
        bottomBar = {
            CoBuyBottomBar(navController)   // ← AQUI ESTÁ A CORRETA
        }
    ) { padding ->
        LazyVerticalGrid(
            columns = GridCells.Fixed(2),
            modifier = Modifier
                .padding(padding)
                .fillMaxSize()
                .padding(horizontal = 12.dp, vertical = 8.dp),
            verticalArrangement = Arrangement.spacedBy(12.dp),
            horizontalArrangement = Arrangement.spacedBy(12.dp),
            contentPadding = PaddingValues(bottom = 60.dp)
        ) {
            items(recipeList) { recipe ->
                RecipeCard(recipe)
            }
        }
    }
}

@Composable
fun RecipeCard(recipe: Recipe) {
    Column(
        modifier = Modifier
            .clickable { /* navController.navigate(...) */ }
            .background(Color(0xFFF3F3F3), RoundedCornerShape(12.dp))
            .padding(10.dp)
    ) {
        Image(
            painter = painterResource(recipe.imageRes),
            contentDescription = recipe.name,
            contentScale = ContentScale.Crop,
            modifier = Modifier
                .fillMaxWidth()
                .height(120.dp)
                .clip(RoundedCornerShape(12.dp))
        )

        Spacer(modifier = Modifier.height(8.dp))

        Text(
            text = recipe.name,
            fontSize = 15.sp,
            fontWeight = FontWeight.Bold,
            modifier = Modifier.fillMaxWidth(),
            textAlign = TextAlign.Center
        )
    }
}
@Preview(showBackground = true, showSystemUi = true)
@Composable
fun RecipesScreenPreview() {
    RecipesScreen(navController = rememberNavController())
}