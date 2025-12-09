package pt.iade.ei.cobuy.ui.screens.list

import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Add
import androidx.compose.material3.AlertDialog
import androidx.compose.material3.Button
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.FloatingActionButton
import androidx.compose.material3.Icon
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.material3.TextButton
import androidx.compose.material3.TextField
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.lifecycle.viewmodel.compose.viewModel
import androidx.navigation.NavController
import androidx.navigation.compose.rememberNavController
import pt.iade.ei.cobuy.network.viewmodels.lists.ListItemsViewModel
import pt.iade.ei.cobuy.ui.components.cards.ShoppingItemCard
import pt.iade.ei.cobuy.ui.components.topbar.CoBuyTopBar
import pt.iade.ei.cobuy.ui.theme.BackgroundLight
import pt.iade.ei.cobuy.ui.theme.COBUYTheme
import pt.iade.ei.cobuy.ui.theme.OrangePrimary

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun ListItemsScreen(
    navController: NavController,
    listId: Int,
    listName: String,
    viewModel: ListItemsViewModel = viewModel()
) {
    LaunchedEffect(listId) {
        viewModel.loadItems(listId)
    }

    val uiState = viewModel.uiState
    val items = uiState.items
    val isLoading = uiState.isLoading
    val errorMessage = uiState.error

    var showAddDialog by remember { mutableStateOf(false) }
    var newItemName by remember { mutableStateOf("") }
    var newItemQty by remember { mutableStateOf("") }
    var newItemUnit by remember { mutableStateOf("") }

    fun clearNewItemFields() {
        newItemName = ""
        newItemQty = ""
        newItemUnit = ""
    }

    val userId = 1
    val defaultItemId = 1
    val defaultUnitId = 1

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
                isLoading -> {
                    Box(
                        modifier = Modifier.fillMaxSize(),
                        contentAlignment = Alignment.Center
                    ) {
                        Text("A carregar itens...")
                    }
                }

                errorMessage != null -> {
                    Box(
                        modifier = Modifier.fillMaxSize(),
                        contentAlignment = Alignment.Center
                    ) {
                        Text(text = errorMessage)
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
                                onItemClicked = { updated ->
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
                    clearNewItemFields()
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
                        TextField(
                            value = newItemUnit,
                            onValueChange = { newItemUnit = it },
                            label = { Text("Unidade (ex: uni, L, kg)") },
                            singleLine = true
                        )
                    }
                },
                confirmButton = {
                    Button(
                        onClick = {
                            if (newItemName.isNotBlank()) {
                                val qty = newItemQty.toDoubleOrNull() ?: 1.0

                                viewModel.addItem(
                                    listId = listId,
                                    userId = userId,
                                    itemId = defaultItemId,
                                    qty = qty,
                                    unitId = defaultUnitId
                                ) { ok, _ ->
                                    if (ok) {
                                        showAddDialog = false
                                        clearNewItemFields()
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
                            clearNewItemFields()
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
fun ListItemsScreenPreview() {
    COBUYTheme {
        ListItemsScreen(
            navController = rememberNavController(),
            listId = 1,
            listName = "Churrasco Rapazes"
        )
    }
}
