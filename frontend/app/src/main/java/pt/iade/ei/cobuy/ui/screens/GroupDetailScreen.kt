package pt.iade.ei.cobuy.ui.screens

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.FloatingActionButton
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.lifecycle.viewmodel.compose.viewModel
import androidx.navigation.NavController
import androidx.navigation.compose.rememberNavController
import pt.iade.ei.cobuy.ui.components.cards.ShoppingItemCard
import pt.iade.ei.cobuy.ui.components.topbar.CoBuyTopBar
import pt.iade.ei.cobuy.ui.theme.BackgroundLight
import pt.iade.ei.cobuy.ui.theme.OrangePrimary
import pt.iade.ei.cobuy.ui.theme.TextLight
import pt.iade.ei.cobuy.viewmodels.GroupDetailViewModel

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun GroupDetailScreen(
    navController: NavController,
    groupId: String?,
    viewModel: GroupDetailViewModel = viewModel()
) {
    // Fetch group items when the screen is first composed
    LaunchedEffect(groupId) {
        groupId?.toIntOrNull()?.let {
            viewModel.fetchGroupItems(it)
        }
    }

    val items by viewModel.items.collectAsState()

    val groupName = "Grupo ${groupId ?: "Desconhecido"}"

    Scaffold(
        topBar = {
            CoBuyTopBar(
                title = groupName,
                navController = navController
            )
        },
        containerColor = BackgroundLight,
        floatingActionButton = {
            FloatingActionButton(
                onClick = { /* TODO: adicionar novo item */ },
                containerColor = OrangePrimary
            ) {
                Text(
                    "+",
                    color = TextLight,
                    fontSize = 22.sp,
                    fontWeight = FontWeight.Bold
                )
            }
        }
    ) { padding ->
        Column(
            modifier = Modifier
                .fillMaxSize()
                .padding(padding)
                .padding(horizontal = 16.dp, vertical = 10.dp)
        ) {
            Text(
                text = "Churrasco dos Amigos 🍖", // This could also be dynamic
                style = MaterialTheme.typography.headlineSmall.copy(
                    color = OrangePrimary,
                    fontSize = 19.sp,
                    fontWeight = FontWeight.SemiBold
                ),
                modifier = Modifier.padding(vertical = 10.dp)
            )

            LazyColumn(
                verticalArrangement = Arrangement.spacedBy(12.dp),
                modifier = Modifier.fillMaxSize()
            ) {
                items(items) { item ->
                    ShoppingItemCard(item = item) { /* No-op for now */ }
                }
            }
        }
    }
}

@Preview(showBackground = true)
@Composable
fun GroupDetailScreenPreview() {
    GroupDetailScreen(navController = rememberNavController(), groupId = "1")
}
