package pt.iade.ei.cobuy.ui.navigation

import androidx.compose.runtime.*
import androidx.lifecycle.viewmodel.compose.viewModel
import androidx.navigation.NavType
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import androidx.navigation.navArgument
import pt.iade.ei.cobuy.network.viewmodels.SessionViewModel
import pt.iade.ei.cobuy.network.viewmodels.maps.SavedPlaceViewModel
import pt.iade.ei.cobuy.ui.screens.DashboardScreen
import pt.iade.ei.cobuy.ui.screens.Auth.HomeScreen
import pt.iade.ei.cobuy.ui.screens.Auth.LoginScreen
import pt.iade.ei.cobuy.ui.screens.Auth.RegisterScreen
import pt.iade.ei.cobuy.ui.screens.group.CreateGroupScreen
import pt.iade.ei.cobuy.ui.screens.group.GroupDetailScreen
import pt.iade.ei.cobuy.ui.screens.group.JoinGroupScreen
import pt.iade.ei.cobuy.ui.screens.group.MyGroupsScreen
import pt.iade.ei.cobuy.ui.screens.list.GroupListScreen
import pt.iade.ei.cobuy.ui.screens.list.ListItemsScreen
import pt.iade.ei.cobuy.ui.screens.map.MapScreen
import pt.iade.ei.cobuy.ui.screens.map.SavedLocationsScreen
import pt.iade.ei.cobuy.ui.screens.profile.EditProfileScreen
import pt.iade.ei.cobuy.ui.screens.profile.EditSettingsScreen
import pt.iade.ei.cobuy.ui.screens.profile.ProfileScreen
import pt.iade.ei.cobuy.ui.screens.recipe.RecipeDetailScreen
import pt.iade.ei.cobuy.ui.screens.recipe.RecipesScreen

@Composable
fun appNavigation() {
    val navController = rememberNavController()
    val savedPlaceViewModel: SavedPlaceViewModel = viewModel()

    val startDestination = if (SessionViewModel.currentUserId == null) {
        NavPath.Home.route
    } else {
        NavPath.Dashboard.route
    }

    NavHost(
        navController = navController,
        startDestination = startDestination
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
            DashboardScreen(navController, savedPlaceViewModel)
        }

        composable(NavPath.Map.route) {
            MapScreen(navController)
        }

        composable(NavPath.SavedLocations.route) {
            SavedLocationsScreen(navController, savedPlaceViewModel)
        }

        composable(NavPath.Profile.route) {
            ProfileScreen(navController)
        }

        composable(NavPath.EditProfile.route) {
            EditProfileScreen(navController)
        }

        composable(NavPath.EditSettings.route) {
            EditSettingsScreen(navController)
        }

        composable(NavPath.CreateGroup.route) {
            CreateGroupScreen(navController)
        }

        composable(NavPath.JoinGroup.route) {
            JoinGroupScreen(navController)
        }

        composable(NavPath.MyGroups.route) {
            MyGroupsScreen(navController)
        }

        composable(
            route = NavPath.GroupDetail.route,
            arguments = listOf(navArgument("groupId") { type = NavType.IntType })
        ) { backStackEntry ->
            val groupId = backStackEntry.arguments?.getInt("groupId") ?: 0
            GroupDetailScreen(navController, groupId)
        }

        composable(
            route = NavPath.MyLists.route,
            arguments = listOf(
                navArgument("groupId") { type = NavType.IntType },
                navArgument("groupName") { type = NavType.StringType }
            )
        ) { backStackEntry ->
            val groupId = backStackEntry.arguments?.getInt("groupId") ?: 0
            val groupName = backStackEntry.arguments?.getString("groupName") ?: ""
            GroupListScreen(navController, groupId, groupName)
        }

        composable(
            route = NavPath.ListItems.route,
            arguments = listOf(
                navArgument("listId") { type = NavType.IntType },
                navArgument("listName") { type = NavType.StringType }
            )
        ) { backStackEntry ->
            val listId = backStackEntry.arguments?.getInt("listId") ?: 0
            val listName = backStackEntry.arguments?.getString("listName") ?: ""
            ListItemsScreen(navController, listId, listName)
        }

        composable(NavPath.Recipes.route) {
            RecipesScreen(navController)
        }

        composable(
            route = NavPath.RecipeDetail.route,
            arguments = listOf(navArgument("recipeId") { type = NavType.IntType })
        ) { backStackEntry ->
            val recipeId = backStackEntry.arguments?.getInt("recipeId") ?: return@composable
            RecipeDetailScreen(navController, recipeId)
        }
    }
}
