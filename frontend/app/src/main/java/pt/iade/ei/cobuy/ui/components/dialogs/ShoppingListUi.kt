package pt.iade.ei.cobuy.ui.components.dialogs

import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material3.*
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp

data class ShoppingListUi(
    val id: Int,
    val title: String
)

@Composable
fun SelectListDialog(
    show: Boolean,
    lists: List<ShoppingListUi>,
    onDismiss: () -> Unit,
    onSelect: (ShoppingListUi) -> Unit
) {
    if (!show) return

    AlertDialog(
        onDismissRequest = onDismiss,
        title = { Text("Escolhe a lista") },
        text = {
            if (lists.isEmpty()) {
                Text("Ainda não tens listas.")
            } else {
                LazyColumn(
                    modifier = Modifier
                        .fillMaxWidth()
                        .heightIn(max = 360.dp)
                ) {
                    items(lists, key = { it.id }) { list ->
                        Column(
                            modifier = Modifier
                                .fillMaxWidth()
                                .clickable { onSelect(list) }
                                .padding(vertical = 12.dp, horizontal = 8.dp)
                        ) {
                            Text(list.title, fontWeight = FontWeight.SemiBold)
                            Divider(modifier = Modifier.padding(top = 12.dp))
                        }
                    }
                }
            }
        },
        confirmButton = {
            TextButton(onClick = onDismiss) { Text("Cancelar") }
        }
    )
}
