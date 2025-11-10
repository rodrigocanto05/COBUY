package pt.iade.ei.cobuy.ui.screens

import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.lifecycle.viewmodel.compose.viewModel
import androidx.navigation.NavController
import androidx.navigation.compose.rememberNavController
import pt.iade.ei.cobuy.network.viewmodels.GroupViewModel
import pt.iade.ei.cobuy.storage.model.UserGroup
import pt.iade.ei.cobuy.ui.components.topbar.CoBuyTopBar
import pt.iade.ei.cobuy.ui.theme.BackgroundLight
import pt.iade.ei.cobuy.ui.theme.OrangePrimary
import pt.iade.ei.cobuy.ui.theme.TextDark


@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun MyGroupsScreen(navController: NavController, userId: Int) {
    val viewModel: GroupViewModel = viewModel()
    var userGroups by remember { mutableStateOf<List<UserGroup>>(emptyList()) }
    var isLoading by remember { mutableStateOf(true) }
    var errorMessage by remember { mutableStateOf<String?>(null) }

    // Chamada ao backend
    LaunchedEffect(Unit) {
        viewModel.getUserGroups(userId) { result, error ->
            if (error != null) errorMessage = error
            else userGroups = result ?: emptyList()
            isLoading = false
        }
    }

    Scaffold(
        topBar = { CoBuyTopBar("Os Meus Grupos", navController) },
        containerColor = BackgroundLight
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

                userGroups.isEmpty() -> {
                    Box(Modifier.fillMaxSize(), Alignment.Center) {
                        Text(
                            text = "Ainda não estás em nenhum grupo.",
                            style = MaterialTheme.typography.bodyLarge.copy(
                                color = TextDark,
                                fontSize = 16.sp
                            )
                        )
                    }
                }

                else -> {
                    LazyColumn(
                        verticalArrangement = Arrangement.spacedBy(12.dp),
                        modifier = Modifier.fillMaxSize()
                    ) {
                        items(userGroups) { group ->
                            GroupCard(group)
                        }
                    }
                }
            }
        }
    }
}

@Composable
fun GroupCard(group: UserGroup) {
    Card(
        colors = CardDefaults.cardColors(containerColor = OrangePrimary.copy(alpha = 0.08f)),
        modifier = Modifier.fillMaxWidth()
    ) {
        Column(modifier = Modifier.padding(16.dp)) {
            Text(
                text = group.name,
                color = OrangePrimary,
                style = MaterialTheme.typography.titleMedium.copy(fontWeight = FontWeight.SemiBold)
            )
            Spacer(modifier = Modifier.height(6.dp))
            Text(
                text = "Cargo: ${group.role}",
                color = TextDark.copy(alpha = 0.7f),
                style = MaterialTheme.typography.bodySmall
            )
        }
    }
}

@Preview(showBackground = true)
@Composable
fun MyGroupsScreenPreview() {
    val nav = rememberNavController()
    MyGroupsScreen(
        navController = nav,
        userId = 1 // <- qualquer ID “fake” para o preview
    )
}

