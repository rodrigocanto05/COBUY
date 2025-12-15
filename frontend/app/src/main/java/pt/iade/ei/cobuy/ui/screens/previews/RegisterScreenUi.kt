package pt.iade.ei.cobuy.ui.screens.previews

import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.*
import androidx.compose.material3.*
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.input.PasswordVisualTransformation
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.compose.rememberNavController
import pt.iade.ei.cobuy.R
import pt.iade.ei.cobuy.ui.components.buttons.PrimaryButton
import pt.iade.ei.cobuy.ui.components.inputs.CustomTextField
import pt.iade.ei.cobuy.ui.components.topbar.CoBuyTopBar
import pt.iade.ei.cobuy.ui.theme.BackgroundLight
import pt.iade.ei.cobuy.ui.theme.OrangePrimary

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun RegisterScreenUi() {
    Scaffold(
        containerColor = BackgroundLight,
        topBar = {
            CoBuyTopBar(
                title = "",
                navController = rememberNavController(),
                showBackButton = true
            )
        }
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
                contentDescription = null,
                modifier = Modifier.size(120.dp)
            )

            Spacer(Modifier.height(20.dp))

            Text(
                text = "Criar Conta",
                fontSize = 22.sp,
                fontWeight = FontWeight.SemiBold,
                color = OrangePrimary
            )

            Spacer(Modifier.height(28.dp))

            Column(
                modifier = Modifier.fillMaxWidth(),
                verticalArrangement = Arrangement.spacedBy(16.dp)
            ) {
                CustomTextField(
                    value = "",
                    onValueChange = {},
                    label = "Nome"
                )

                CustomTextField(
                    value = "",
                    onValueChange = {},
                    label = "Email"
                )

                CustomTextField(
                    value = "",
                    onValueChange = {},
                    label = "Palavra-passe",
                    visualTransformation = PasswordVisualTransformation()
                )

                CustomTextField(
                    value = "",
                    onValueChange = {},
                    label = "Confirmar palavra-passe",
                    visualTransformation = PasswordVisualTransformation()
                )
            }

            Spacer(Modifier.height(32.dp))

            PrimaryButton(text = "Registar") {}

            Spacer(Modifier.height(12.dp))

            TextButton(onClick = {}) {
                Text(
                    text = "Já tem conta? Iniciar sessão",
                    color = OrangePrimary,
                    fontSize = 14.sp
                )
            }
        }
    }
}
