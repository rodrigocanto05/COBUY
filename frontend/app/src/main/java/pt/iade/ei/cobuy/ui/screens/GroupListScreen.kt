package pt.iade.ei.cobuy.ui.screens

import androidx.compose.foundation.ExperimentalFoundationApi
import androidx.compose.animation.AnimatedVisibility
import androidx.compose.animation.core.animateFloatAsState
import androidx.compose.animation.core.tween
import androidx.compose.animation.fadeIn
import androidx.compose.animation.fadeOut
import androidx.compose.animation.slideInHorizontally
import androidx.compose.animation.slideOutHorizontally
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.gestures.Orientation
import androidx.compose.foundation.gestures.draggable
import androidx.compose.foundation.gestures.rememberDraggableState
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
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
import androidx.compose.material3.HorizontalDivider
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.OutlinedButton
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.material3.TextButton
import androidx.compose.material3.TextField
import androidx.compose.material3.TextFieldDefaults
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.onGloballyPositioned
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.IntOffset
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.lifecycle.viewmodel.compose.viewModel
import androidx.navigation.NavController
import androidx.navigation.compose.rememberNavController
import kotlin.math.roundToInt
import pt.iade.ei.cobuy.R
import pt.iade.ei.cobuy.network.viewmodels.GroupListsViewModel
import pt.iade.ei.cobuy.network.viewmodels.GroupMembersViewModel
import pt.iade.ei.cobuy.storage.model.Membership
import pt.iade.ei.cobuy.storage.model.ShoppingList
import pt.iade.ei.cobuy.ui.components.topbar.CoBuyTopBar
import pt.iade.ei.cobuy.ui.theme.OrangePrimary
import pt.iade.ei.cobuy.ui.theme.TextDark

// -------------------- SIDE SHEET --------------------

@Composable
fun GroupMembersSideSheet(
    visible: Boolean,
    memberships: List<Membership>,
    onDismiss: () -> Unit,
    onLeaveGroup: (() -> Unit)? = null,
    onInviteMember: (() -> Unit)? = null
) {
    var sheetWidth by remember { mutableStateOf(0f) }
    var offsetX by remember { mutableStateOf(0f) }

    val animatedOffsetX by animateFloatAsState(
        targetValue = offsetX,
        animationSpec = tween(durationMillis = 180),
        label = "membersSheetOffset"
    )

    LaunchedEffect(visible) {
        if (visible) offsetX = 0f
    }

    Box(
        modifier = Modifier.fillMaxSize(),
        contentAlignment = Alignment.CenterEnd
    ) {
        AnimatedVisibility(
            visible = visible,
            enter = fadeIn(animationSpec = tween(200)),
            exit = fadeOut(animationSpec = tween(200))
        ) {
            Box(
                modifier = Modifier
                    .fillMaxSize()
                    .background(Color.Black.copy(alpha = 0.35f))
                    .clickable { onDismiss() }
            )
        }

        AnimatedVisibility(
            visible = visible,
            enter = slideInHorizontally(
                initialOffsetX = { it },
                animationSpec = tween(250)
            ) + fadeIn(),
            exit = slideOutHorizontally(
                targetOffsetX = { it },
                animationSpec = tween(250)
            ) + fadeOut()
        ) {
            Box(
                modifier = Modifier
                    .fillMaxHeight()
                    .fillMaxWidth(0.8f)
                    .onGloballyPositioned { coords ->
                        sheetWidth = coords.size.width.toFloat()
                    }
                    .offset { IntOffset(animatedOffsetX.roundToInt(), 0) }
                    .draggable(
                        orientation = Orientation.Horizontal,
                        state = rememberDraggableState { delta ->
                            if (sheetWidth > 0f) {
                                val newOffset = (offsetX + delta).coerceIn(0f, sheetWidth)
                                offsetX = newOffset
                            }
                        },
                        onDragStopped = { velocity ->
                            if (sheetWidth == 0f) return@draggable

                            val shouldDismiss =
                                offsetX > sheetWidth * 0.3f || velocity > 1500f

                            if (shouldDismiss) {
                                offsetX = sheetWidth
                                onDismiss()
                            } else {
                                offsetX = 0f
                            }
                        }
                    )
                    .background(Color.White)
                    .align(Alignment.CenterEnd)
            ) {
                Column(
                    modifier = Modifier
                        .fillMaxSize()
                        .padding(16.dp)
                ) {

                    Text(
                        text = "Membros do grupo",
                        style = MaterialTheme.typography.titleLarge.copy(
                            fontWeight = FontWeight.Bold
                        )
                    )
                    Spacer(Modifier.height(8.dp))
                    HorizontalDivider()
                    Spacer(Modifier.height(8.dp))

                    if (memberships.isEmpty()) {
                        Box(
                            modifier = Modifier
                                .weight(1f)
                                .fillMaxWidth(),
                            contentAlignment = Alignment.Center
                        ) {
                            Text(
                                text = "Este grupo ainda não tem membros.",
                                style = MaterialTheme.typography.bodyMedium.copy(
                                    color = TextDark.copy(alpha = 0.7f)
                                )
                            )
                        }
                    } else {
                        LazyColumn(
                            modifier = Modifier
                                .weight(1f)
                                .fillMaxWidth()
                        ) {
                            items(memberships) { membership ->
                                MemberRow(membership)
                            }
                        }
                    }

                    HorizontalDivider()
                    Spacer(Modifier.height(12.dp))

                    Column(
                        modifier = Modifier.fillMaxWidth()
                    ) {
                        onInviteMember?.let {
                            Button(
                                onClick = it,
                                modifier = Modifier.fillMaxWidth(),
                                shape = RoundedCornerShape(20.dp),
                                colors = ButtonDefaults.buttonColors(
                                    containerColor = OrangePrimary
                                )
                            ) {
                                Text("Convidar membro")
                            }
                            Spacer(Modifier.height(8.dp))
                        }

                        onLeaveGroup?.let {
                            OutlinedButton(
                                onClick = it,
                                modifier = Modifier.fillMaxWidth(),
                                shape = RoundedCornerShape(20.dp)
                            ) {
                                Text("Sair do grupo")
                            }
                        }
                    }
                }
            }
        }
    }
}

