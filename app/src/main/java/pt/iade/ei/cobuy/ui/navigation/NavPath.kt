package pt.iade.ei.cobuy.ui.navigation

sealed class NavPath(val route: String) {
    object Login : NavPath("login")
    object Register : NavPath("register")
    object Home : NavPath("home")
    object ShoppingList : NavPath("list")
    object Recipes : NavPath("recipes")
    object Map : NavPath("map")
}
