package pt.iade.ei.cobuy.ui.screens

import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Add
import androidx.compose.material3.*
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavController
import pt.iade.ei.cobuy.ui.theme.*
import androidx.compose.material3.TopAppBarDefaults


@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun DashboardScreen(navController: NavController) {
    val groups = listOf("Família", "Amigos da Faculdade", "Colegas de Trabalho")

    Scaffold(
        topBar = {
            TopAppBar(
                title = {
                    Text(
                        "Os Meus Grupos",
                        style = MaterialTheme.typography.headlineSmall.copy(
                            fontWeight = FontWeight.SemiBold,
                            fontSize = 20.sp,
                            color = TextLight
                        )
                    )
                },
                colors = TopAppBarDefaults.topAppBarColors(
                    containerColor = OrangePrimary
                )
            )
        },
        floatingActionButton = {
            FloatingActionButton(
                onClick = { /* TODO: criar novo grupo */ },
                containerColor = OrangePrimary
            ) {
                Icon(
                    imageVector = Icons.Default.Add,
                    contentDescription = "Adicionar grupo",
                    tint = TextLight
                )
            }
        },
        containerColor = BackgroundLight
    ) { padding ->
        Column(
            modifier = Modifier
                .fillMaxSize()
                .padding(padding)
                .padding(horizontal = 16.dp, vertical = 12.dp)
        ) {
            Text(
                text = "Bem-vindo de volta 👋",
                style = MaterialTheme.typography.bodyLarge.copy(
                    color = TextDark,
                    fontSize = 16.sp
                )
            )

            Spacer(Modifier.height(16.dp))

            LazyColumn(
                verticalArrangement = Arrangement.spacedBy(14.dp),
                modifier = Modifier.fillMaxSize()
            ) {
                items(groups) { group ->
                    Card(
                        modifier = Modifier
                            .fillMaxWidth()
                            .height(90.dp),
                        shape = RoundedCornerShape(18.dp),
                        elevation = CardDefaults.cardElevation(defaultElevation = 6.dp),
                        colors = CardDefaults.cardColors(containerColor = TextLight)
                    ) {
                        Row(
                            modifier = Modifier
                                .fillMaxSize()
                                .padding(horizontal = 20.dp),
                            verticalAlignment = Alignment.CenterVertically,
                            horizontalArrangement = Arrangement.SpaceBetween
                        ) {
                            Column(
                                verticalArrangement = Arrangement.Center
                            ) {
                                Text(
                                    text = group,
                                    style = MaterialTheme.typography.bodyLarge.copy(
                                        color = OrangePrimary,
                                        fontSize = 17.sp,
                                        fontWeight = FontWeight.SemiBold
                                    )
                                )
                                Text(
                                    text = "3 membros ativos",
                                    style = MaterialTheme.typography.bodyMedium.copy(
                                        color = TextDark.copy(alpha = 0.7f)
                                    )
                                )
                            }

                            Button(
                                onClick = { /* TODO: abrir grupo */ },
                                colors = ButtonDefaults.buttonColors(containerColor = OrangePrimary),
                                shape = RoundedCornerShape(20.dp),
                                modifier = Modifier.height(38.dp)
                            ) {
                                Text("Abrir", fontSize = 14.sp)
                            }
                        }
                    }
                }
            }
        }
    }
}
