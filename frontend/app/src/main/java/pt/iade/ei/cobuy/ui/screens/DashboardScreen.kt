package pt.iade.ei.cobuy.ui.screens

import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Groups
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
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
import pt.iade.ei.cobuy.network.viewmodels.AuthViewModel
import pt.iade.ei.cobuy.network.viewmodels.AuthViewModelFactory
import pt.iade.ei.cobuy.network.viewmodels.GroupViewModel
import pt.iade.ei.cobuy.ui.components.bottombar.CoBuyBottomBar
import pt.iade.ei.cobuy.ui.components.buttons.CustomOutlinedButton
import pt.iade.ei.cobuy.ui.components.cards.StatusCard
import pt.iade.ei.cobuy.ui.navigation.NavPath
import pt.iade.ei.cobuy.ui.theme.OrangePrimary
import pt.iade.ei.cobuy.ui.theme.TextDark

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun DashboardScreen(navController: NavController, userId: Int = 1) {

    // ViewModels
    val groupViewModel: GroupViewModel = viewModel()
    val authViewModel: AuthViewModel = viewModel(
        factory = AuthViewModelFactory(LocalContext.current)
    )

    val user by authViewModel.currentUser

    var groupCount by remember { mutableStateOf(0) }

    // Carrega info do user e dos grupos apenas 1 vez
    LaunchedEffect(Unit) {
        authViewModel.loadUser()

        groupViewModel.getUserGroups(userId) { result, error ->
            if (error == null) groupCount = result?.size ?: 0
        }
    }

    // Obter primeiro nome
    val firstName = user?.name?.substringBefore(" ") ?: "Utilizador"

    // Obter iniciais (2 primeiras letras úteis)
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
                    Row(
                        verticalAlignment = Alignment.CenterVertically,
                        modifier = Modifier.clickable {
                            navController.navigate(NavPath.MyGroups.route)
                        }
                    ) {
                        Icon(
                            imageVector = Icons.Default.Groups,
                            contentDescription = "Grupos",
                            tint = Color.Black
                        )
                        Spacer(modifier = Modifier.width(8.dp))
                        Text(
                            text = "Grupos",
                            color = Color.Black,
                            fontWeight = FontWeight.SemiBold,
                            fontSize = 18.sp
                        )
                    }
                },
                actions = {
                    IconButton(onClick = { navController.navigate(NavPath.Profile.route) }) {
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

            // LOGO
            Image(
                painter = painterResource(id = R.drawable.logo),
                contentDescription = "App Logo",
                modifier = Modifier
                    .size(130.dp)
                    .padding(bottom = 16.dp)
            )

            // FRASE DE BOAS-VINDAS
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

            // STATUS
            Row(
                horizontalArrangement = Arrangement.SpaceEvenly,
                verticalAlignment = Alignment.CenterVertically,
                modifier = Modifier.fillMaxWidth()
            ) {
                StatusCard(title = "Grupos", value = groupCount.toString())
                StatusCard(title = "Locais Salvos", value = "5")
            }

            Spacer(modifier = Modifier.height(48.dp))

            // BOTÕES
            CustomOutlinedButton("Entrar em grupo") {
                navController.navigate(NavPath.JoinGroup.route)
            }

            Spacer(modifier = Modifier.height(20.dp))

            CustomOutlinedButton("Criar Novo Grupo") {
                navController.navigate(NavPath.CreateGroup.route)
            }
        }
    }
}

@Preview(showBackground = true)
@Composable
fun DashboardScreenPreview() {
    val navController = rememberNavController()
    DashboardScreen(navController)
}
