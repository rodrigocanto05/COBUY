package pt.iade.ei.cobuy.ui.screens

import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.*
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Brush
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavController
import pt.iade.ei.cobuy.R
import pt.iade.ei.cobuy.ui.navigation.NavPath
import pt.iade.ei.cobuy.ui.theme.*
import androidx.compose.foundation.BorderStroke



@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun HomeScreen(navController: NavController) {
    Box(
        modifier = Modifier
            .fillMaxSize()
            .background(
                brush = Brush.verticalGradient(
                    colors = listOf(OrangeLight, BackgroundLight)
                )
            )
            .padding(horizontal = 32.dp, vertical = 40.dp)
    ) {
        Column(
            modifier = Modifier.fillMaxSize(),
            verticalArrangement = Arrangement.SpaceBetween,
            horizontalAlignment = Alignment.CenterHorizontally
        ) {
            // Logo central (ajustado ao tamanho do Figma)
            Image(
                painter = painterResource(id = R.drawable.image),
                contentDescription = "Logo CoBuy",
                modifier = Modifier
                    .size(200.dp)
                    .padding(top = 16.dp)
            )

            // Título e subtítulo
            Column(horizontalAlignment = Alignment.CenterHorizontally) {
                Text(
                    text = "CoBuy",
                    style = MaterialTheme.typography.headlineLarge.copy(
                        color = OrangePrimary,
                        fontSize = 26.sp,
                        fontWeight = FontWeight.SemiBold
                    )
                )
                Spacer(Modifier.height(10.dp))
                Text(
                    text = "Do GPS ao carrinho — tudo numa app!",
                    style = MaterialTheme.typography.bodyLarge.copy(
                        color = TextDark,
                        fontSize = 15.sp
                    )
                )
            }

            // Botões principais
            Column(
                horizontalAlignment = Alignment.CenterHorizontally,
                verticalArrangement = Arrangement.spacedBy(18.dp)
            ) {
                Button(
                    onClick = { navController.navigate(NavPath.Register.route) },
                    modifier = Modifier
                        .fillMaxWidth()
                        .height(56.dp),
                    shape = RoundedCornerShape(40.dp),
                    elevation = ButtonDefaults.buttonElevation(defaultElevation = 6.dp)
                ) {
                    Text(
                        text = "Criar Conta",
                        fontSize = 17.sp,
                        fontWeight = FontWeight.Medium
                    )
                }

                OutlinedButton(
                    onClick = { navController.navigate(NavPath.Login.route) },
                    modifier = Modifier
                        .fillMaxWidth()
                        .height(56.dp),
                    shape = RoundedCornerShape(40.dp),
                    border = BorderStroke(2.dp, OrangePrimary)
                ) {
                    Text(
                        text = "Iniciar Sessão",
                        fontSize = 17.sp,
                        color = OrangePrimary,
                        fontWeight = FontWeight.Medium
                    )
                }
            }
        }
    }
}
