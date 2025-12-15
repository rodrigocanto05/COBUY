package pt.iade.ei.cobuy.ui.screens.previews

import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Add
import androidx.compose.material3.*
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.navigation.compose.rememberNavController
import pt.iade.ei.cobuy.ui.components.topbar.CoBuyTopBar
import pt.iade.ei.cobuy.ui.theme.BackgroundLight
import pt.iade.ei.cobuy.ui.theme.COBUYTheme
import pt.iade.ei.cobuy.ui.theme.OrangePrimary

private data class UiItem(
    val id: Int,
    val name: String,
    val qty: Double,
    val unit: String,
    val done: Boolean = false
)

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun ListItemsScreenUi(
    listName: String = "Churrasco Rapazes"
) {
    val navController = rememberNavController()

    val fakeItems = listOf(
        UiItem(1, "Carne", 2.0, "kg"),
        UiItem(2, "Pão", 6.0, "un"),
        UiItem(3, "Refrigerante", 3.0, "L", done = true),
        UiItem(4, "Carvão", 1.0, "un")
    )

    Scaffold(
        topBar = { CoBuyTopBar(title = listName, navController = navController) },
        containerColor = BackgroundLight,
        floatingActionButton = {
            FloatingActionButton(
                onClick = {},
                containerColor = OrangePrimary
            ) { Icon(Icons.Default.Add, contentDescription = "Adicionar item") }
        }
    ) { padding ->
        Column(
            modifier = Modifier
                .fillMaxSize()
                .padding(padding)
                .padding(horizontal = 24.dp, vertical = 16.dp)
        ) {
            Text(
                text = "Itens da lista",
                style = MaterialTheme.typography.titleMedium
            )

            Spacer(Modifier.height(12.dp))

            LazyColumn(
                verticalArrangement = Arrangement.spacedBy(12.dp),
                modifier = Modifier.fillMaxSize()
            ) {
                items(fakeItems, key = { it.id }) { item ->
                    Card(
                        modifier = Modifier.fillMaxWidth(),
                        elevation = CardDefaults.cardElevation(defaultElevation = 4.dp)
                    ) {
                        Row(
                            modifier = Modifier
                                .fillMaxWidth()
                                .padding(14.dp),
                            verticalAlignment = Alignment.CenterVertically,
                            horizontalArrangement = Arrangement.SpaceBetween
                        ) {
                            Column {
                                Text(item.name, style = MaterialTheme.typography.titleSmall)
                                Text(
                                    "${item.qty} ${item.unit}",
                                    style = MaterialTheme.typography.bodyMedium,
                                    color = MaterialTheme.colorScheme.onSurface.copy(alpha = 0.65f)
                                )
                            }
                            AssistChip(
                                onClick = {},
                                label = { Text(if (item.done) "Feito" else "Pendente") }
                            )
                        }
                    }
                }
            }
        }
    }
}

@Preview(showBackground = true, device = "spec:width=411dp,height=891dp")
@Composable
fun ListItemsScreenUiPreview() {
    COBUYTheme {
        ListItemsScreenUi()
    }
}
