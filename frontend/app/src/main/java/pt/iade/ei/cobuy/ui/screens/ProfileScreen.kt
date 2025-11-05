package pt.iade.ei.cobuy.ui.screens

import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.ArrowBack
import androidx.compose.material.icons.filled.Logout
import androidx.compose.material3.*
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavController
import pt.iade.ei.cobuy.R
import pt.iade.ei.cobuy.ui.theme.*
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.BorderStroke
import androidx.compose.material3.TopAppBarDefaults


@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun ProfileScreen(navController: NavController) {
    Scaffold(
        topBar = {
            TopAppBar(
                title = {
                    Text(
                        text = "Perfil",
                        style = MaterialTheme.typography.headlineSmall.copy(
                            fontWeight = FontWeight.SemiBold,
                            color = TextLight,
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
                .padding(horizontal = 24.dp, vertical = 32.dp),
            horizontalAlignment = Alignment.CenterHorizontally,
            verticalArrangement = Arrangement.spacedBy(24.dp)
        ) {
            Image(
                painter = painterResource(id = R.drawable.image),
                contentDescription = "Foto de Perfil",
                modifier = Modifier
                    .size(120.dp)
                    .clip(CircleShape)
            )

            Text("João Silva", style = MaterialTheme.typography.headlineSmall.copy(
                color = OrangePrimary,
                fontWeight = FontWeight.SemiBold,
                fontSize = 20.sp
            ))

            Column(
                verticalArrangement = Arrangement.spacedBy(8.dp)
            ) {
                Text("📧 joao@example.com", style = MaterialTheme.typography.bodyLarge.copy(color = TextDark))
                Text("📞 +351 912 345 678", style = MaterialTheme.typography.bodyLarge.copy(color = TextDark))
            }

            Spacer(Modifier.height(20.dp))

            Button(
                onClick = { /* TODO: Editar Perfil */ },
                shape = RoundedCornerShape(30.dp),
                colors = ButtonDefaults.buttonColors(containerColor = OrangePrimary),
                modifier = Modifier
                    .fillMaxWidth()
                    .height(50.dp)
            ) {
                Text("Editar Perfil", color = TextLight, fontSize = 16.sp)
            }

            OutlinedButton(
                onClick = { /* TODO: Logout */ },
                shape = RoundedCornerShape(30.dp),
                border = BorderStroke(2.dp, OrangePrimary),
                modifier = Modifier
                    .fillMaxWidth()
                    .height(50.dp)
            ) {
                Icon(Icons.Default.Logout, contentDescription = null, tint = OrangePrimary)
                Spacer(Modifier.width(6.dp))
                Text("Terminar Sessão", color = OrangePrimary, fontSize = 15.sp)
            }
        }
    }
}
