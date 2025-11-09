package pt.iade.ei.cobuy.ui.screens

import android.util.Log
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
import pt.iade.ei.cobuy.network.viewmodels.AuthViewModel // 👈 usa o viewmodel certo
import pt.iade.ei.cobuy.network.viewmodels.AuthViewModelFactory

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun RegisterScreen(navController: NavController) {
    val context = LocalContext.current
    val viewModel: AuthViewModel = viewModel(factory = AuthViewModelFactory(context))

    var email by remember { mutableStateOf("") }
    var password by remember { mutableStateOf("") }
    var confirm by remember { mutableStateOf("") }
    var errorMessage by remember { mutableStateOf<String?>(null) }

    PrimaryButton(text = "Criar Conta") {
        if (password == confirm) {
            viewModel.register(email, password) { ok, err ->
                if (ok) {
                    navController.navigate(NavPath.Login.route)
                } else {
                    errorMessage = err ?: "Erro ao criar conta"
                }
            }
        } else {
            errorMessage = "As palavras-passe não coincidem"
        }
    }

    errorMessage?.let {
        Text(text = it, color = MaterialTheme.colorScheme.error)
    }



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
            // LOGO
            Image(
                painter = painterResource(id = R.drawable.image),
                contentDescription = "Logo CoBuy",
                modifier = Modifier
                    .size(140.dp)
                    .padding(bottom = 20.dp)
            )

            // TÍTULO
            Text(
                text = "Criar Conta",
                style = MaterialTheme.typography.headlineSmall.copy(
                    fontWeight = FontWeight.SemiBold,
                    color = OrangePrimary,
                    fontSize = 22.sp
                )
            )

            Spacer(Modifier.height(28.dp))

            // CAMPOS DE TEXTO
            Column(
                modifier = Modifier.fillMaxWidth(),
                verticalArrangement = Arrangement.spacedBy(14.dp)
            ) {
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
            }

            Spacer(Modifier.height(32.dp))

            // BOTÃO DE REGISTO
            PrimaryButton(text = "Criar Conta") {
                if (password == confirm) {
                    viewModel.register(email, password) { success, error ->
                        if (success) {
                            Toast.makeText(context, "Conta criada com sucesso!", Toast.LENGTH_SHORT).show()
                            Log.d("REGISTER", "Registo feito com sucesso!")
                            navController.navigate(NavPath.Login.route)
                        } else {
                            errorMessage = error ?: "Erro ao criar conta"
                            Log.e("REGISTER", "Erro no registo: $error")
                        }
                    }
                } else {
                    errorMessage = "As palavras-passe não coincidem"
                }
            }

            // Mostrar erro (se houver)
            errorMessage?.let {
                Spacer(Modifier.height(12.dp))
                Text(text = it, color = MaterialTheme.colorScheme.error, fontSize = 14.sp)
            }

            Spacer(Modifier.height(12.dp))

            // TEXTO DE LOGIN
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

@Preview(showBackground = true)
@Composable
fun RegisterScreenPreview() {
    RegisterScreen(navController = NavController(LocalContext.current))
}
