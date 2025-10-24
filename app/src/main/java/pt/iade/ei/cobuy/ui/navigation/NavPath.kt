package pt.iade.ei.cobuy.ui.navigation

sealed class NavPath(val route: String) {
    object Home : NavPath("home")
    object Register : NavPath("register")
    object Login : NavPath("login")
    object Dashboard : NavPath("dashboard")
    object Profile : NavPath("profile")
    object JoinGroup : NavPath("joinGroup")
    object CreateGroup : NavPath("createGroup")
    object GroupDetail : NavPath("groupDetail")
    object Map : NavPath("map")
}
