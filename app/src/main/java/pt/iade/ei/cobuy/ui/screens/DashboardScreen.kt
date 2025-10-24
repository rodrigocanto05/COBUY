package pt.iade.ei.cobuy.ui.screens

import androidx.compose.foundation.layout.*
import androidx.compose.material3.*
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import androidx.navigation.NavController
import pt.iade.ei.cobuy.ui.navigation.NavPath

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun DashboardScreen(navController: NavController) {
    Scaffold(
        topBar = { TopAppBar(title = { Text("Painel Principal") }) }
    ) { padding ->
        Column(
            modifier = Modifier
                .fillMaxSize()
                .padding(padding)
                .padding(24.dp),
            verticalArrangement = Arrangement.Center,
            horizontalAlignment = Alignment.CenterHorizontally
        ) {
            Button(
                onClick = { navController.navigate(NavPath.JoinGroup.route) },
                modifier = Modifier.fillMaxWidth()
            ) {
                Text("Entrar em grupo")
            }

            Spacer(Modifier.height(12.dp))

            Button(
                onClick = { navController.navigate(NavPath.CreateGroup.route) },
                modifier = Modifier.fillMaxWidth()
            ) {
                Text("Criar novo grupo")
            }

            Spacer(Modifier.height(12.dp))

            Button(
                onClick = { navController.navigate(NavPath.Map.route) },
                modifier = Modifier.fillMaxWidth()
            ) {
                Text("Supermercados próximos")
            }

            Spacer(Modifier.height(12.dp))

            Button(
                onClick = { navController.navigate(NavPath.Profile.route) },
                modifier = Modifier.fillMaxWidth()
            ) {
                Text("Perfil")
            }
        }
    }
}