@Composable
private fun MemberRow(membership: Membership) {
    val user = membership.user
    val displayName = user?.name ?: "Utilizador #${membership.id}"
    val email = user?.email ?: ""

    val roleLabel = when (membership.role.lowercase()) {
        "owner" -> "Owner"
        "admin" -> "Administrador"
        else -> "Membro"
    }

    Column(
        modifier = Modifier
            .fillMaxWidth()
            .padding(vertical = 8.dp)
    ) {
        Text(
            text = displayName,
            style = MaterialTheme.typography.bodyLarge.copy(
                fontWeight = FontWeight.Medium,
                color = TextDark
            )
        )
        if (email.isNotBlank()) {
            Text(
                text = email,
                style = MaterialTheme.typography.bodySmall.copy(
                    color = TextDark.copy(alpha = 0.6f)
                )
            )
        }
        Text(
            text = roleLabel,
            style = MaterialTheme.typography.bodySmall.copy(
                color = TextDark.copy(alpha = 0.6f)
            )
        )
    }
}

// -------------------- MY LISTS SCREEN --------------------

@OptIn(ExperimentalMaterial3Api::class, ExperimentalFoundationApi::class)
@Composable
fun GroupListScreen(
    navController: NavController,
    groupId: Int,
    groupName: String,
    userId: Int,
    listsViewModel: GroupListsViewModel = viewModel(),
    membersViewModel: GroupMembersViewModel = viewModel()
) {
    val listsUiState = listsViewModel.uiState
    val membersUiState = membersViewModel.uiState

    var showCreateDialog by remember { mutableStateOf(false) }
    var showMembersSheet by remember { mutableStateOf(false) }

    LaunchedEffect(groupId, userId) {
        listsViewModel.loadGroupLists(groupId, userId)
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
                            membersViewModel.loadMembers(groupId)
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
                                ShoppingListCard(list = list)
                            }
                        }
                    }
                }
            }

            GroupMembersSideSheet(
                visible = showMembersSheet,
                memberships = membersUiState.members,
                onDismiss = { showMembersSheet = false }
            )

            // 👉 AQUI: o popup agora chama o ViewModel para criar a lista
            if (showCreateDialog) {
                CreateListDialog(
                    onDismiss = { showCreateDialog = false },
                    onConfirm = { name, description ->
                        val descOrNull = description.trim().ifBlank { null }

                        listsViewModel.createList(
                            groupId = groupId,
                            userId = userId,
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

// -------------------- COMPONENTES AUXILIARES --------------------

@Composable
fun ShoppingListCard(list: ShoppingList) {
    Card(
        modifier = Modifier
            .fillMaxWidth()
            .aspectRatio(1.1f),
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
        groupName = "Grupo Exemplo",
        userId = 1
    )
}
