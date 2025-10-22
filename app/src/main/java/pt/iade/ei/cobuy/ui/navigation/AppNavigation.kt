package pt.iade.ei.cobuy.ui.navigation

import androidx.compose.runtime.Composable
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import pt.iade.ei.cobuy.ui.screens.*

@Composable
fun appNavigation() {
    // Cria o controlador de navegação
    val navController = rememberNavController()

    // Define o NavHost (contenedor de todas as rotas)
    NavHost(
        navController = navController,
        startDestination = NavPath.Login.route // Primeira tela a abrir
    ) {
        // As tuas rotas
        composable(NavPath.Login.route) {
            LoginScreen(navController)
        }
        composable(NavPath.Register.route) {
            RegisterScreen(navController)
        }
        composable(NavPath.Home.route) {
            HomeScreen(navController)
        }
        composable(NavPath.ShoppingList.route) {
            ShoppingListScreen(navController)
        }
        composable(NavPath.Recipes.route) {
            RecipeScreen(navController)
        }
        composable(NavPath.Map.route) {
            MapScreen(navController)
        }
    }
}
