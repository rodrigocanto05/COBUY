package pt.iade.ei.cobuy.ui.components.cards

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavController
import pt.iade.ei.cobuy.storage.model.Group
import pt.iade.ei.cobuy.ui.navigation.NavPath
import pt.iade.ei.cobuy.ui.theme.OrangePrimary
import pt.iade.ei.cobuy.ui.theme.TextDark
import pt.iade.ei.cobuy.ui.theme.TextLight

@Composable
fun GroupCard(group: Group, navController: NavController) {
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
                // Nome do grupo
                Text(
                    text = group.name,
                    style = MaterialTheme.typography.bodyLarge.copy(
                        color = OrangePrimary,
                        fontSize = 17.sp,
                        fontWeight = FontWeight.SemiBold
                    )
                )

                // Data de criação (opcional)
                group.createdAt?.let {
                    Text(
                        text = "Criado em: ${it.substring(0, 10)}", // mostra só YYYY-MM-DD
                        style = MaterialTheme.typography.bodyMedium.copy(
                            color = TextDark.copy(alpha = 0.7f),
                            fontSize = 13.sp
                        )
                    )
                }
            }

            Button(
                onClick = {
                    navController.navigate(
                        NavPath.MyLists.withArgs(group.id ?: 0)   // ⬅️ em vez de GroupDetail
                    )
                },
                colors = ButtonDefaults.buttonColors(containerColor = OrangePrimary),
                shape = RoundedCornerShape(20.dp),
                modifier = Modifier.height(38.dp)
            ) {
                Text("Entrar", fontSize = 14.sp, color = TextLight)
            }

        }
    }
}