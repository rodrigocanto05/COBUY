package pt.iade.ei.cobuy.ui.navigation

import androidx.compose.runtime.Composable
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import pt.iade.ei.cobuy.ui.screens. recipesingredients.*
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
        composable(NavPath.EditSettings.route) { EditSettingsScreen(navController) }



        // *************** GROUP DETAIL ROUTE ***************
        composable("group_detail/{groupId}") { backStackEntry ->
            val groupId = backStackEntry.arguments?.getString("groupId")!!.toInt()
            GroupDetailScreen(navController, groupId)
        }

        // *************** RECIPES ***************
        composable(NavPath.Recipes.route) {
            RecipesScreen(navController)
        }
        // *************** INDIVIDUAL RECIPE SCREENS ***************
        composable("massacarbonara") { MassaCarbonaraSreen(navController) }
        composable("frangogrelhado") { FrangoGrelhadoScreen(navController) }
        composable("lasanhadecarne") { LasanhadeCarneScreen(navController) }
        composable("arrozmarisco") { ArrozdeMarisco(navController) }
        composable("bacalhaabras") { BacalhaaBrasScreen(navController) }
        composable("salmaonoforno") { SalmãonoFornocomBatatasScreen(navController) }
        composable("salamechocolate") { SalamedeChocolateScreen(navController) }
        composable("chilicomcarne") { ChilicomCarneScreen(navController) }
        composable("panquecas") { PanquecasScreen(navController) }
        composable("omeletequeijo") { OmeletedeQueijoeFiambreScreen(navController) }
        composable("sopadelegumes") { SopadeLegumesScreen(navController) }
        composable("tostamista") { TostaMistaScreen(navController) }
        composable("wrapfrangoalface") { WrapdeFrangocomAlfaceScreen(navController) }
        composable("hamburgercaseiro") { HamburguerCaseiroScreen(navController) }
        composable("pizzacaseira") { PizzaCaseiraScreen(navController) }
        composable("bolonhesa") { EspargueteaBolonhesa(navController) }
        composable("arrozdoce") { ArrozDoceScreen(navController) }
        composable("gelatinaiogurte") { GelatinacomiogurteScreen(navController) }
        composable("saladamediterranica") { SaladaMediterranicaScreen(navController) }
        composable("bolodechocolate") { BolodeChocolateScreen(navController) }






    }
    }

