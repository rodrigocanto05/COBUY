package pt.iade.ei.cobuy.ui.navigation

import androidx.compose.runtime.Composable
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import pt.iade.ei.cobuy.ui.screens.*

@Composable
fun appNavigation() {
    val navController = rememberNavController()

    NavHost(navController = navController, startDestination = NavPath.Login.route) {
        composable(NavPath.Login.route) { LoginScreen(navController) }
        composable(NavPath.Register.route) { RegisterScreen(navController) }
        composable(NavPath.Home.route) { HomeScreen(navController) }
        composable(NavPath.Dashboard.route) { DashboardScreen(navController) }
        composable(NavPath.Map.route) { MapScreen(navController) }
        composable(NavPath.Profile.route) { ProfileScreen(navController) }
        composable(NavPath.Welcome.route) { WelcomeScreen(navController) }

    }
}
