package pt.iade.ei.cobuy.ui.screens

import androidx.compose.foundation.BorderStroke
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Add
import androidx.compose.material.icons.filled.Groups
import androidx.compose.material.icons.filled.Person
import androidx.compose.material.icons.filled.Place
import androidx.compose.material3.*
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavController
import androidx.navigation.compose.rememberNavController
import pt.iade.ei.cobuy.R
import pt.iade.ei.cobuy.ui.components.buttons.CustomOutlinedButton
import pt.iade.ei.cobuy.ui.components.cards.StatusCard
import pt.iade.ei.cobuy.ui.navigation.NavPath
import pt.iade.ei.cobuy.ui.theme.OrangePrimary
import pt.iade.ei.cobuy.ui.theme.TextDark
import pt.iade.ei.cobuy.ui.theme.TextLight

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun DashboardScreen(navController: NavController) {
    Scaffold(
        topBar = {
            TopAppBar(
                title = {
                    Row(
                        verticalAlignment = Alignment.CenterVertically,
                        modifier = Modifier.clickable {
                            navController.navigate(NavPath.GroupList.route)
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
                                "JS",
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
            BottomAppBar(
                containerColor = Color.White,
                modifier = Modifier.height(64.dp)
            ) {
                // O BottomAppBar fica vazio para criar espaço para o FAB
            }
        },
        floatingActionButton = {
            FloatingActionButton(
                onClick = { navController.navigate(NavPath.Map.route) },
                containerColor = OrangePrimary,
                shape = CircleShape,
                modifier = Modifier
                    .size(64.dp)
            ) {
                Icon(
                    imageVector = Icons.Default.Place,
                    contentDescription = "Mapa",
                    tint = TextLight,
                    modifier = Modifier.size(32.dp)
                )
            }
        },
        floatingActionButtonPosition = FabPosition.Center
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
                painter = painterResource(id = R.drawable.image),
                contentDescription = "App Logo",
                modifier = Modifier
                    .size(130.dp)
                    .padding(bottom = 16.dp)
            )

            // TÍTULO
            Text(
                text = "Bem-vindo, João!",
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

            // STATUS CARDS
            Row(
                horizontalArrangement = Arrangement.SpaceEvenly,
                verticalAlignment = Alignment.CenterVertically,
                modifier = Modifier.fillMaxWidth()
            ) {
                StatusCard(title = "Grupos", value = "3")
                StatusCard(title = "Locais Salvos", value = "5")
            }

            Spacer(modifier = Modifier.height(48.dp))

            // BOTÃO “ENTRAR EM GRUPO”
            CustomOutlinedButton("Entrar em grupo") { navController.navigate(NavPath.JoinGroup.route) }

            Spacer(modifier = Modifier.height(20.dp))

            // BOTÃO “CRIAR NOVO GRUPO”
            CustomOutlinedButton("Criar Novo Grupo") { navController.navigate(NavPath.CreateGroup.route) }
        }
    }
}

@Preview(showBackground = true)
@Composable
fun DashboardScreenPreview() {
    val navController = rememberNavController()
    DashboardScreen(navController)
}
