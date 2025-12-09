package pt.iade.ei.cobuy.ui.screens.list

import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Add
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.lifecycle.viewmodel.compose.viewModel
import androidx.navigation.NavController
import androidx.navigation.compose.rememberNavController
import pt.iade.ei.cobuy.network.viewmodels.SessionViewModel
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
    var newItemUnit by remember { mutableStateOf("un") }

    // dropdown das unidades
    val unitOptions = listOf("un", "L", "kg")
    var unitExpanded by remember { mutableStateOf(false) }

    fun clearNewItemFields() {
        newItemName = ""
        newItemQty = ""
        newItemUnit = "un"
    }

    // tenta usar o userId da sessão; se for null usa 2 (user de teste)
    val userId = SessionViewModel.currentUserId ?: 2

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
                                onItemClicked = { _ ->
                                    // TODO: marcar done no backend mais tarde
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

                        // DROPDOWN de unidades
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
                                unitOptions.forEach { option ->
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
                                    "un", "uni", "und", "unid" -> 1   // ID da unidade "un"
                                    "l", "lt", "litro", "litros" -> 2 // ID da unidade "L"
                                    "kg", "quilo", "kilo" -> 3        // ID da unidade "kg"
                                    else -> 1
                                }

                                viewModel.addItem(
                                    listId = listId,
                                    userId = userId,
                                    name = newItemName,
                                    qty = qty,
                                    unitId = unitId
                                ) { ok, error ->
                                    if (ok) {
                                        showAddDialog = false
                                        clearNewItemFields()
                                    } else if (error != null) {
                                        println("ERRO AO ADICIONAR ITEM: $error")
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
