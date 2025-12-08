package pt.iade.ei.cobuy.ui.screens.list

import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import androidx.compose.ui.tooling.preview.Preview
import androidx.navigation.NavController
import androidx.navigation.compose.rememberNavController
import pt.iade.ei.cobuy.storage.model.ListItem
import pt.iade.ei.cobuy.ui.components.cards.ShoppingItemCard
import pt.iade.ei.cobuy.ui.components.topbar.CoBuyTopBar
import pt.iade.ei.cobuy.ui.theme.BackgroundLight
import pt.iade.ei.cobuy.ui.theme.COBUYTheme

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun ListItemsScreen(
    navController: NavController,
    listId: Int,
    listName: String
) {
    // TODO: no futuro substituir por ViewModel que carrega os itens da API
    val itemsState = remember {
        mutableStateListOf(
            ListItem(id = 1, name = "Leite", qty = 1.0, unit = "L"),
            ListItem(id = 2, name = "Pão", qty = 6.0, unit = "uni"),
            ListItem(id = 3, name = "Ovos", qty = 12.0, unit = "uni")
        )
    }

    Scaffold(
        topBar = {
            CoBuyTopBar(
                title = listName,
                navController = navController
            )
        },
        containerColor = BackgroundLight
    ) { padding ->
        Column(
            modifier = Modifier
                .fillMaxSize()
                .padding(padding)
                .padding(horizontal = 24.dp, vertical = 16.dp)
        ) {
            if (itemsState.isEmpty()) {
                Box(
                    modifier = Modifier.fillMaxSize(),
                    contentAlignment = Alignment.Center
                ) {
                    Text(text = "Ainda não há itens nesta lista.")
                }
            } else {
                LazyColumn(
                    verticalArrangement = Arrangement.spacedBy(12.dp),
                    modifier = Modifier.fillMaxSize()
                ) {
                    items(itemsState, key = { it.id }) { item ->
                        ShoppingItemCard(
                            item = item,
                            onItemClicked = { updated ->
                                val index = itemsState.indexOfFirst { it.id == updated.id }
                                if (index != -1) {
                                    itemsState[index] = updated
                                }
                            }
                        )
                    }
                }
            }
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
