package pt.iade.ei.cobuy.ui.screens.Auth

import android.widget.Toast
import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.*
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.input.PasswordVisualTransformation
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.lifecycle.viewmodel.compose.viewModel
import androidx.navigation.NavController
import pt.iade.ei.cobuy.R
import pt.iade.ei.cobuy.ui.components.buttons.PrimaryButton
import pt.iade.ei.cobuy.ui.components.inputs.CustomTextField
import pt.iade.ei.cobuy.ui.components.topbar.CoBuyTopBar
import pt.iade.ei.cobuy.ui.navigation.NavPath
import pt.iade.ei.cobuy.ui.theme.*
import pt.iade.ei.cobuy.network.viewmodels.auth.AuthViewModel
import pt.iade.ei.cobuy.network.viewmodels.auth.AuthViewModelFactory

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun RegisterScreen(navController: NavController) {
    val context = LocalContext.current
    val viewModel: AuthViewModel = viewModel(factory = AuthViewModelFactory(context))

    var email by remember { mutableStateOf("") }
    var password by remember { mutableStateOf("") }
    var confirm by remember { mutableStateOf("") }
    var errorMessage by remember { mutableStateOf<String?>(null) }


    var username by remember { mutableStateOf("") }
    var gender by remember { mutableStateOf("M") }

    Scaffold(
        containerColor = BackgroundLight,
        topBar = { CoBuyTopBar("", navController = navController, showBackButton = false) }
    ) { padding ->
        Column(
            modifier = Modifier
                .fillMaxSize()
                .padding(padding)
                .padding(horizontal = 32.dp, vertical = 20.dp),
            horizontalAlignment = Alignment.CenterHorizontally,
            verticalArrangement = Arrangement.Center
        ) {

            Image(
                painter = painterResource(id = R.drawable.logo),
                contentDescription = "Logo CoBuy",
                modifier = Modifier
                    .size(140.dp)
                    .padding(bottom = 20.dp)
            )

            Text(
                text = "Criar Conta",
                style = MaterialTheme.typography.headlineSmall.copy(
                    fontWeight = FontWeight.SemiBold,
                    color = OrangePrimary,
                    fontSize = 22.sp
                )
            )

            Spacer(Modifier.height(28.dp))

            Column(
                modifier = Modifier.fillMaxWidth(),
                verticalArrangement = Arrangement.spacedBy(14.dp)
            ) {


                CustomTextField(
                    value = username,
                    onValueChange = { username = it },
                    label = "Nome de Utilizador"
                )

                CustomTextField(value = email, onValueChange = { email = it }, label = "Email")

                CustomTextField(
                    value = password,
                    onValueChange = { password = it },
                    label = "Palavra-passe",
                    visualTransformation = PasswordVisualTransformation()
                )

                CustomTextField(
                    value = confirm,
                    onValueChange = { confirm = it },
                    label = "Confirmar Palavra-passe",
                    visualTransformation = PasswordVisualTransformation()
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
                    GenderRadio("Masculino", gender, "M") { gender = it }
                    GenderRadio("Feminino", gender, "F") { gender = it }
                    GenderRadio("Outro", gender, "O") { gender = it }

                }

            }

            Spacer(Modifier.height(32.dp))

            PrimaryButton(text = "Criar Conta") {
                if (password == confirm) {


                    viewModel.register(username, email, password, gender) { success, error ->
                        if (success) {
                            Toast.makeText(context, "Conta criada com sucesso!", Toast.LENGTH_SHORT).show()
                            navController.navigate(NavPath.Login.route) {
                                popUpTo(NavPath.Register.route) {
                                    inclusive = true
                                }
                                launchSingleTop = true
                            }
                        } else {
                            errorMessage = error ?: "Erro ao criar conta"
                        }
                    }

                } else {
                    errorMessage = "As palavras-passe não coincidem"
                }
            }


            errorMessage?.let {
                Spacer(Modifier.height(12.dp))
                Text(text = it, color = MaterialTheme.colorScheme.error, fontSize = 14.sp)
            }

            Spacer(Modifier.height(12.dp))

            TextButton(onClick = { navController.navigate(NavPath.Login.route) }) {
                Text(
                    text = "Já tem conta? Iniciar Sessão",
                    color = OrangePrimary,
                    fontSize = 14.sp
                )
            }
        }
    }
}

@Composable
fun GenderRadio(label: String, selectedValue: String, value: String, onSelect: (String) -> Unit) {
    Row(verticalAlignment = Alignment.CenterVertically) {
        RadioButton(
            selected = (value == selectedValue),
            onClick = { onSelect(value) }
        )
        Text(label)
    }
}


@Preview(showBackground = true)
@Composable
fun RegisterScreenPreview() {
    RegisterScreen(navController = NavController(LocalContext.current))
}
