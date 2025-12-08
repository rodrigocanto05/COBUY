package pt.iade.ei.cobuy.ui.screens.list

import android.widget.Toast
import androidx.compose.foundation.ExperimentalFoundationApi
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.grid.GridCells
import androidx.compose.foundation.lazy.grid.LazyVerticalGrid
import androidx.compose.foundation.lazy.grid.items as gridItems
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Add
import androidx.compose.material3.AlertDialog
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.CircularProgressIndicator
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.FloatingActionButton
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.material3.TextButton
import androidx.compose.material3.TextField
import androidx.compose.material3.TextFieldDefaults
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalContext
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
import pt.iade.ei.cobuy.network.viewmodels.groups.GroupListsViewModel
import pt.iade.ei.cobuy.network.viewmodels.groups.GroupMembersViewModel
import pt.iade.ei.cobuy.network.viewmodels.SessionViewModel
import pt.iade.ei.cobuy.storage.model.ShoppingList
import pt.iade.ei.cobuy.ui.components.cards.ShoppingListCard
import pt.iade.ei.cobuy.ui.components.topbar.CoBuyTopBar
import pt.iade.ei.cobuy.ui.navigation.NavPath
import pt.iade.ei.cobuy.ui.screens.MembersSideCard
import pt.iade.ei.cobuy.ui.theme.OrangePrimary
import pt.iade.ei.cobuy.ui.theme.TextDark

@OptIn(ExperimentalMaterial3Api::class, ExperimentalFoundationApi::class)
@Composable
fun GroupListScreen(
    navController: NavController,
    groupId: Int,
    groupName: String,
    listsViewModel: GroupListsViewModel = viewModel(),
    membersViewModel: GroupMembersViewModel = viewModel()
) {
    val listsUiState = listsViewModel.uiState
    val membersUiState = membersViewModel.uiState
    val context = LocalContext.current
    val currentUserId = SessionViewModel.currentUserId

    var showCreateDialog by remember { mutableStateOf(false) }
    var showMembersSheet by remember { mutableStateOf(false) }

    val isCurrentUserOwner = remember(membersUiState.members, currentUserId) {
        currentUserId != null &&
                membersUiState.members.any {
                    it.id == currentUserId && it.role.equals("owner", ignoreCase = true)
                }
    }

    LaunchedEffect(groupId, currentUserId) {
        currentUserId?.let { uid ->
            listsViewModel.loadGroupLists(groupId, uid)
        }
    }

    Scaffold(
        topBar = {
            CoBuyTopBar(
                title = groupName,
                navController = navController,
                actions = {
                    TextButton(
                        onClick = {
                            showMembersSheet = !showMembersSheet
                            if (showMembersSheet) {
                                membersViewModel.loadMembers(groupId)
                                membersViewModel.loadGroupCode(groupId)
                            }
                        }
                    ) {
                        Text(
                            text = "Membros",
                            color = OrangePrimary,
                            fontWeight = FontWeight.SemiBold
                        )
                    }
                }
            )
        },
        floatingActionButton = {
            if (!showMembersSheet) {
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
        }
    ) { padding ->

        Box(
            modifier = Modifier
                .fillMaxSize()
                .padding(padding)
        ) {

            Column(
                modifier = Modifier
                    .fillMaxSize()
                    .padding(horizontal = 24.dp, vertical = 16.dp)
            ) {

                if (currentUserId == null) {
                    Box(
                        modifier = Modifier.fillMaxSize(),
                        contentAlignment = Alignment.Center
                    ) {
                        Text(
                            text = "Utilizador não definido. Faz login novamente.",
                            color = MaterialTheme.colorScheme.error
                        )
                    }
                } else {
                    when {
                        listsUiState.isLoading -> {
                            Box(Modifier.fillMaxSize(), Alignment.Center) {
                                CircularProgressIndicator(color = OrangePrimary)
                            }
                        }

                        listsUiState.error != null -> {
                            Box(Modifier.fillMaxSize(), Alignment.Center) {
                                Text(
                                    text = "Erro: ${listsUiState.error}",
                                    color = MaterialTheme.colorScheme.error
                                )
                            }
                        }

                        else -> {
                            LazyVerticalGrid(
                                columns = GridCells.Fixed(2),
                                verticalArrangement = Arrangement.spacedBy(16.dp),
                                horizontalArrangement = Arrangement.spacedBy(16.dp),
                                modifier = Modifier.fillMaxSize(),
                                contentPadding = PaddingValues(bottom = 96.dp)
                            ) {
                                gridItems(listsUiState.lists) { list ->
                                    ShoppingListCard(
                                        list = list,
                                        onClick = {
                                            navController.navigate(
                                                NavPath.ListItems.withArgs(
                                                    listId = list.id,
                                                    listName = list.title
                                                )
                                            )
                                        }
                                    )
                                }
                            }
                        }
                    }
                }
            }

            MembersSideCard(
                visible = showMembersSheet,
                memberships = membersUiState.members,
                groupCode = membersUiState.groupCode,
                onDismiss = { showMembersSheet = false },
                onLeaveGroup = {
                    membersViewModel.leaveGroup(groupId) { ok, msg ->
                        if (ok) {
                            Toast.makeText(
                                context,
                                "Saíste do grupo",
                                Toast.LENGTH_SHORT
                            ).show()
                            showMembersSheet = false
                            navController.popBackStack()
                        } else {
                            Toast.makeText(
                                context,
                                msg ?: "Erro ao sair do grupo",
                                Toast.LENGTH_SHORT
                            ).show()
                        }
                    }
                },
                onKickMember = { member ->
                    membersViewModel.removeMember(groupId, member.id) { ok, msg ->
                        if (ok) {
                            Toast.makeText(
                                context,
                                "Membro expulso",
                                Toast.LENGTH_SHORT
                            ).show()
                        } else {
                            Toast.makeText(
                                context,
                                msg ?: "Erro ao expulsar membro",
                                Toast.LENGTH_SHORT
                            ).show()
                        }
                    }
                },
                currentUserId = currentUserId,
                isCurrentUserOwner = isCurrentUserOwner
            )

            if (showCreateDialog && currentUserId != null) {
                CreateListDialog(
                    onDismiss = { showCreateDialog = false },
                    onConfirm = { name, description ->
                        val descOrNull = description.trim().ifBlank { null }

                        listsViewModel.createList(
                            groupId = groupId,
                            userId = currentUserId,
                            title = name.trim(),
                            description = descOrNull
                        )

                        showCreateDialog = false
                    }
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

    val logoSize = 90.dp
    val overlap = 45.dp

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
                    Card(
                        shape = RoundedCornerShape(30.dp),
                        colors = CardDefaults.cardColors(
                            containerColor = Color(0xFFFFE4C2)
                        ),
                        elevation = CardDefaults.cardElevation(8.dp),
                        modifier = Modifier
                            .align(Alignment.TopCenter)
                            .padding(top = overlap)
                            .fillMaxWidth()
                    ) {
                        Column(
                            modifier = Modifier
                                .fillMaxWidth()
                                .padding(
                                    top = 60.dp,
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

                    Card(
                        shape = RoundedCornerShape(26.dp),
                        colors = CardDefaults.cardColors(
                            containerColor = Color(0xFFFFE4C2)
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
fun GroupListScreenPreview() {
    val nav = rememberNavController()
    GroupListScreen(
        navController = nav,
        groupId = 1,
        groupName = "Grupo Exemplo"
    )
}
