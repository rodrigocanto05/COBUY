package pt.iade.ei.cobuy.ui.components.cards

import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.CheckCircle
import androidx.compose.material.icons.filled.RadioButtonUnchecked
import androidx.compose.material3.*
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import pt.iade.ei.cobuy.storage.model.ListItem
import pt.iade.ei.cobuy.ui.theme.COBUYTheme
import pt.iade.ei.cobuy.ui.theme.OrangePrimary
import pt.iade.ei.cobuy.ui.theme.TextDark
import pt.iade.ei.cobuy.ui.theme.TextLight

@Composable
fun ShoppingItemCard(item: ListItem, onItemClicked: (ListItem) -> Unit) {

    // Format quantity: if 2.0 → "2", else 2.5 → "2.5"
    val qtyText = item.qty?.let {
        if (it % 1.0 == 0.0) it.toInt().toString() else it.toString()
    } ?: ""

    val unitText = item.unit ?: ""

    Card(
        modifier = Modifier
            .fillMaxWidth()
            .height(70.dp),
        shape = RoundedCornerShape(18.dp),
        elevation = CardDefaults.cardElevation(defaultElevation = 5.dp),
        colors = CardDefaults.cardColors(containerColor = TextLight)
    ) {
        Row(
            modifier = Modifier
                .fillMaxSize()
                .padding(horizontal = 20.dp),
            verticalAlignment = Alignment.CenterVertically,
            horizontalArrangement = Arrangement.SpaceBetween
        ) {

            // LEFT SIDE — Icon + Name + Qty/Unit
            Row(verticalAlignment = Alignment.CenterVertically) {

                // Checkbox icon
                IconButton(onClick = { onItemClicked(item.copy(done = !item.done)) }) {
                    Icon(
                        imageVector = if (item.done) Icons.Default.CheckCircle else Icons.Default.RadioButtonUnchecked,
                        contentDescription = "Marcar como feito",
                        tint = if (item.done) OrangePrimary else Color.Gray,
                        modifier = Modifier.size(26.dp)
                    )
                }

                Spacer(Modifier.width(8.dp))

                // NAME + QTY/UNIT stacked
                Column {
                    // Item name
                    Text(
                        text = item.name,
                        style = MaterialTheme.typography.bodyLarge.copy(
                            color = if (item.done) Color.Gray else TextDark,
                            fontSize = 16.sp,
                            fontWeight = FontWeight.Medium
                        )
                    )

                    // Quantity + unit below (only if available)
                    if (qtyText.isNotEmpty() || unitText.isNotEmpty()) {
                        Text(
                            text = "$qtyText $unitText",
                            style = MaterialTheme.typography.bodySmall.copy(
                                color = Color.Gray,
                                fontSize = 13.sp
                            )
                        )
                    }
                }
            }
        }
    }
}

@Preview(showBackground = true)
@Composable
fun ShoppingItemCardDonePreview() {
    val doneItem = ListItem(
        id = 2,
        name = "Arroz",
        qty = 2.0,
        unit = "kg",
        done = true
    )

    COBUYTheme {
        ShoppingItemCard(
            item = doneItem,
            onItemClicked = {}
        )
    }
}
