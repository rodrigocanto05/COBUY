package pt.iade.ei.cobuy.ui.screens

import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.lifecycle.viewmodel.compose.viewModel
import androidx.navigation.NavController
import pt.iade.ei.cobuy.R
import pt.iade.ei.cobuy.network.viewmodels.AuthViewModel
import pt.iade.ei.cobuy.network.viewmodels.AuthViewModelFactory
import pt.iade.ei.cobuy.ui.components.buttons.PrimaryButton
import pt.iade.ei.cobuy.ui.components.topbar.CoBuyTopBar
import pt.iade.ei.cobuy.ui.navigation.NavPath
import pt.iade.ei.cobuy.ui.theme.*

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun ProfileScreen(
    navController: NavController,
    authViewModel: AuthViewModel = viewModel(
        factory = AuthViewModelFactory(LocalContext.current)
    )
) {
    val user by authViewModel.currentUser

    // Carrega o user quando se entra na screen
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
                .padding(padding),
            verticalArrangement = Arrangement.Center,
            horizontalAlignment = Alignment.CenterHorizontally
        ) {

            // FOTO
            Image(
                painter = painterResource(id = R.drawable.image),
                contentDescription = "Foto de Perfil",
                modifier = Modifier
                    .size(120.dp)
                    .clip(CircleShape)
            )

            Spacer(Modifier.height(20.dp))

            // NOME
            Text(
                text = user?.name ?: "Sem nome disponível",
                style = MaterialTheme.typography.headlineSmall.copy(
                    color = OrangePrimary,
                    fontWeight = FontWeight.SemiBold,
                    fontSize = 22.sp
                )
            )

            Spacer(Modifier.height(12.dp))

            // OUTROS DADOS
            Column(
                horizontalAlignment = Alignment.CenterHorizontally,
                verticalArrangement = Arrangement.spacedBy(6.dp)
            ) {
                Text(
                    text = "📧 ${user?.email ?: "Sem email disponível"}",
                    style = MaterialTheme.typography.bodyLarge.copy(color = TextDark)
                )

                Text(
                    text = "⚧ ${user?.gender ?: "Sem género disponível"}",
                    style = MaterialTheme.typography.bodyLarge.copy(color = TextDark)
                )
            }

            Spacer(Modifier.height(30.dp))

            Box(
                modifier = Modifier
                    .fillMaxWidth(),
                contentAlignment = Alignment.Center
            ) {
                Box(
                    modifier = Modifier
                        .fillMaxWidth(0.7f) // 70% da largura
                ) {
                    PrimaryButton(
                        text = "Editar Perfil",
                        onClick = { navController.navigate(NavPath.EditProfile.route) }
                    )
                }
            }

        }
    }
}

@Preview(showBackground = true)
@Composable
fun ProfileScreenPreview() {
    ProfileScreen(navController = NavController(LocalContext.current))
}