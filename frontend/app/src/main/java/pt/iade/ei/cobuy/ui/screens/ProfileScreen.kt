package pt.iade.ei.cobuy.ui.screens

import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.lifecycle.viewmodel.compose.viewModel
import androidx.navigation.NavController
import androidx.navigation.compose.rememberNavController
import pt.iade.ei.cobuy.R
import pt.iade.ei.cobuy.network.viewmodels.AuthViewModel
import pt.iade.ei.cobuy.network.viewmodels.AuthViewModelFactory
import pt.iade.ei.cobuy.ui.components.buttons.CustomOutlinedButton
import pt.iade.ei.cobuy.ui.components.buttons.PrimaryButton
import pt.iade.ei.cobuy.ui.components.topbar.CoBuyTopBar
import pt.iade.ei.cobuy.ui.navigation.NavPath
import pt.iade.ei.cobuy.ui.theme.BackgroundLight
import pt.iade.ei.cobuy.ui.theme.OrangePrimary
import pt.iade.ei.cobuy.ui.theme.TextDark

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun ProfileScreen(
    navController: NavController,
    authViewModel: AuthViewModel = viewModel(
        factory = AuthViewModelFactory(LocalContext.current)
    )
) {
    val user by authViewModel.currentUser

    LaunchedEffect(Unit) {
        authViewModel.loadUser()
    }

    Scaffold(
        topBar = { CoBuyTopBar("Perfil", navController) },
        containerColor = BackgroundLight
    ) { padding ->

        Column(
            modifier = Modifier
                .fillMaxSize()
                .padding(padding)
                .background(Color(0xFFF7ECFF))
                .padding(horizontal = 32.dp),
            horizontalAlignment = Alignment.CenterHorizontally
        ) {

            Spacer(modifier = Modifier.height(30.dp))

            // LOGO (igual ao Dashboard)
            Image(
                painter = painterResource(id = R.drawable.image),
                contentDescription = "Foto de Perfil",
                modifier = Modifier
                    .size(130.dp)
                    .padding(bottom = 16.dp)
            )

            Spacer(Modifier.height(8.dp))

            // CARD
            Card(
                modifier = Modifier
                    .fillMaxWidth(),
                shape = RoundedCornerShape(20.dp),
                colors = CardDefaults.cardColors(containerColor = Color.White),
                elevation = CardDefaults.cardElevation(6.dp)
            ) {
                Column(
                    modifier = Modifier
                        .padding(20.dp)
                        .fillMaxWidth(),
                    horizontalAlignment = Alignment.CenterHorizontally
                ) {

                    Text(
                        text = user?.name ?: "Sem nome disponível",
                        style = MaterialTheme.typography.headlineSmall.copy(
                            color = OrangePrimary,
                            fontWeight = FontWeight.Bold,
                            fontSize = 22.sp
                        )
                    )

                    Spacer(Modifier.height(10.dp))

                    Text(
                        text = "📧 ${user?.email ?: "Sem email disponível"}",
                        style = MaterialTheme.typography.bodyLarge.copy(color = TextDark)
                    )

                    Text(
                        text = "⚧ ${user?.gender ?: "Sem género disponível"}",
                        style = MaterialTheme.typography.bodyLarge.copy(color = TextDark)
                    )
                }
            }

            Spacer(Modifier.height(32.dp))

            // BOTÃO EDITAR PERFIL
            Box(
                modifier = Modifier.fillMaxWidth(),
                contentAlignment = Alignment.Center
            ) {
                Box(
                    modifier = Modifier.fillMaxWidth(0.75f)
                ) {
                    PrimaryButton(
                        text = "Editar Perfil",
                        onClick = { navController.navigate(NavPath.EditProfile.route) }
                    )
                }
            }

            Spacer(Modifier.height(16.dp))

            // BOTÃO EDITAR DEFINIÇÕES
            Box(
                modifier = Modifier.fillMaxWidth(),
                contentAlignment = Alignment.Center
            ) {
                Box(
                    modifier = Modifier.fillMaxWidth(0.75f)
                ) {
                    CustomOutlinedButton(
                        text = "Editar Email/Password",
                        onClick = { navController.navigate(NavPath.EditSettings.route) }
                    )
                }
            }
        }
    }
}


@Preview(showBackground = true)
@Composable
fun ProfileScreenPreview() {
    ProfileScreen(navController = rememberNavController())
}
