package pt.iade.ei.cobuy.ui.screens

import android.annotation.SuppressLint
import androidx.activity.compose.BackHandler
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Groups
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.draw.shadow
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.lifecycle.viewmodel.compose.viewModel
import androidx.navigation.NavController
import androidx.navigation.compose.rememberNavController
import pt.iade.ei.cobuy.R
import pt.iade.ei.cobuy.network.viewmodels.auth.AuthViewModel
import pt.iade.ei.cobuy.network.viewmodels.auth.AuthViewModelFactory
import pt.iade.ei.cobuy.network.viewmodels.groups.GroupViewModel
import pt.iade.ei.cobuy.network.viewmodels.maps.SavedPlaceViewModel
import pt.iade.ei.cobuy.ui.components.bottombar.CoBuyBottomBar
import pt.iade.ei.cobuy.ui.components.buttons.CustomOutlinedButton
import pt.iade.ei.cobuy.ui.components.buttons.GroupsButton
import pt.iade.ei.cobuy.ui.components.cards.StatusCard
import pt.iade.ei.cobuy.ui.navigation.NavPath
import pt.iade.ei.cobuy.ui.screens.previews.DashboardScreenUi
import pt.iade.ei.cobuy.ui.theme.COBUYTheme
import pt.iade.ei.cobuy.ui.theme.OrangePrimary
import pt.iade.ei.cobuy.ui.theme.TextDark

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun DashboardScreen(
    navController: NavController,
    savedPlaceViewModel: SavedPlaceViewModel
) {
    val context = LocalContext.current

    val groupViewModel: GroupViewModel = viewModel()
    val authViewModel: AuthViewModel = viewModel(
        factory = AuthViewModelFactory(context)
    )

    val user by authViewModel.currentUser
    var groupCount by remember { mutableStateOf(0) }

    val savedPlaces by savedPlaceViewModel.savedPlaces.collectAsState()
    val savedLocationsCount = savedPlaces.size

    BackHandler(enabled = true) { }

    LaunchedEffect(Unit) {
        authViewModel.loadUser()
    }

    LaunchedEffect(user?.id) {
        if (user != null) {
            savedPlaceViewModel.clear()
            savedPlaceViewModel.load()

            groupViewModel.getUserGroups { result, error ->
                if (error == null) {
                    groupCount = result?.size ?: 0
                }
            }
        } else {
            savedPlaceViewModel.clear()
            groupCount = 0
        }
    }

    val firstName = user?.name?.substringBefore(" ") ?: "Utilizador"

    val initials = user?.name
        ?.split(" ")
        ?.filter { it.isNotBlank() }
        ?.take(2)
        ?.joinToString("") { it.first().uppercaseChar().toString() }
        ?: "US"

    Scaffold(
        topBar = {
            TopAppBar(
                title = {
                    GroupsButton(
                        onClick = {
                            navController.navigate(NavPath.MyGroups.route) {
                                launchSingleTop = true
                            }
                        }
                    )
                },
                actions = {
                    IconButton(
                        onClick = {
                            navController.navigate(NavPath.Profile.route) {
                                launchSingleTop = true
                            }
                        }
                    ) {
                        Box(
                            modifier = Modifier
                                .size(32.dp)
                                .clip(CircleShape)
                                .background(OrangePrimary)
                        ) {
                            Text(
                                text = initials,
                                color = Color.White,
                                fontWeight = FontWeight.Bold,
                                fontSize = 14.sp,
                                modifier = Modifier.align(Alignment.Center)
                            )
                        }
                    }
                },
                colors = TopAppBarDefaults.topAppBarColors(
                    containerColor = Color.White
                )
            )
        },
        bottomBar = {
            CoBuyBottomBar(navController)
        }
    ) { paddingValues ->

        Column(
            modifier = Modifier
                .fillMaxSize()
                .padding(paddingValues)
                .padding(horizontal = 32.dp),
            horizontalAlignment = Alignment.CenterHorizontally,
            verticalArrangement = Arrangement.Center
        ) {

            Image(
                painter = painterResource(id = R.drawable.logo),
                contentDescription = "App Logo",
                modifier = Modifier
                    .size(130.dp)
                    .padding(bottom = 16.dp)
            )

            Text(
                text = "Bem-vindo, $firstName!",
                fontWeight = FontWeight.Bold,
                color = TextDark,
                fontSize = 22.sp
            )

            Spacer(modifier = Modifier.height(6.dp))

            Text(
                text = "Pronto para comprar com o seu grupo?",
                color = TextDark.copy(alpha = 0.7f),
                fontSize = 14.sp,
                textAlign = TextAlign.Center
            )

            Spacer(modifier = Modifier.height(36.dp))

            Row(
                horizontalArrangement = Arrangement.SpaceEvenly,
                verticalAlignment = Alignment.CenterVertically,
                modifier = Modifier.fillMaxWidth()
            ) {
                StatusCard(title = "Grupos", value = groupCount.toString())
                StatusCard(
                    title = "Locais Salvos",
                    value = savedLocationsCount.toString()
                )
            }

            Spacer(modifier = Modifier.height(48.dp))

            CustomOutlinedButton(
                text = "Entrar em grupo",
                onClick = {
                    navController.navigate(NavPath.JoinGroup.route) {
                        launchSingleTop = true
                    }
                }
            )

            Spacer(modifier = Modifier.height(20.dp))

            CustomOutlinedButton(
                text = "Criar Novo Grupo",
                onClick = {
                    navController.navigate(NavPath.CreateGroup.route) {
                        launchSingleTop = true
                    }
                }
            )
        }
    }
}

@Preview(
    showBackground = true,
    device = "spec:width=411dp,height=891dp"
)
@Composable
fun DashboardScreenUiPreview() {
    DashboardScreenUi()
}
