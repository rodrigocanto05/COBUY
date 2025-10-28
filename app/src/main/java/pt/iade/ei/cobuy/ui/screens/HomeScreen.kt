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
import androidx.compose.ui.unit.sp
import androidx.navigation.NavController
import pt.iade.ei.cobuy.R
import pt.iade.ei.cobuy.ui.navigation.NavPath
import pt.iade.ei.cobuy.ui.theme.OrangePrimary

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
            // Logo
            Image(
                painter = painterResource(id = R.mipmap.ic_launcher_foreground),
                contentDescription = "CoBuy Logo",
                modifier = Modifier.size(120.dp),
                contentScale = ContentScale.Crop
            )

            // Texto principal
            Column(horizontalAlignment = Alignment.CenterHorizontally) {
                Text(
                    text = "CoBuy",
                    fontSize = 40.sp,
                    fontWeight = FontWeight.Bold,
                    color = OrangePrimary
                )
                Spacer(Modifier.height(8.dp))
                Text(
                    text = "Do GPS ao carrinho — tudo numa app!",
                    fontSize = 16.sp,
                    color = MaterialTheme.colorScheme.onBackground
                )
            }

            // Botões
            Column(horizontalAlignment = Alignment.CenterHorizontally) {
                Button(
                    onClick = { navController.navigate(NavPath.Register.route) },
                    modifier = Modifier
                        .fillMaxWidth()
                        .height(56.dp),
                    shape = MaterialTheme.shapes.extraLarge
                ) {
                    Text("Criar Conta", fontSize = 18.sp)
                }

                Spacer(Modifier.height(16.dp))

                OutlinedButton(
                    onClick = { navController.navigate(NavPath.Login.route) },
                    modifier = Modifier
                        .fillMaxWidth()
                        .height(56.dp),
                    shape = MaterialTheme.shapes.extraLarge,
                    colors = ButtonDefaults.outlinedButtonColors(contentColor = OrangePrimary)
                ) {
                    Text("Iniciar Sessão", fontSize = 18.sp)
                }
            }
        }
    }
}
