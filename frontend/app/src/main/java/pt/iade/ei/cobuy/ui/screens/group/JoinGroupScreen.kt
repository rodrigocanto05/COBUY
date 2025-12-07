package pt.iade.ei.cobuy.ui.screens.group

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.lifecycle.viewmodel.compose.viewModel
import androidx.navigation.NavController
import androidx.navigation.compose.rememberNavController
import pt.iade.ei.cobuy.network.viewmodels.groups.GroupViewModel
import pt.iade.ei.cobuy.ui.components.buttons.PrimaryButton
import pt.iade.ei.cobuy.ui.components.inputs.CustomTextField
import pt.iade.ei.cobuy.ui.components.topbar.CoBuyTopBar
import pt.iade.ei.cobuy.ui.navigation.NavPath
import pt.iade.ei.cobuy.ui.theme.BackgroundLight

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun JoinGroupScreen(navController: NavController) {
    val viewModel: GroupViewModel = viewModel()

    var groupCode by remember { mutableStateOf("") }
    var errorMessage by remember { mutableStateOf<String?>(null) }

    val userId = 1  // TODO: substituir pelo user logado (DataStore / SharedPrefs)

    Scaffold(
        topBar = { CoBuyTopBar("Entrar em Grupo", navController) },
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
            Text("Introduz o código do grupo partilhado contigo:")

            CustomTextField(
                value = groupCode,
                onValueChange = { groupCode = it },
                label = "Código do grupo"
            )

            // BOTÃO DE ENTRAR
            PrimaryButton("Entrar no Grupo") {
                if (groupCode.isBlank()) {
                    errorMessage = "Insere um código válido"
                    return@PrimaryButton
                }

                viewModel.joinGroup(groupCode.trim(), userId) { group, error ->
                    if (error != null) {
                        errorMessage = error
                    } else {
                        errorMessage = null
                        navController.navigate(NavPath.MyGroups.route)
                    }
                }
            }

            // ERRO
            if (errorMessage != null) {
                Text(text = errorMessage!!, color = Color.Red)
            }

            Text("O código é fornecido pelo criador do grupo.")
        }
    }
}

@Preview(showBackground = true)
@Composable
fun JoinGroupScreenPreview() {
    JoinGroupScreen(navController = rememberNavController())
}
