package pt.iade.ei.cobuy.ui.screens.list

import android.widget.Toast
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Add
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.lifecycle.viewmodel.compose.viewModel
import androidx.navigation.NavController
import androidx.navigation.compose.rememberNavController
import pt.iade.ei.cobuy.network.viewmodels.lists.ListItemsViewModel
import pt.iade.ei.cobuy.network.viewmodels.lists.UnitViewModel
import pt.iade.ei.cobuy.ui.components.cards.ShoppingItemCard
import pt.iade.ei.cobuy.ui.components.topbar.CoBuyTopBar
import pt.iade.ei.cobuy.ui.screens.previews.ListItemsScreenUi
import pt.iade.ei.cobuy.ui.theme.BackgroundLight
import pt.iade.ei.cobuy.ui.theme.COBUYTheme
import pt.iade.ei.cobuy.ui.theme.OrangePrimary
import pt.iade.ei.cobuy.storage.model.Unit as UnitModel

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun ListItemsScreen(
    navController: NavController,
    listId: Int,
    listName: String,
    viewModel: ListItemsViewModel = viewModel()
) {
    val unitViewModel: UnitViewModel = viewModel()
    val context = LocalContext.current

    LaunchedEffect(listId) {
        viewModel.loadItems(listId)
    }

    LaunchedEffect(Unit) {
        unitViewModel.loadUnits()
    }

    val uiState = viewModel.uiState
    val items = uiState.items

    var showAddDialog by remember { mutableStateOf(false) }
    var newItemName by remember { mutableStateOf("") }
    var newItemQty by remember { mutableStateOf("") }
    var newItemUnit by remember { mutableStateOf("un") }
    var unitExpanded by remember { mutableStateOf(false) }

    Scaffold(
        topBar = {
            CoBuyTopBar(
                title = listName,
                navController = navController
            )
        },
        containerColor = BackgroundLight,
        floatingActionButton = {
            FloatingActionButton(
                onClick = { showAddDialog = true },
                containerColor = OrangePrimary
            ) {
                Icon(
                    imageVector = Icons.Default.Add,
                    contentDescription = "Adicionar item"
                )
            }
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
                fontWeight = FontWeight.SemiBold,
                modifier = Modifier.padding(bottom = 12.dp)
            )

            when {
                uiState.isLoading -> {
                    Box(
                        modifier = Modifier.fillMaxSize(),
                        contentAlignment = Alignment.Center
                    ) {
                        Text("A carregar itens...")
                    }
                }

                uiState.error != null -> {
                    Box(
                        modifier = Modifier.fillMaxSize(),
                        contentAlignment = Alignment.Center
                    ) {
                        Text(text = uiState.error ?: "")
                    }
                }

                items.isEmpty() -> {
                    Box(
                        modifier = Modifier.fillMaxSize(),
                        contentAlignment = Alignment.Center
                    ) {
                        Text(text = "Ainda não há itens nesta lista.")
                    }
                }

                else -> {
                    LazyColumn(
                        verticalArrangement = Arrangement.spacedBy(12.dp),
                        modifier = Modifier.fillMaxSize()
                    ) {
                        items(items, key = { it.id }) { item ->
                            ShoppingItemCard(
                                item = item,
                                onItemClicked = {
                                    viewModel.toggleDone(
                                        listId,
                                        item.id
                                    ) { _, _ -> }
                                },
                                onDeleteClicked = {
                                    val name = item.item.name
                                    Toast
                                        .makeText(
                                            context,
                                            "$name foi removido",
                                            Toast.LENGTH_SHORT
                                        )
                                        .show()

                                    viewModel.deleteItem(
                                        listId,
                                        item.id
                                    ) { _, _ -> }
                                }
                            )
                        }
                    }
                }
            }
        }

        if (showAddDialog) {
            AlertDialog(
                onDismissRequest = {
                    showAddDialog = false
                    newItemName = ""
                    newItemQty = ""
                    newItemUnit = "un"
                },
                title = { Text(text = "Adicionar item") },
                text = {
                    Column(verticalArrangement = Arrangement.spacedBy(8.dp)) {
                        TextField(
                            value = newItemName,
                            onValueChange = { newItemName = it },
                            label = { Text("Nome do item") },
                            singleLine = true
                        )
                        TextField(
                            value = newItemQty,
                            onValueChange = { newItemQty = it },
                            label = { Text("Quantidade") },
                            singleLine = true
                        )

                        ExposedDropdownMenuBox(
                            expanded = unitExpanded,
                            onExpandedChange = { unitExpanded = !unitExpanded }
                        ) {
                            TextField(
                                value = newItemUnit,
                                onValueChange = {},
                                readOnly = true,
                                label = { Text("Unidade") },
                                modifier = Modifier
                                    .menuAnchor()
                                    .fillMaxWidth(),
                                trailingIcon = {
                                    ExposedDropdownMenuDefaults.TrailingIcon(
                                        expanded = unitExpanded
                                    )
                                }
                            )
                            ExposedDropdownMenu(
                                expanded = unitExpanded,
                                onDismissRequest = { unitExpanded = false }
                            ) {
                                listOf("kg", "g", "L", "ml", "un").forEach { option ->
                                    DropdownMenuItem(
                                        text = { Text(option) },
                                        onClick = {
                                            newItemUnit = option
                                            unitExpanded = false
                                        }
                                    )
                                }
                            }
                        }
                    }
                },
                confirmButton = {
                    Button(
                        onClick = {
                            if (newItemName.isNotBlank()) {

                                val qty = newItemQty.toDoubleOrNull() ?: 1.0

                                val unitId = when (newItemUnit.lowercase()) {
                                    "kg" -> 1
                                    "g" -> 2
                                    "l" -> 3
                                    "ml" -> 4
                                    else -> 5
                                }

                                val nameForToast = newItemName

                                viewModel.addItem(
                                    listId,
                                    newItemName,
                                    qty,
                                    unitId
                                ) { ok, error ->
                                    if (ok) {
                                        Toast
                                            .makeText(
                                                context,
                                                "$nameForToast foi adicionado à lista",
                                                Toast.LENGTH_SHORT
                                            )
                                            .show()

                                        showAddDialog = false
                                        newItemName = ""
                                        newItemQty = ""
                                        newItemUnit = "un"
                                    } else if (error != null) {
                                        Toast
                                            .makeText(
                                                context,
                                                error,
                                                Toast.LENGTH_SHORT
                                            )
                                            .show()
                                    }
                                }
                            }
                        }
                    ) {
                        Text("Adicionar")
                    }
                },
                dismissButton = {
                    TextButton(
                        onClick = {
                            showAddDialog = false
                            newItemName = ""
                            newItemQty = ""
                            newItemUnit = "un"
                        }
                    ) {
                        Text("Cancelar")
                    }
                }
            )
        }
    }
}

@Preview(showBackground = true)
@Composable
fun ListItemsScreenUiPreview() {
    ListItemsScreenUi()
}

