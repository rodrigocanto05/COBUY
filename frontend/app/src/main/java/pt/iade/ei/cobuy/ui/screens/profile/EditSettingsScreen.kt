package pt.iade.ei.cobuy.ui.screens.profile

import android.widget.Toast
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
import pt.iade.ei.cobuy.network.viewmodels.auth.AuthViewModel
import pt.iade.ei.cobuy.network.viewmodels.auth.AuthViewModelFactory
import pt.iade.ei.cobuy.ui.components.buttons.PrimaryButton
import pt.iade.ei.cobuy.ui.components.inputs.CustomTextField
import pt.iade.ei.cobuy.ui.components.topbar.CoBuyTopBar
import pt.iade.ei.cobuy.ui.screens.previews.EditSettingsScreenUi
import pt.iade.ei.cobuy.ui.theme.BackgroundLight

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun EditSettingsScreen(
    navController: NavController,
    authViewModel: AuthViewModel = viewModel(
        factory = AuthViewModelFactory(LocalContext.current)
    )
) {
    val context = LocalContext.current
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

            CustomTextField(
                value = email,
                onValueChange = { email = it },
                label = "Novo Email"
            )

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

                authViewModel.updateEmail(email) { okEmail ->
                    if (!okEmail) {
                        Toast.makeText(
                            context,
                            "Erro ao atualizar email",
                            Toast.LENGTH_SHORT
                        ).show()
                        return@updateEmail
                    }

                    if (oldPassword.isNotBlank() && newPassword.isNotBlank()) {
                        authViewModel.updatePassword(oldPassword, newPassword) { okPass ->
                            if (okPass) {
                                Toast.makeText(
                                    context,
                                    "Email e password atualizados",
                                    Toast.LENGTH_SHORT
                                ).show()
                                navController.popBackStack()
                            } else {
                                Toast.makeText(
                                    context,
                                    "Erro ao atualizar password",
                                    Toast.LENGTH_SHORT
                                ).show()
                            }
                        }
                    } else {
                        Toast.makeText(
                            context,
                            "Email atualizado com sucesso",
                            Toast.LENGTH_SHORT
                        ).show()
                        navController.popBackStack()
                    }
                }
            }
        }
    }
}
@Preview(showBackground = true, device = "spec:width=411dp,height=891dp")
@Composable
fun EditSettingsScreenPreview() {
    EditSettingsScreenUi(navController = rememberNavController())
}

