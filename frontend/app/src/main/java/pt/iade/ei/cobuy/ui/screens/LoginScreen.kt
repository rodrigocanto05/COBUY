package pt.iade.ei.cobuy.ui.screens

import android.util.Log
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
import pt.iade.ei.cobuy.network.viewmodels.AuthViewModel
import pt.iade.ei.cobuy.network.viewmodels.AuthViewModelFactory

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun LoginScreen(navController: NavController) {
    val context = LocalContext.current
    val viewModel: AuthViewModel = viewModel(factory = AuthViewModelFactory(context))

    var emailOrPhone by remember { mutableStateOf("") }
    var password by remember { mutableStateOf("") }


    var errorMessage by remember { mutableStateOf("") }

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
                text = "Iniciar Sessão",
                style = MaterialTheme.typography.headlineSmall.copy(
                    fontWeight = FontWeight.SemiBold,
                    color = OrangePrimary,
                    fontSize = 22.sp
                )
            )

            Spacer(Modifier.height(28.dp))

            // CAMPOS DE ENTRADA
            Column(
                modifier = Modifier.fillMaxWidth(),
                verticalArrangement = Arrangement.spacedBy(16.dp)
            ) {
                CustomTextField(
                    value = emailOrPhone,
                    onValueChange = { emailOrPhone = it },
                    label = "Email ou Telefone"
                )

                CustomTextField(
                    value = password,
                    onValueChange = { password = it },
                    label = "Palavra-passe",
                    visualTransformation = PasswordVisualTransformation()
                )
            }

            Spacer(Modifier.height(32.dp))


            if (errorMessage.isNotEmpty()) {
                Text(
                    text = errorMessage,
                    color = MaterialTheme.colorScheme.error,
                    fontSize = 14.sp,
                    fontWeight = FontWeight.Medium,
                    modifier = Modifier.padding(bottom = 8.dp)
                )
            }

            // BOTÃO DE LOGIN
            PrimaryButton(text = "Entrar") {
                viewModel.login(emailOrPhone, password) { ok, err ->
                    if (ok) {
                        errorMessage = ""
                        navController.navigate(NavPath.Dashboard.route)
                    } else {
                        errorMessage = "Ups! Algo não está certo. Verifique os seus dados."
                        Log.e("LOGIN", "Erro: $err")
                    }
                }
            }

            Spacer(Modifier.height(12.dp))

            // TEXTO DE REGISTO
            TextButton(onClick = { navController.navigate(NavPath.Register.route) }) {
                Text(
                    text = "Ainda não tem conta? Criar agora",
                    color = OrangePrimary,
                    fontSize = 14.sp
                )
            }
        }
    }
}

@Preview(showBackground = true)
@Composable
fun LoginScreenPreview() {
    LoginScreen(navController = NavController(LocalContext.current))
}
