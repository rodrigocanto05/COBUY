package pt.iade.ei.cobuy.ui.navigation

import androidx.compose.runtime.Composable
import androidx.navigation.NavType
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import androidx.navigation.navArgument
import pt.iade.ei.cobuy.ui.screens.DashboardScreen
import pt.iade.ei.cobuy.ui.screens.Auth.HomeScreen
import pt.iade.ei.cobuy.ui.screens.Auth.LoginScreen
import pt.iade.ei.cobuy.ui.screens.Auth.RegisterScreen
import pt.iade.ei.cobuy.ui.screens.group.CreateGroupScreen
import pt.iade.ei.cobuy.ui.screens.profile.ProfileScreen
import pt.iade.ei.cobuy.ui.screens.group.GroupDetailScreen
import pt.iade.ei.cobuy.ui.screens.group.GroupListScreen
import pt.iade.ei.cobuy.ui.screens.group.JoinGroupScreen
import pt.iade.ei.cobuy.ui.screens.group.MyGroupsScreen
import pt.iade.ei.cobuy.ui.screens.map.MapScreen
import pt.iade.ei.cobuy.ui.screens.map.SavedLocationsScreen
import pt.iade.ei.cobuy.ui.screens.profile.EditProfileScreen
import pt.iade.ei.cobuy.ui.screens.profile.EditSettingsScreen
import pt.iade.ei.cobuy.ui.screens.recipe.RecipesScreen
import pt.iade.ei.cobuy.ui.screens.recipesingredients.*
import androidx.lifecycle.viewmodel.compose.viewModel
import pt.iade.ei.cobuy.network.viewmodels.maps.SavedPlaceViewModel




@Composable
fun appNavigation() {
    val navController = rememberNavController()
    val savedPlaceViewModel: SavedPlaceViewModel = viewModel()

    NavHost(
        navController = navController,
        startDestination = NavPath.Home.route
    ) {

        composable(NavPath.Home.route) { HomeScreen(navController) }
        composable(NavPath.Login.route) { LoginScreen(navController) }
        composable(NavPath.Register.route) { RegisterScreen(navController) }
        composable(NavPath.Dashboard.route) { DashboardScreen(navController, savedPlaceViewModel) }
        composable(NavPath.Map.route) { MapScreen(navController) }
        composable(NavPath.SavedLocations.route) { SavedLocationsScreen(navController, savedPlaceViewModel) }
        composable(NavPath.Profile.route) { ProfileScreen(navController) }
        composable(NavPath.CreateGroup.route) { CreateGroupScreen(navController) }
        composable(NavPath.JoinGroup.route) { JoinGroupScreen(navController) }
        composable(NavPath.EditProfile.route) { EditProfileScreen(navController) }
        composable(NavPath.MyGroups.route) { MyGroupsScreen(navController) }
        composable(NavPath.EditSettings.route) { EditSettingsScreen(navController) }

        composable(
            route = "group_detail/{groupId}",
            arguments = listOf(
                navArgument("groupId") { type = NavType.IntType }
            )
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
            val userId = 1 // TODO: trocar pelo user autenticado

            GroupListScreen(
                navController = navController,
                groupId = groupId,
                groupName = groupName,
            )
        }



        composable(NavPath.Recipes.route) {
            RecipesScreen(navController)
        }

        composable("massacarbonara") {
            MassaCarbonaraScreen(
                navController = navController,
                onAddIngredientToShoppingList = {}
            )
        }

        composable("arrozdoce") {
            ArrozDoceScreen(
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

        composable("pizzacaseira") {
            PizzaCaseiraScreen(
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

        composable("wrapfrangoalface") {
            WrapdeFrangocomAlfaceScreen(
                navController = navController,
                onAddIngredientToShoppingList = {}
            )
        }

            composable("saladamediterranica") {
                SaladaMediterranicaScreen(
                    navController = navController,
                    onAddIngredientToShoppingList = {}
                )
            }

        composable("hamburgercaseiro") {
            HamburguerCaseiroScreen(
                navController = navController,
                onAddIngredientToShoppingList = {}
            )
        }
        composable("bolodechocolate") {
            BolodeChocolateScreen(
                navController = navController,
                onAddIngredientToShoppingList = {}
            )
        }
        composable("gelatinaiogurte") {
            GelatinacomIogurteScreen(
                navController = navController,
                onAddIngredientToShoppingList = {}
            )
        }
        composable("bolonhesa") {
            EspargueteaBolonhesa(
                navController = navController,
                onAddIngredientToShoppingList = {}
            )
        }
    }
}

