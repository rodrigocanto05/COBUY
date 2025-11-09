package pt.iade.ei.cobuy.ui.screens

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.navigation.NavController
import androidx.navigation.compose.rememberNavController
import pt.iade.ei.cobuy.ui.components.buttons.PrimaryButton
import pt.iade.ei.cobuy.ui.components.inputs.CustomTextField
import pt.iade.ei.cobuy.ui.components.topbar.CoBuyTopBar
import pt.iade.ei.cobuy.ui.theme.BackgroundLight


@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun JoinGroupScreen(navController: NavController) {
    var groupCode by remember { mutableStateOf("") }

    Scaffold(
        topBar = { CoBuyTopBar("Entrar em Grupo", navController = navController) },
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
            Text(
                "Introduz o código do grupo partilhado contigo:",
            )

            CustomTextField(value = groupCode, onValueChange = { groupCode = it }, label = "Código do grupo")

            PrimaryButton("Entrar no Grupo") { /* TODO: Entrar no grupo */ }

            Text(
                text = "O código é fornecido pelo criador do grupo.",
            )
        }
    }
}

@Preview(showBackground = true)
@Composable
fun JoinGroupScreenPreview() {
    JoinGroupScreen(navController = rememberNavController())
}
