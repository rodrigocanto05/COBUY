package pt.iade.ei.cobuy.ui.navigation

import androidx.compose.runtime.Composable
import androidx.navigation.NavType
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import androidx.navigation.navArgument
import pt.iade.ei.cobuy.ui.screens.*
import pt.iade.ei.cobuy.ui.screens.recipesingredients.*

@Composable
fun appNavigation() {
    val navController = rememberNavController()

    NavHost(
        navController = navController,
        startDestination = NavPath.Home.route
    ) {

        // *************** MAIN SCREENS ***************
        composable(NavPath.Home.route) { HomeScreen(navController) }
        composable(NavPath.Login.route) { LoginScreen(navController) }
        composable(NavPath.Register.route) { RegisterScreen(navController) }
        composable(NavPath.Dashboard.route) { DashboardScreen(navController) }
        composable(NavPath.Map.route) { MapScreen(navController) }
        composable(NavPath.Profile.route) { ProfileScreen(navController) }
        composable(NavPath.CreateGroup.route) { CreateGroupScreen(navController) }
        composable(NavPath.JoinGroup.route) { JoinGroupScreen(navController) }
        composable(NavPath.EditProfile.route) { EditProfileScreen(navController) }

        // MyGroups recebe por agora userId fixo (ajustas depois com auth)
        composable(NavPath.MyGroups.route) {
            MyGroupsScreen(navController, userId = 1)
        }

        composable(NavPath.SavedLocations.route) { SavedLocationsScreen(navController) }
        composable(NavPath.EditSettings.route) { EditSettingsScreen(navController) }

        // *************** GROUP DETAIL ***************
        composable(
            route = "group_detail/{groupId}",
            arguments = listOf(
                navArgument("groupId") { type = NavType.IntType }
            )
        ) { backStackEntry ->
            val groupId = backStackEntry.arguments?.getInt("groupId") ?: 0
            GroupDetailScreen(navController, groupId)
        }

        // *************** MY LISTS (LISTAS DO GRUPO) ***************
        composable(
            route = NavPath.MyLists.route,
            arguments = listOf(
                navArgument("groupId") { type = NavType.IntType },
                navArgument("groupName") { type = NavType.StringType }
            )
        ) { backStackEntry ->
            val groupId = backStackEntry.arguments?.getInt("groupId") ?: 0
            val groupName = backStackEntry.arguments?.getString("groupName") ?: ""
            val userId = 1 // TODO: trocar pelo user autenticado

            MyListsScreen(
                navController = navController,
                groupId = groupId,
                groupName = groupName,
                userId = userId
            )
        }



        // *************** RECIPES HOME ***************
        composable(NavPath.Recipes.route) {
            RecipesScreen(navController)
        }

        // *************** INDIVIDUAL RECIPE SCREENS ***************
        composable("massacarbonara") {
            MassaCarbonaraScreen(
                navController = navController,
                onAddIngredientToShoppingList = {}
            )
        }

        composable("frangogrelhado") {
            FrangoGrelhadoScreen(
                navController = navController,
                onAddIngredientToShoppingList = {}
            )
        }

        composable("lasanhadecarne") {
            LasanhadeCarneScreen(
                navController = navController,
                onAddIngredientToShoppingList = {}
            )
        }

        composable("arrozmarisco") {
            ArrozdeMarisco(
                navController = navController,
                onAddIngredientToShoppingList = {}
            )
        }

        composable("bacalhaabras") {
            BacalhaaBrasScreen(
                navController = navController,
                onAddIngredientToShoppingList = {}
            )
        }

        composable("salmaonoforno") {
            SalmãonoFornocomBatatasScreen(
                navController = navController,
                onAddIngredientToShoppingList = {}
            )
        }

        composable("salamechocolate") {
            SalamedeChocolateScreen(
                navController = navController,
                onAddIngredientToShoppingList = {}
            )
        }

        composable("chilicomcarne") {
            ChilicomCarneScreen(
                navController = navController,
                onAddIngredientToShoppingList = {}
            )
        }

        composable("panquecas") {
            PanquecasScreen(
                navController = navController,
                onAddIngredientToShoppingList = {}
            )
        }

        composable("omeletequeijo") {
            OmeletedeQueijoeFiambreScreen(
                navController = navController,
                onAddIngredientToShoppingList = {}
            )
        }

        composable("sopadelegumes") {
            SopadeLegumesScreen(
                navController = navController,
                onAddIngredientToShoppingList = {}
            )
        }

        composable("tostamista") {
            TostaMistaScreen(
                navController = navController,
                onAddIngredientToShoppingList = {}
            )
        }
    }
}
