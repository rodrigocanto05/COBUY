package pt.iade.ei.cobuy.ui.navigation

import androidx.compose.runtime.Composable
import androidx.navigation.NavType
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import androidx.navigation.navArgument
import pt.iade.ei.cobuy.ui.screens.*

@Composable
fun appNavigation() {
    val navController = rememberNavController()

    NavHost(navController = navController, startDestination = NavPath.Home.route) {
        composable(NavPath.Home.route) { HomeScreen(navController) }
        composable(NavPath.Login.route) { LoginScreen(navController) }
        composable(NavPath.Register.route) { RegisterScreen(navController) }
        composable(NavPath.Dashboard.route) { DashboardScreen(navController) }
        composable(NavPath.Map.route) { MapScreen(navController) }
        composable(NavPath.Profile.route) { ProfileScreen(navController) }
        composable(NavPath.CreateGroup.route) { CreateGroupScreen(navController) }
        composable(NavPath.JoinGroup.route) { JoinGroupScreen(navController) }
        composable(NavPath.GroupList.route) { GroupListScreen(navController) }
        composable(NavPath.EditProfile.route) { EditProfileScreen(navController) }
        composable(NavPath.SavedLocations.route) { SavedLocationsScreen(navController) }
        composable(
            route = NavPath.GroupDetail.route,
            arguments = listOf(navArgument("groupId") { type = NavType.StringType })
        ) { backStackEntry ->
            val groupId = backStackEntry.arguments?.getString("groupId")
            GroupDetailScreen(navController, groupId)
        }
    }
}
