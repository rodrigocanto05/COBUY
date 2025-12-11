package pt.iade.ei.cobuy.ui.components.cards

import androidx.compose.animation.animateContentSize
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.CheckCircle
import androidx.compose.material.icons.filled.Delete
import androidx.compose.material.icons.filled.RadioButtonUnchecked
import androidx.compose.material3.*
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.compose.ui.text.font.FontWeight
import pt.iade.ei.cobuy.storage.model.ListItem
import pt.iade.ei.cobuy.storage.model.Item
import pt.iade.ei.cobuy.storage.model.Unit as ItemUnit
import pt.iade.ei.cobuy.ui.theme.COBUYTheme
import pt.iade.ei.cobuy.ui.theme.OrangePrimary
import pt.iade.ei.cobuy.ui.theme.TextDark
import pt.iade.ei.cobuy.ui.theme.TextLight

@Composable
fun ShoppingItemCard(
    item: ListItem,
    onItemClicked: (ListItem) -> Unit,
    onDeleteClicked: (ListItem) -> Unit = {}
) {
    val qtyText = item.qty?.let {
        if (it % 1.0 == 0.0) it.toInt().toString() else it.toString()
    } ?: ""

    val unitText = item.unit.name

    Card(
        modifier = Modifier
            .fillMaxWidth()
            .animateContentSize()
            .padding(vertical = 2.dp),
        shape = RoundedCornerShape(18.dp),
        elevation = CardDefaults.cardElevation(defaultElevation = 5.dp),
        colors = CardDefaults.cardColors(containerColor = TextLight)
    ) {
        Row(
            modifier = Modifier
                .fillMaxWidth()
                .padding(horizontal = 20.dp, vertical = 12.dp),
            verticalAlignment = Alignment.CenterVertically,
            horizontalArrangement = Arrangement.SpaceBetween
        ) {

            Row(verticalAlignment = Alignment.CenterVertically) {

                IconButton(onClick = { onItemClicked(item) }) {
                    Icon(
                        imageVector = if (item.done)
                            Icons.Filled.CheckCircle
                        else
                            Icons.Filled.RadioButtonUnchecked,
                        contentDescription = "Marcar como feito",
                        tint = if (item.done) OrangePrimary else Color.Gray,
                        modifier = Modifier.size(26.dp)
                    )
                }

                Spacer(Modifier.width(8.dp))

                Column {
                    Text(
                        text = item.item.name,
                        style = MaterialTheme.typography.bodyLarge.copy(
                            color = if (item.done) Color.Gray else TextDark,
                            fontSize = 16.sp,
                            fontWeight = FontWeight.Medium
                        )
                    )

                    if (qtyText.isNotEmpty() || unitText.isNotEmpty()) {
                        Text(
                            text = "$qtyText $unitText".trim(),
                            style = MaterialTheme.typography.bodySmall.copy(
                                color = Color.Gray,
                                fontSize = 13.sp
                            )
                        )
                    }
                }
            }

            IconButton(onClick = { onDeleteClicked(item) }) {
                Icon(
                    imageVector = Icons.Filled.Delete,
                    contentDescription = "Apagar item",
                    tint = Color.Red.copy(alpha = 0.85f),
                    modifier = Modifier.size(24.dp)
                )
            }
        }
    }
}

@Preview(showBackground = true)
@Composable
fun ShoppingItemCardDonePreview() {
    val doneItem = ListItem(
        id = 2,
        qty = 2.0,
        done = true,
        updatedAt = null,
        item = Item(
            id = 1,
            name = "Arroz"
        ),
        unit = ItemUnit(
            id = 3,
            name = "kg"
        )
    )

    COBUYTheme {
        ShoppingItemCard(
            item = doneItem,
            onItemClicked = {},
            onDeleteClicked = {}
        )
    }
}
