package pt.iade.ei.cobuy.ui.screens

import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Add
import androidx.compose.material.icons.filled.List
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.lifecycle.viewmodel.compose.viewModel
import androidx.navigation.NavController
import androidx.navigation.compose.rememberNavController
import pt.iade.ei.cobuy.network.viewmodels.GroupViewModel
import pt.iade.ei.cobuy.storage.model.ShoppingList
import pt.iade.ei.cobuy.ui.components.topbar.CoBuyTopBar
import pt.iade.ei.cobuy.ui.theme.BackgroundLight
import pt.iade.ei.cobuy.ui.theme.OrangePrimary
import pt.iade.ei.cobuy.ui.theme.TextDark

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun MyListsScreen(
    navController: NavController,
    groupId: Int
) {
    val viewModel: GroupViewModel = viewModel()

    var lists by remember { mutableStateOf<List<ShoppingList>>(emptyList()) }
    var isLoading by remember { mutableStateOf(true) }
    var errorMessage by remember { mutableStateOf<String?>(null) }

    LaunchedEffect(groupId) {
        viewModel.getGroupLists(groupId) { result, error ->
            if (error != null) errorMessage = error
            else lists = result ?: emptyList()
            isLoading = false
        }
    }

    Scaffold(
        topBar = { CoBuyTopBar("Listas do Grupo", navController) },
        containerColor = BackgroundLight,
        floatingActionButton = {
            FloatingActionButton(
                onClick = {
                    // TODO: navegar para a screen de criar nova lista
                    // navController.navigate(NavPath.CreateList.withArgs(groupId))
                },
                containerColor = OrangePrimary
            ) {
                Icon(
                    imageVector = Icons.Filled.Add,
                    contentDescription = "Criar nova lista",
                    tint = MaterialTheme.colorScheme.onPrimary
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

            when {
                isLoading -> {
                    Box(Modifier.fillMaxSize(), Alignment.Center) {
                        CircularProgressIndicator(color = OrangePrimary)
                    }
                }

                errorMessage != null -> {
                    Box(Modifier.fillMaxSize(), Alignment.Center) {
                        Text(
                            text = "Erro: $errorMessage",
                            color = MaterialTheme.colorScheme.error,
                            style = MaterialTheme.typography.bodyLarge
                        )
                    }
                }

                lists.isEmpty() -> {
                    Box(
                        modifier = Modifier.fillMaxSize(),
                        contentAlignment = Alignment.Center
                    ) {
                        Card(
                            shape = RoundedCornerShape(24.dp),
                            colors = CardDefaults.cardColors(
                                containerColor = MaterialTheme.colorScheme.surface.copy(alpha = 0.9f)
                            ),
                            elevation = CardDefaults.cardElevation(defaultElevation = 6.dp),
                            modifier = Modifier
                                .padding(20.dp)
                                .fillMaxWidth()
                        ) {
                            Column(
                                modifier = Modifier
                                    .padding(28.dp)
                                    .fillMaxWidth(),
                                horizontalAlignment = Alignment.CenterHorizontally
                            ) {
                                Icon(
                                    imageVector = Icons.Filled.List,
                                    contentDescription = null,
                                    tint = OrangePrimary,
                                    modifier = Modifier.size(46.dp)
                                )

                                Spacer(modifier = Modifier.height(14.dp))

                                Text(
                                    text = "Ainda não tens listas neste grupo",
                                    style = MaterialTheme.typography.titleMedium.copy(
                                        color = TextDark,
                                        fontWeight = FontWeight.SemiBold,
                                        fontSize = 18.sp
                                    ),
                                    textAlign = TextAlign.Center
                                )

                                Spacer(modifier = Modifier.height(6.dp))

                                Text(
                                    text = "Toca no ícone + para criares a primeira lista.",
                                    style = MaterialTheme.typography.bodyMedium.copy(
                                        color = TextDark.copy(alpha = 0.6f),
                                        fontSize = 14.sp
                                    ),
                                    textAlign = TextAlign.Center
                                )
                            }
                        }
                    }
                }

                else -> {
                    LazyColumn(
                        verticalArrangement = Arrangement.spacedBy(16.dp),
                        modifier = Modifier.fillMaxSize(),
                        contentPadding = PaddingValues(bottom = 96.dp) // espaço para o FAB
                    ) {
                        items(lists) { list ->
                            ShoppingListCard(list = list)
                        }
                    }
                }
            }
        }
    }
}

@Composable
fun ShoppingListCard(list: ShoppingList) {
    Card(
        modifier = Modifier
            .fillMaxWidth()
            .height(80.dp),
        shape = RoundedCornerShape(18.dp),
        elevation = CardDefaults.cardElevation(defaultElevation = 6.dp),
        colors = CardDefaults.cardColors(containerColor = MaterialTheme.colorScheme.surface)
    ) {
        Row(
            modifier = Modifier
                .fillMaxSize()
                .padding(horizontal = 20.dp),
            verticalAlignment = Alignment.CenterVertically,
            horizontalArrangement = Arrangement.SpaceBetween
        ) {
            Column {
                Text(
                    // ⚠️ TROCA "Lista" pelo campo correto do teu ShoppingList
                    // por exemplo: list.title, list.listName, etc.
                    text = "Lista",
                    style = MaterialTheme.typography.bodyLarge.copy(
                        color = OrangePrimary,
                        fontSize = 17.sp,
                        fontWeight = FontWeight.SemiBold
                    )
                )

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

            // Aqui podes pôr mais ações (ver detalhes, etc.)
        }
    }
}

@Preview(showBackground = true)
@Composable
fun MyListsScreenPreview() {
    val nav = rememberNavController()
    MyListsScreen(navController = nav, groupId = 1)
}
