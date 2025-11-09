package pt.iade.ei.cobuy.ui.screens

import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.material.icons.Icons
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
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.tooling.preview.Preview
import pt.iade.ei.cobuy.ui.components.buttons.CustomOutlinedButton
import pt.iade.ei.cobuy.ui.components.buttons.PrimaryButton
import pt.iade.ei.cobuy.ui.components.topbar.CoBuyTopBar
import pt.iade.ei.cobuy.ui.navigation.NavPath

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun ProfileScreen(navController: NavController) {
    Scaffold(
        topBar = { CoBuyTopBar("Perfil", navController = navController) },
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

            PrimaryButton("Editar Perfil") { navController.navigate(NavPath.EditProfile.route) }

            CustomOutlinedButton("Terminar Sessão") { /* TODO: Logout */ }
        }
    }
}

@Preview(showBackground = true)
@Composable
fun ProfileScreenPreview() {
    ProfileScreen(navController = NavController(LocalContext.current))
}
