package pt.iade.ei.cobuy.ui.screens

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Scaffold
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.lifecycle.viewmodel.compose.viewModel
import androidx.navigation.NavController
import androidx.navigation.compose.rememberNavController
import pt.iade.ei.cobuy.ui.components.cards.GroupCard
import pt.iade.ei.cobuy.ui.components.topbar.CoBuyTopBar
import pt.iade.ei.cobuy.ui.theme.BackgroundLight
import pt.iade.ei.cobuy.viewmodels.GroupListViewModel

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun GroupListScreen(navController: NavController, viewModel: GroupListViewModel = viewModel()) {
    // Fetch groups when the screen is first composed
    LaunchedEffect(Unit) {
        viewModel.fetchUserGroups(1) // Using a hardcoded user ID for now
    }

    val groups by viewModel.groups.collectAsState()

    Scaffold(
        topBar = {
            CoBuyTopBar(
                title = "Os Meus Grupos",
                navController = navController
            )
        },
        containerColor = BackgroundLight
    ) { padding ->
        LazyColumn(
            modifier = Modifier
                .fillMaxSize()
                .padding(padding)
                .padding(horizontal = 16.dp, vertical = 12.dp),
            verticalArrangement = Arrangement.spacedBy(14.dp)
        ) {
            items(groups) { group ->
                GroupCard(group = group, navController = navController)
            }
        }
    }
}


@Preview(showBackground = true)
@Composable
fun GroupListScreenPreview() {
    GroupListScreen(navController = rememberNavController())
}
