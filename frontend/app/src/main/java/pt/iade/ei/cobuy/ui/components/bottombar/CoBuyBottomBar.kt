package pt.iade.ei.cobuy.ui.components.bottombar

import androidx.compose.animation.core.animateDpAsState
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.draw.shadow
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.navigation.NavController
import androidx.navigation.compose.currentBackStackEntryAsState
import androidx.navigation.compose.rememberNavController
import pt.iade.ei.cobuy.R
import pt.iade.ei.cobuy.ui.navigation.NavPath
import pt.iade.ei.cobuy.ui.theme.COBUYTheme
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxSize
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable

@Composable
fun CoBuyBottomBar(navController: NavController) {

    val items = listOf(
        NavPath.Map.route to R.drawable.ic_location,
        NavPath.Dashboard.route to R.drawable.ic_home,
        NavPath.Recipes.route to R.drawable.ic_recipe
    )

    val navBackStackEntry by navController.currentBackStackEntryAsState()
    val currentRoute = navBackStackEntry?.destination?.route

    Box(
        modifier = Modifier
            .fillMaxWidth()
            .height(90.dp)
            .background(Color.White),
        contentAlignment = Alignment.Center
    ) {
        Row(
            modifier = Modifier
                .fillMaxWidth()
                .padding(horizontal = 50.dp),
            horizontalArrangement = Arrangement.SpaceBetween,
            verticalAlignment = Alignment.CenterVertically
        ) {
            items.forEach { (route, icon) ->

                val isSelected = currentRoute == route

                val offsetY by animateDpAsState(if (isSelected) (-20).dp else 0.dp)
                val size by animateDpAsState(if (isSelected) 40.dp else 28.dp)
                val shadow by animateDpAsState(if (isSelected) 12.dp else 0.dp)

                Box(
                    modifier = Modifier
                        .offset(y = offsetY)
                        .size(60.dp)
                        .clip(CircleShape)
                        .shadow(shadow)
                        .background(if (isSelected) Color.White else Color.Transparent),
                    contentAlignment = Alignment.Center
                ) {
                    IconButton(
                        onClick = {
                            navController.navigate(route) {
                                launchSingleTop = true
                                popUpTo(NavPath.Dashboard.route)
                            }
                        }
                    ) {
                        Icon(
                            painter = painterResource(id = icon),
                            contentDescription = null,
                            tint = Color.Black,
                            modifier = Modifier.size(size)
                        )
                    }
                }
            }
        }
    }
}
@Preview(showBackground = true)
@Composable
fun CoBuyBottomBarPreview() {
    val navController = rememberNavController()

    COBUYTheme {
        Box(Modifier.fillMaxSize()) {

            NavHost(
                navController = navController,
                startDestination = NavPath.Dashboard.route,
                modifier = Modifier.fillMaxSize()
            ) {
                composable(NavPath.Dashboard.route) {}
                composable(NavPath.Map.route) {}
                composable(NavPath.Recipes.route) {}
            }

            Box(
                modifier = Modifier.align(Alignment.BottomCenter)
            ) {
                CoBuyBottomBar(navController = navController)
            }
        }
    }
}
