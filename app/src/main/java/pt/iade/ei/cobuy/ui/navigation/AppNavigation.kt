package pt.iade.ei.cobuy.ui.navigation

import androidx.compose.runtime.Composable
import androidx.navigation.compose.NavHost

fun appNavigation() {
    val navController = rememberNavController()
    NavHost(navController, startDestination = NavPath.Login.route) {
        composable(NavPath.Login.route) { LoginScreen(navController) }
        composable(NavPath.Register.route) { RegisterScreen(navController) }
        composable(NavPath.Home.route) { HomeScreen(navController) }
        composable(NavPath.ShoppingList.route) { ShoppingListScreen(navController) }
        composable(NavPath.Recipes.route) { RecipeScreen(navController) }
        composable(NavPath.Map.route) { MapScreen(navController) }
    }
}