package pt.iade.ei.cobuy.ui.screens

import androidx.compose.animation.AnimatedVisibility
import androidx.compose.animation.core.animateFloatAsState
import androidx.compose.animation.core.tween
import androidx.compose.animation.fadeIn
import androidx.compose.animation.fadeOut
import androidx.compose.animation.slideInHorizontally
import androidx.compose.animation.slideOutHorizontally
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.gestures.Orientation
import androidx.compose.foundation.gestures.draggable
import androidx.compose.foundation.gestures.rememberDraggableState
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Logout
import androidx.compose.material.icons.filled.PersonRemove
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.onGloballyPositioned
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.IntOffset
import androidx.compose.ui.unit.dp
import kotlin.math.roundToInt
import pt.iade.ei.cobuy.storage.model.Membership
import pt.iade.ei.cobuy.ui.theme.COBUYTheme
import pt.iade.ei.cobuy.ui.theme.OrangePrimary
import pt.iade.ei.cobuy.ui.theme.TextDark

@Composable
fun MembersSideCard(
    visible: Boolean,
    memberships: List<Membership>,
    groupCode: String? = null,
    onDismiss: () -> Unit,
    onLeaveGroup: (() -> Unit)? = null,
    onInviteMember: (() -> Unit)? = null,
    onKickMember: ((Membership) -> Unit)? = null,
    currentUserId: Int? = null,
    isCurrentUserOwner: Boolean = false
) {
    var sheetWidth by remember { mutableStateOf(0f) }
    var offsetX by remember { mutableStateOf(0f) }

    val animatedOffsetX by animateFloatAsState(
        targetValue = offsetX,
        animationSpec = tween(durationMillis = 180),
        label = "membersSheetOffset"
    )

    LaunchedEffect(visible) {
        if (visible) {
            offsetX = 0f
        }
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
                                val newOffset = (offsetX + delta)
                                    .coerceIn(0f, sheetWidth)
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
                    Divider()
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
                                MemberRow(
                                    membership = membership,
                                    showKickButton = isCurrentUserOwner &&
                                            membership.id != currentUserId,
                                    onKickClicked = {
                                        onKickMember?.invoke(membership)
                                    }
                                )
                            }
                        }
                    }

                    Divider()
                    Spacer(Modifier.height(12.dp))

                    Column(
                        modifier = Modifier.fillMaxWidth(),
                        horizontalAlignment = Alignment.CenterHorizontally
                    ) {
                        if (!groupCode.isNullOrBlank()) {
                            Text(
                                text = "Código do grupo: $groupCode",
                                style = MaterialTheme.typography.bodyMedium.copy(
                                    color = TextDark.copy(alpha = 0.7f),
                                    fontWeight = FontWeight.Medium
                                ),
                                textAlign = TextAlign.Center
                            )
                            Spacer(Modifier.height(12.dp))
                        }

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
                                Icon(
                                    imageVector = Icons.Filled.Logout,
                                    contentDescription = "Sair do grupo"
                                )
                                Spacer(Modifier.width(8.dp))
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
private fun MemberRow(
    membership: Membership,
    showKickButton: Boolean = false,
    onKickClicked: (() -> Unit)? = null
) {
    val displayName = membership.name ?: "Utilizador #${membership.id}"

    val roleLabel = when (membership.role.lowercase()) {
        "owner" -> "Owner"
        "admin" -> "Administrador"
        else -> "Membro"
    }

    Row(
        modifier = Modifier
            .fillMaxWidth()
            .padding(vertical = 8.dp),
        horizontalArrangement = Arrangement.SpaceBetween,
        verticalAlignment = Alignment.CenterVertically
    ) {
        Column(
            modifier = Modifier.weight(1f)
        ) {
            Text(
                text = displayName,
                style = MaterialTheme.typography.bodyLarge.copy(
                    fontWeight = FontWeight.Medium,
                    color = TextDark
                )
            )
            Text(
                text = roleLabel,
                style = MaterialTheme.typography.bodySmall.copy(
                    color = TextDark.copy(alpha = 0.6f)
                )
            )
        }

        if (showKickButton) {
            TextButton(onClick = { onKickClicked?.invoke() }) {
                Icon(
                    imageVector = Icons.Filled.PersonRemove,
                    contentDescription = "Expulsar membro"
                )
                Spacer(Modifier.width(4.dp))
                Text("Expulsar membro")
            }
        }
    }
}
@Preview(showBackground = true, showSystemUi = true)
@Composable
fun MembersSideCardPreview() {
    val fakeMemberships = listOf(
        Membership(
            id = 1,
            name = "Tu (owner)",
            role = "owner",
            email = "tu@exemplo.com"
        ),
        Membership(
            id = 2,
            name = "João Silva",
            role = "admin",
            email = "joao@exemplo.com"
        ),
        Membership(
            id = 3,
            name = "Maria Santos",
            role = "member",
            email = "maria@exemplo.com"
        )
    )

    COBUYTheme {
        MembersSideCard(
            visible = true,
            memberships = fakeMemberships,
            groupCode = "AAAAA",
            onDismiss = {},
            onLeaveGroup = {},
            onInviteMember = {},
            onKickMember = { /* membership -> */ },
            currentUserId = 1,
            isCurrentUserOwner = true
        )
    }
}