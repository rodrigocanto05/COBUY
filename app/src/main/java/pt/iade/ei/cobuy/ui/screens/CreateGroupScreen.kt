package pt.iade.ei.cobuy.ui.screens

import androidx.compose.foundation.layout.*
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.ArrowBack
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavController
import pt.iade.ei.cobuy.ui.theme.*
import kotlin.random.Random
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.TopAppBarDefaults

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun CreateGroupScreen(navController: NavController) {
    var groupName by remember { mutableStateOf("") }
    var generatedCode by remember { mutableStateOf(generateCode()) }

    Scaffold(
        topBar = {
            TopAppBar(
                title = {
                    Text(
                        text = "Criar Novo Grupo",
                        style = MaterialTheme.typography.headlineSmall.copy(
                            color = TextLight,
                            fontWeight = FontWeight.SemiBold,
                            fontSize = 20.sp
                        )
                    )
                },
                navigationIcon = {
                    IconButton(onClick = { navController.popBackStack() }) {
                        Icon(Icons.Default.ArrowBack, contentDescription = "Voltar", tint = TextLight)
                    }
                },
                colors = TopAppBarDefaults.topAppBarColors(
                    containerColor = OrangePrimary
                )
            )
        },
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
            OutlinedTextField(
                value = groupName,
                onValueChange = { groupName = it },
                label = { Text("Nome do Grupo") },
                singleLine = true,
                shape = RoundedCornerShape(20.dp),
                modifier = Modifier.fillMaxWidth()
            )

            Surface(
                color = TextLight,
                shadowElevation = 4.dp,
                shape = RoundedCornerShape(16.dp),
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

            Button(
                onClick = {
                    // TODO: criar grupo
                    generatedCode = generateCode()
                },
                shape = RoundedCornerShape(30.dp),
                colors = ButtonDefaults.buttonColors(containerColor = OrangePrimary),
                modifier = Modifier
                    .fillMaxWidth()
                    .height(54.dp)
            ) {
                Text("Criar Grupo", color = TextLight, fontSize = 16.sp)
            }

            Text(
                text = "Partilha o código com os teus amigos para entrarem no grupo.",
                style = MaterialTheme.typography.bodyMedium.copy(color = TextDark.copy(alpha = 0.7f)),
                modifier = Modifier.padding(top = 8.dp)
            )
        }
    }
}

private fun generateCode(): String {
    val chars = "ABCDEFGHIJKLMNOPQRSTUVWXYZ0123456789"
    return (1..5).map { chars.random(Random(System.nanoTime())) }.joinToString("")
}
