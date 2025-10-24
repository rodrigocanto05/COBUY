package pt.iade.ei.cobuy.ui.screens

import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.*
import androidx.compose.material3.*
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.navigation.NavController
import pt.iade.ei.cobuy.R
import pt.iade.ei.cobuy.ui.navigation.NavPath

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun HomeScreen(navController: NavController) {
    Scaffold { padding ->
        Column(
            modifier = Modifier
                .fillMaxSize()
                .padding(padding)
                .padding(horizontal = 32.dp, vertical = 40.dp),
            verticalArrangement = Arrangement.SpaceBetween,
            horizontalAlignment = Alignment.CenterHorizontally
        ) {
            Spacer(Modifier.height(40.dp))

            // Logo
            Image(
                painter = painterResource(id = R.drawable.ic_launcher_foreground),
                contentDescription = "CoBuy Logo",
                modifier = Modifier
                    .size(120.dp)
                    .padding(8.dp),
                contentScale = ContentScale.Crop
            )

            Spacer(Modifier.height(24.dp))

            // App name + slogan
            Column(horizontalAlignment = Alignment.CenterHorizontally) {
                Text(
                    text = "CoBuy",
                    style = MaterialTheme.typography.headlineMedium.copy(fontWeight = FontWeight.Bold)
                )
                Spacer(Modifier.height(8.dp))
                Text(
                    text = "Do GPS ao carrinho — tudo numa app!",
                    style = MaterialTheme.typography.bodyMedium
                )
            }

            Spacer(Modifier.height(48.dp))

            // Buttons
            Column(
                modifier = Modifier.fillMaxWidth(),
                horizontalAlignment = Alignment.CenterHorizontally
            ) {
                Button(
                    onClick = { navController.navigate(NavPath.Register.route) },
                    modifier = Modifier
                        .fillMaxWidth()
                        .height(56.dp)
                ) {
                    Text("Criar conta")
                }

                Spacer(Modifier.height(16.dp))

                OutlinedButton(
                    onClick = { navController.navigate(NavPath.Login.route) },
                    modifier = Modifier
                        .fillMaxWidth()
                        .height(56.dp)
                ) {
                    Text("Iniciar sessão")
                }
            }

            Spacer(Modifier.height(40.dp))
        }
    }
}
