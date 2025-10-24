package pt.iade.ei.cobuy.ui.navigation

import androidx.compose.runtime.Composable
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import pt.iade.ei.cobuy.ui.screens.*

@Composable
fun AppNavigation() {
    val navController = rememberNavController()

    NavHost(
        navController = navController,
        startDestination = NavPath.Home.route
    ) {
        composable(NavPath.Home.route) { HomeScreen(navController) }
        composable(NavPath.Register.route) { RegisterScreen(navController) }
        composable(NavPath.Login.route) { LoginScreen(navController) }
        composable(NavPath.Dashboard.route) { DashboardScreen(navController) }
        composable(NavPath.Profile.route) { ProfileScreen(navController) }
        composable(NavPath.JoinGroup.route) { JoinGroupScreen(navController) }
        composable(NavPath.CreateGroup.route) { CreateGroupScreen(navController) }
        composable(NavPath.GroupDetail.route) { GroupDetailScreen(navController) }
        composable(NavPath.Map.route) { MapScreen(navController) }
    }
}
