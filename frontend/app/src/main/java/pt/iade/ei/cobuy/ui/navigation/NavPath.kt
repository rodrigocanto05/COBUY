package pt.iade.ei.cobuy.ui.navigation

sealed class NavPath(val route: String) {
    object Login : NavPath("login")
    object Register : NavPath("register")
    object Home : NavPath("home")
    object Dashboard : NavPath("dashboard")
    object Map : NavPath("map")
    object Profile : NavPath("profile")
    object Welcome : NavPath("welcome")

}
