package pt.iade.ei.cobuy.ui.screens.profile

import android.widget.Toast
import androidx.compose.foundation.layout.*
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.lifecycle.viewmodel.compose.viewModel
import androidx.navigation.NavController
import androidx.navigation.compose.rememberNavController
import pt.iade.ei.cobuy.network.viewmodels.auth.AuthViewModel
import pt.iade.ei.cobuy.network.viewmodels.auth.AuthViewModelFactory
import pt.iade.ei.cobuy.ui.components.buttons.PrimaryButton
import pt.iade.ei.cobuy.ui.components.inputs.CustomTextField
import pt.iade.ei.cobuy.ui.components.topbar.CoBuyTopBar
import pt.iade.ei.cobuy.ui.screens.Auth.GenderRadio
import pt.iade.ei.cobuy.ui.theme.BackgroundLight
import pt.iade.ei.cobuy.ui.theme.OrangePrimary

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun EditProfileScreen(
    navController: NavController,
    authViewModel: AuthViewModel = viewModel(
        factory = AuthViewModelFactory(LocalContext.current)
    )
) {
    val context = LocalContext.current
    val user by authViewModel.currentUser

    var name by remember { mutableStateOf("") }
    var gender by remember { mutableStateOf("M") }

    LaunchedEffect(user) {
        user?.let {
            name = it.name
            gender = it.gender ?: "M"
        }
    }

    EditProfileScreenContent(
        navController = navController,
        name = name,
        gender = gender,
        onNameChange = { name = it },
        onGenderChange = { gender = it },
        onSave = {
            authViewModel.updateUser(name, gender) { ok ->
                if (ok) {
                    Toast.makeText(
                        context,
                        "Perfil atualizado com sucesso",
                        Toast.LENGTH_SHORT
                    ).show()
                    navController.popBackStack()
                } else {
                    Toast.makeText(
                        context,
                        "Erro ao atualizar perfil",
                        Toast.LENGTH_SHORT
                    ).show()
                }
            }
        }
    )
}

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun EditProfileScreenContent(
    navController: NavController,
    name: String,
    gender: String,
    onNameChange: (String) -> Unit,
    onGenderChange: (String) -> Unit,
    onSave: () -> Unit
) {
    Scaffold(
        topBar = { CoBuyTopBar("Editar Perfil", navController) },
        containerColor = BackgroundLight
    ) { padding ->

        Column(
            modifier = Modifier
                .fillMaxSize()
                .padding(padding)
                .padding(horizontal = 32.dp, vertical = 32.dp),
            horizontalAlignment = Alignment.CenterHorizontally,
            verticalArrangement = Arrangement.Top
        ) {

            Spacer(Modifier.height(8.dp))

            Column(
                modifier = Modifier.fillMaxWidth(),
                verticalArrangement = Arrangement.spacedBy(16.dp)
            ) {

                CustomTextField(
                    value = name,
                    onValueChange = onNameChange,
                    label = "Nome"
                )

                Text(
                    text = "Género",
                    fontWeight = FontWeight.SemiBold,
                    fontSize = 16.sp,
                    color = OrangePrimary
                )

                Row(
                    modifier = Modifier.fillMaxWidth(),
                    horizontalArrangement = Arrangement.SpaceBetween,
                    verticalAlignment = Alignment.CenterVertically
                ) {
                    GenderRadio("Masculino", gender, "M", onGenderChange)
                    GenderRadio("Feminino", gender, "F", onGenderChange)
                    GenderRadio("Outro", gender, "O", onGenderChange)
                }
            }

            Spacer(Modifier.height(32.dp))

            PrimaryButton(text = "Guardar Alterações") {
                onSave()
            }
        }
    }
}

@Preview(showBackground = true)
@Composable
fun EditProfileScreenPreview() {
    val navController = rememberNavController()
    var name by remember { mutableStateOf("Marco Fonseca") }
    var gender by remember { mutableStateOf("M") }

    EditProfileScreenContent(
        navController = navController,
        name = name,
        gender = gender,
        onNameChange = { name = it },
        onGenderChange = { gender = it },
        onSave = { }
    )
}