package pt.iade.ei.cobuy.ui.navigation

import androidx.compose.runtime.Composable
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import pt.iade.ei.cobuy.ui.screens.*

@Composable
fun appNavigation() {
    val navController = rememberNavController()

    NavHost(
        navController = navController,
        startDestination = NavPath.Home.route
    ) {

        composable(NavPath.Home.route) {
            HomeScreen(navController)
        }

        composable(NavPath.Login.route) {
            LoginScreen(navController)
        }

        composable(NavPath.Register.route) {
            RegisterScreen(navController)
        }

        composable(NavPath.Dashboard.route) {
            DashboardScreen(navController)
        }

        composable(NavPath.Map.route) {
            MapScreen(navController)
        }

        composable(NavPath.Profile.route) {
            ProfileScreen(navController)
        }

        composable(NavPath.CreateGroup.route) {
            CreateGroupScreen(navController)
        }

        composable(NavPath.JoinGroup.route) {
            JoinGroupScreen(navController)
        }

        composable(NavPath.EditProfile.route) {
            EditProfileScreen(navController)
        }

        composable(NavPath.MyGroups.route) {
            MyGroupsScreen(navController, userId = 1)
        }

        composable(NavPath.SavedLocations.route) {
            SavedLocationsScreen(navController)
        }


        // *************** GROUP DETAIL ROUTE ***************
        composable("group_detail/{groupId}") { backStackEntry ->
            val groupId = backStackEntry.arguments?.getString("groupId")!!.toInt()
            GroupDetailScreen(navController, groupId)
        }

        // *************** RECIPES ***************
        composable(NavPath.Recipes.route) {
            RecipesScreen(navController)
        }
    }
}
