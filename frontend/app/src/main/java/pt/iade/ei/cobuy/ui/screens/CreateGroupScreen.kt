package pt.iade.ei.cobuy.ui.screens

import android.widget.Toast
import androidx.compose.foundation.layout.*
import androidx.compose.material3.*
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
import pt.iade.ei.cobuy.network.viewmodels.GroupViewModel
import pt.iade.ei.cobuy.ui.components.buttons.PrimaryButton
import pt.iade.ei.cobuy.ui.components.inputs.CustomTextField
import pt.iade.ei.cobuy.ui.components.topbar.CoBuyTopBar
import pt.iade.ei.cobuy.ui.theme.BackgroundLight
import pt.iade.ei.cobuy.ui.theme.OrangePrimary
import pt.iade.ei.cobuy.ui.theme.TextDark
import kotlin.random.Random

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun CreateGroupScreen(navController: NavController) {
    val context = LocalContext.current
    val viewModel: GroupViewModel = viewModel()   // 👈 conecta ao ViewModel

    var groupName by remember { mutableStateOf("") }
    var generatedCode by remember { mutableStateOf(generateCode()) }
    var isLoading by remember { mutableStateOf(false) }   // 👈 agora o compilador reconhece

    Scaffold(
        topBar = { CoBuyTopBar("Criar Grupo", navController = navController) },
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
                value = groupName,
                onValueChange = { groupName = it },
                label = "Nome do Grupo"
            )

            Surface(
                shadowElevation = 4.dp,
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(top = 12.dp)
            ) {
                Column(
                    horizontalAlignment = Alignment.CenterHorizontally,
                    modifier = Modifier.padding(vertical = 16.dp)
                ) {
                    Text(
                        "Código do grupo",
                        style = MaterialTheme.typography.bodyLarge.copy(color = TextDark)
                    )
                    Spacer(Modifier.height(8.dp))
                    Text(
                        generatedCode,
                        style = MaterialTheme.typography.headlineSmall.copy(
                            color = OrangePrimary,
                            fontWeight = FontWeight.SemiBold,
                            fontSize = 24.sp
                        )
                    )
                }
            }

            PrimaryButton(
                text = if (isLoading) "A criar..." else "Criar Grupo",
                onClick = {
                    if (isLoading) return@PrimaryButton

                    if (groupName.isBlank()) {
                        Toast.makeText(
                            context,
                            "O nome do grupo não pode estar vazio.",
                            Toast.LENGTH_SHORT
                        ).show()
                        return@PrimaryButton
                    }

                    isLoading = true
                    viewModel.createGroup(groupName) { success, error ->
                        isLoading = false
                        if (success) {
                            Toast.makeText(
                                context,
                                "Grupo criado com sucesso!",
                                Toast.LENGTH_SHORT
                            ).show()
                            generatedCode = generateCode()
                            groupName = ""
                        } else {
                            Toast.makeText(
                                context,
                                "Erro: $error",
                                Toast.LENGTH_LONG
                            ).show()
                        }
                    }
                }
            )

            Text(
                text = "Partilha o código com os teus amigos para entrarem no grupo.",
                style = MaterialTheme.typography.bodyMedium.copy(
                    color = TextDark.copy(alpha = 0.7f)
                ),
                modifier = Modifier.padding(top = 8.dp)
            )
        }
    }
}

private fun generateCode(): String {
    val chars = "ABCDEFGHIJKLMNOPQRSTUVWXYZ0123456789"
    return (1..5).map { chars.random(Random(System.nanoTime())) }.joinToString("")
}

@Preview(showBackground = true)
@Composable
fun CreateGroupScreenPreview() {
    CreateGroupScreen(navController = rememberNavController())
}
