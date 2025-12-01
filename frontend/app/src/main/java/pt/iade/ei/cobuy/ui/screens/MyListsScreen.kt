package pt.iade.ei.cobuy.ui.screens

import androidx.compose.foundation.ExperimentalFoundationApi
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.grid.GridCells
import androidx.compose.foundation.lazy.grid.LazyVerticalGrid
import androidx.compose.foundation.lazy.grid.items
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Add
import androidx.compose.material.icons.filled.List
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.lifecycle.viewmodel.compose.viewModel
import androidx.navigation.NavController
import androidx.navigation.compose.rememberNavController
import pt.iade.ei.cobuy.R
import pt.iade.ei.cobuy.network.viewmodels.GroupListsViewModel
import pt.iade.ei.cobuy.storage.model.ShoppingList
import pt.iade.ei.cobuy.ui.components.topbar.CoBuyTopBar
import pt.iade.ei.cobuy.ui.theme.BackgroundLight
import pt.iade.ei.cobuy.ui.theme.OrangePrimary
import pt.iade.ei.cobuy.ui.theme.TextDark

@OptIn(ExperimentalMaterial3Api::class, ExperimentalFoundationApi::class)
@Composable
fun MyListsScreen(
    navController: NavController,
    groupId: Int,
    userId: Int,
    viewModel: GroupListsViewModel = viewModel()
) {
    val uiState = viewModel.uiState
    var showCreateDialog by remember { mutableStateOf(false) }

    LaunchedEffect(groupId, userId) {
        viewModel.loadGroupLists(groupId, userId)
    }

    Scaffold(
        topBar = { CoBuyTopBar("Listas do Grupo", navController) },
        containerColor = BackgroundLight,
        floatingActionButton = {
            FloatingActionButton(
                onClick = { showCreateDialog = true },
                containerColor = OrangePrimary,
                shape = RoundedCornerShape(20.dp)
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
                uiState.isLoading -> {
                    Box(Modifier.fillMaxSize(), Alignment.Center) {
                        CircularProgressIndicator(color = OrangePrimary)
                    }
                }

                uiState.error != null -> {
                    Box(Modifier.fillMaxSize(), Alignment.Center) {
                        Text(
                            text = "Erro: ${uiState.error}",
                            color = MaterialTheme.colorScheme.error,
                            style = MaterialTheme.typography.bodyLarge
                        )
                    }
                }

                uiState.lists.isEmpty() -> {
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
                    LazyVerticalGrid(
                        // 2 colunas, todas as caixas com o mesmo tamanho
                        columns = GridCells.Fixed(2),
                        verticalArrangement = Arrangement.spacedBy(16.dp),
                        horizontalArrangement = Arrangement.spacedBy(16.dp),
                        modifier = Modifier.fillMaxSize(),
                        contentPadding = PaddingValues(bottom = 96.dp)
                    ) {
                        items(uiState.lists) { list ->
                            ShoppingListCard(list = list)
                        }
                    }
                }
            }
        }

        if (showCreateDialog) {
            CreateListDialog(
                onDismiss = { showCreateDialog = false },
                onConfirm = { name, description ->
                    viewModel.createList(
                        groupId = groupId,
                        userId = userId,
                        title = name,
                        description = description
                    ) { _, _ -> }
                    showCreateDialog = false
                }
            )
        }
    }
}

