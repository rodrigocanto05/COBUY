package pt.iade.ei.cobuy.ui.navigation

sealed class NavPath(val route: String) {
    object Login : NavPath("login")
    object Register : NavPath("register")
    object Home : NavPath("home")
    object Dashboard : NavPath("dashboard")
    object Map : NavPath("map")
    object Profile : NavPath("profile")
    object CreateGroup : NavPath("create_group")
    object JoinGroup : NavPath("join_group")
    object GroupList : NavPath("group_list")
    object EditProfile : NavPath("edit_profile")
    object SavedLocations : NavPath("saved_locations")

    object GroupDetail : NavPath("group_detail/{groupId}") {
        fun withArgs(groupId: String): String {
            return this.route.replace("{groupId}", groupId)
        }
    }
}
