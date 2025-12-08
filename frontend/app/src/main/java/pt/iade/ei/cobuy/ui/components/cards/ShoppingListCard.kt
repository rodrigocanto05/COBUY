package pt.iade.ei.cobuy.ui.components.cards

import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.aspectRatio
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import pt.iade.ei.cobuy.storage.model.ShoppingList
import pt.iade.ei.cobuy.ui.theme.COBUYTheme
import pt.iade.ei.cobuy.ui.theme.OrangePrimary
import pt.iade.ei.cobuy.ui.theme.TextDark

@Composable
fun ShoppingListCard(
    list: ShoppingList,
    onClick: () -> Unit
) {
    Card(
        modifier = Modifier
            .fillMaxWidth()
            .aspectRatio(1.1f)
            .clickable { onClick() },
        shape = RoundedCornerShape(18.dp),
        elevation = CardDefaults.cardElevation(defaultElevation = 6.dp),
        colors = CardDefaults.cardColors(containerColor = MaterialTheme.colorScheme.surface)
    ) {
        Column(
            modifier = Modifier
                .fillMaxSize()
                .padding(horizontal = 16.dp, vertical = 14.dp),
            verticalArrangement = Arrangement.SpaceBetween
        ) {
            Text(
                text = list.title,
                style = MaterialTheme.typography.bodyLarge.copy(
                    color = OrangePrimary,
                    fontSize = 17.sp,
                    fontWeight = FontWeight.SemiBold
                )
            )

            Spacer(modifier = Modifier.height(8.dp))

            list.createdAt?.let {
                Text(
                    text = "Criada em: ${it.substring(0, 10)}",
                    style = MaterialTheme.typography.bodyMedium.copy(
                        color = TextDark.copy(alpha = 0.7f),
                        fontSize = 13.sp
                    )
                )
            }
        }
    }
}

@Preview(showBackground = true)
@Composable
fun ShoppingListCardPreview() {
    COBUYTheme {
        ShoppingListCard(
            list = ShoppingList(
                id = 1,
                title = "Churrasco Rapazes",
                createdAt = "2025-12-05"
            ),
            onClick = {}
        )
    }
}
