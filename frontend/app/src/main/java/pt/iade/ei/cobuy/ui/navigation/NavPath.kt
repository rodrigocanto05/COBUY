package pt.iade.ei.cobuy.ui.navigation

sealed class NavPath(val route: String) {

    object Login : NavPath("login")
    object Register : NavPath("register")

    object Home : NavPath("home")
    object Dashboard : NavPath("dashboard")
    object Map : NavPath("map")
    object Profile : NavPath("profile")
    object EditProfile : NavPath("edit_profile")
    object SavedLocations : NavPath("saved_locations")

    object CreateGroup : NavPath("create_group")
    object JoinGroup : NavPath("join_group")

    object MyGroups : NavPath("my_groups")
    object EditSettings : NavPath("edit_settings")

    object GroupDetail : NavPath("group_detail/{groupId}") {
        fun withArgs(groupId: String): String {
            return this.route.replace("{groupId}", groupId)
        }
    }
}
