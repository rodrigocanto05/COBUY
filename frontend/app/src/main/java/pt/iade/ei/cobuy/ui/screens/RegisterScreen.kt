package pt.iade.ei.cobuy.ui.screens

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
import pt.iade.ei.cobuy.model.AuthRequest
import pt.iade.ei.cobuy.ui.components.buttons.PrimaryButton
import pt.iade.ei.cobuy.ui.components.inputs.CustomTextField
import pt.iade.ei.cobuy.ui.components.topbar.CoBuyTopBar
import pt.iade.ei.cobuy.ui.navigation.NavPath
import pt.iade.ei.cobuy.ui.theme.*
import pt.iade.ei.cobuy.viewmodels.UserViewModel

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun RegisterScreen(navController: NavController, viewModel: UserViewModel = viewModel()) {
    var email by remember { mutableStateOf("") }
    var password by remember { mutableStateOf("") }
    var confirm by remember { mutableStateOf("") }

    val scope = rememberCoroutineScope() // 👈 Necessário para chamadas suspensas


    Scaffold(
        containerColor = BackgroundLight,
        topBar = { CoBuyTopBar("", navController = navController, showBackButton = false) } // Back button hidden
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

            // CAMPOS
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
            PrimaryButton("Registar") {
                if (password == confirm) {
                    viewModel.register(AuthRequest(email, password))
                    navController.navigate(NavPath.Login.route)
                }
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