@Composable
fun ShoppingListCard(list: ShoppingList) {
    Card(
        modifier = Modifier
            .fillMaxWidth()
            .aspectRatio(1.1f), // mesma proporção para todas as cards
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

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun CreateListDialog(
    onDismiss: () -> Unit,
    onConfirm: (String, String) -> Unit
) {
    var name by remember { mutableStateOf("") }
    var description by remember { mutableStateOf("") }

    val logoSize = 90.dp        // tamanho do card do logo
    val overlap = 45.dp         // quanto o logo "entra" na aba

    AlertDialog(
        onDismissRequest = onDismiss,
        confirmButton = {},
        title = null,
        text = {
            Column(
                modifier = Modifier.fillMaxWidth(),
                horizontalAlignment = Alignment.CenterHorizontally
            ) {

                Box(
                    modifier = Modifier.fillMaxWidth(0.9f)
                ) {
                    // ---------- ABA PRINCIPAL, DESCIDA PARA DAR ESPAÇO AO LOGO ----------
                    Card(
                        shape = RoundedCornerShape(30.dp),
                        colors = CardDefaults.cardColors(
                            containerColor = Color(0xFFFFE4C2)
                        ),
                        elevation = CardDefaults.cardElevation(8.dp),
                        modifier = Modifier
                            .align(Alignment.TopCenter)
                            .padding(top = overlap)   // aba desce 45dp
                            .fillMaxWidth()
                    ) {
                        Column(
                            modifier = Modifier
                                .fillMaxWidth()
                                .padding(
                                    top = 60.dp,   // 🔥 aumentei para não ficar por baixo do logo
                                    start = 22.dp,
                                    end = 22.dp,
                                    bottom = 28.dp
                                ),
                            horizontalAlignment = Alignment.CenterHorizontally
                        ) {

                            Text(
                                text = "Criar nova lista",
                                style = MaterialTheme.typography.titleMedium.copy(
                                    fontWeight = FontWeight.Bold,
                                    fontSize = 20.sp,
                                    color = TextDark
                                )
                            )

                            Spacer(modifier = Modifier.height(4.dp))

                            Text(
                                text = "Define o nome e uma breve descrição.",
                                style = MaterialTheme.typography.bodyMedium.copy(
                                    color = TextDark.copy(alpha = 0.7f),
                                    fontSize = 14.sp
                                ),
                                textAlign = TextAlign.Center
                            )

                            Spacer(modifier = Modifier.height(24.dp))

                            // ----------- INPUT 1 ARREDONDADO ----------
                            Box(
                                modifier = Modifier
                                    .fillMaxWidth()
                                    .background(Color.White, RoundedCornerShape(20.dp))
                            ) {
                                TextField(
                                    value = name,
                                    onValueChange = { name = it },
                                    placeholder = { Text("Nome da lista") },
                                    singleLine = true,
                                    modifier = Modifier.fillMaxWidth(),
                                    shape = RoundedCornerShape(20.dp),
                                    colors = TextFieldDefaults.colors(
                                        focusedContainerColor = Color.Transparent,
                                        unfocusedContainerColor = Color.Transparent,
                                        cursorColor = OrangePrimary,
                                        focusedIndicatorColor = Color.Transparent,
                                        unfocusedIndicatorColor = Color.Transparent
                                    )
                                )
                            }

                            Spacer(modifier = Modifier.height(15.dp))

                            // ----------- INPUT 2 ARREDONDADO ----------
                            Box(
                                modifier = Modifier
                                    .fillMaxWidth()
                                    .background(Color.White, RoundedCornerShape(20.dp))
                            ) {
                                TextField(
                                    value = description,
                                    onValueChange = { description = it },
                                    placeholder = { Text("Descrição (opcional)") },
                                    modifier = Modifier
                                        .fillMaxWidth()
                                        .heightIn(min = 80.dp),
                                    shape = RoundedCornerShape(20.dp),
                                    colors = TextFieldDefaults.colors(
                                        focusedContainerColor = Color.Transparent,
                                        unfocusedContainerColor = Color.Transparent,
                                        cursorColor = OrangePrimary,
                                        focusedIndicatorColor = Color.Transparent,
                                        unfocusedIndicatorColor = Color.Transparent
                                    )
                                )
                            }
                        }
                    }

                    // ---------- BOTÃO X NO TOPO DA ABA ----------
                    TextButton(
                        onClick = onDismiss,
                        modifier = Modifier
                            .align(Alignment.TopEnd)
                            .padding(top = overlap + 4.dp, end = 14.dp),
                        contentPadding = PaddingValues(0.dp)
                    ) {
                        Text(
                            "✕",
                            fontSize = 22.sp,
                            fontWeight = FontWeight.Bold,
                            color = TextDark.copy(alpha = 0.8f)
                        )
                    }

                    // ---------- LOGO, MESMA COR DO FUNDO DA ABA ----------
                    Card(
                        shape = RoundedCornerShape(26.dp),
                        colors = CardDefaults.cardColors(
                            containerColor = Color(0xFFFFE4C2)  // 🔥 igual ao fundo da aba
                        ),
                        elevation = CardDefaults.cardElevation(12.dp),
                        modifier = Modifier
                            .size(logoSize)
                            .align(Alignment.TopCenter)
                    ) {
                        Box(contentAlignment = Alignment.Center) {
                            Image(
                                painter = painterResource(id = R.drawable.logo),
                                contentDescription = "CoBuy"
                            )
                        }
                    }
                }

                Spacer(modifier = Modifier.height(28.dp))

                // ---------- BOTÃO FORA DA ABA ----------
                Button(
                    onClick = {
                        if (name.isNotBlank())
                            onConfirm(name.trim(), description.trim())
                    },
                    enabled = name.isNotBlank(),
                    modifier = Modifier
                        .fillMaxWidth(0.75f)
                        .height(48.dp),
                    colors = ButtonDefaults.buttonColors(
                        containerColor = OrangePrimary,
                        disabledContainerColor = OrangePrimary.copy(alpha = 0.4f)
                    ),
                    shape = RoundedCornerShape(26.dp)
                ) {
                    Text(
                        text = "Criar lista",
                        fontSize = 16.sp,
                        fontWeight = FontWeight.SemiBold,
                        color = Color.White
                    )
                }
            }
        },
        containerColor = Color.Transparent
    )
}



@Preview(showBackground = true)
@Composable
fun MyListsScreenPreview() {
    val nav = rememberNavController()
    MyListsScreen(
        navController = nav,
        groupId = 1,
        userId = 1
    )
}
