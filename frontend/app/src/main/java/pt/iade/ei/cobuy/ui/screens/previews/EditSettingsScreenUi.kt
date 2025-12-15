package pt.iade.ei.cobuy.ui.screens.previews

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Scaffold
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import androidx.navigation.NavController
import pt.iade.ei.cobuy.ui.components.buttons.PrimaryButton
import pt.iade.ei.cobuy.ui.components.inputs.CustomTextField
import pt.iade.ei.cobuy.ui.components.topbar.CoBuyTopBar
import pt.iade.ei.cobuy.ui.theme.BackgroundLight

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun EditSettingsScreenUi(
    navController: NavController,
    email: String = "example@gmail.com",
    oldPassword: String = "",
    newPassword: String = ""
) {
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
                onValueChange = {},
                label = "Novo Email"
            )

            CustomTextField(
                value = oldPassword,
                onValueChange = {},
                label = "Password atual"
            )

            CustomTextField(
                value = newPassword,
                onValueChange = {},
                label = "Nova password"
            )

            PrimaryButton("Guardar Alterações") { }
        }
    }
}
