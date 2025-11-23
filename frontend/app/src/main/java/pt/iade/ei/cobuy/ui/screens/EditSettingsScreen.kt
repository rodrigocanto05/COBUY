package pt.iade.ei.cobuy.ui.screens

import androidx.compose.foundation.layout.*
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Scaffold
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.lifecycle.viewmodel.compose.viewModel
import androidx.navigation.NavController
import androidx.navigation.compose.rememberNavController
import pt.iade.ei.cobuy.network.viewmodels.AuthViewModel
import pt.iade.ei.cobuy.network.viewmodels.AuthViewModelFactory
import pt.iade.ei.cobuy.ui.components.buttons.PrimaryButton
import pt.iade.ei.cobuy.ui.components.inputs.CustomTextField
import pt.iade.ei.cobuy.ui.components.topbar.CoBuyTopBar
import pt.iade.ei.cobuy.ui.theme.BackgroundLight

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun EditSettingsScreen(
    navController: NavController,
    authViewModel: AuthViewModel = viewModel(
        factory = AuthViewModelFactory(LocalContext.current)
    )
) {
    val user by authViewModel.currentUser

    var email by remember { mutableStateOf("") }
    var oldPassword by remember { mutableStateOf("") }
    var newPassword by remember { mutableStateOf("") }

    LaunchedEffect(user) {
        user?.let {
            email = it.email
        }
    }

    Scaffold(
        topBar = { CoBuyTopBar("Definições de Conta", navController) },
        containerColor = BackgroundLight
    ) { padding ->

        Column(
            modifier = Modifier
                .fillMaxSize()
                .padding(padding)
                .padding(horizontal = 32.dp, vertical = 40.dp),
            horizontalAlignment = Alignment.CenterHorizontally,
            verticalArrangement = Arrangement.spacedBy(24.dp)
        ) {

            // -------- EMAIL --------
            CustomTextField(
                value = email,
                onValueChange = { email = it },
                label = "Novo Email"
            )

            // -------- PASSWORD --------
            CustomTextField(
                value = oldPassword,
                onValueChange = { oldPassword = it },
                label = "Password atual"
            )

            CustomTextField(
                value = newPassword,
                onValueChange = { newPassword = it },
                label = "Nova password"
            )

            PrimaryButton("Guardar Alterações") {

                // 1 — atualizar email
                authViewModel.updateEmail(email) { okEmail ->
                    if (!okEmail) return@updateEmail

                    // 2 — atualizar password (se o user preencheu)
                    if (oldPassword.isNotBlank() && newPassword.isNotBlank()) {
                        authViewModel.updatePassword(oldPassword, newPassword) { okPass ->
                            if (okPass) navController.popBackStack()
                        }
                    } else {
                        navController.popBackStack()
                    }
                }
            }
        }
    }
}

@Preview(showBackground = true)
@Composable
fun EditSettingsScreenPreview() {
    EditSettingsScreen(navController = rememberNavController())
}
