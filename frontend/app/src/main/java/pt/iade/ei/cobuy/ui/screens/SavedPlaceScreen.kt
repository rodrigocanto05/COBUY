package pt.iade.ei.cobuy.ui.screens

import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Delete
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavController
import androidx.lifecycle.viewmodel.compose.viewModel
import pt.iade.ei.cobuy.network.viewmodels.SavedPlaceViewModel

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun SavedLocationsScreen(navController: NavController) {

    val viewModel: SavedPlaceViewModel = viewModel()
    val savedPlaces by viewModel.savedPlaces.collectAsState()

    Scaffold(
        topBar = {
            CenterAlignedTopAppBar(
                title = { Text("Locais Salvos") }
            )
        }
    ) { padding ->

        if (savedPlaces.isEmpty()) {
            Box(
                modifier = Modifier
                    .fillMaxSize()
                    .padding(padding),
                contentAlignment = Alignment.Center
            ) {
                Text("Nenhum local salvo ainda!")
            }
        } else {
            LazyColumn(
                modifier = Modifier
                    .fillMaxSize()
                    .padding(padding)
            ) {
                items(savedPlaces) { place ->
                    Card(
                        modifier = Modifier
                            .fillMaxWidth()
                            .padding(12.dp),
                        elevation = CardDefaults.cardElevation(4.dp)
                    ) {
                        Row(
                            modifier = Modifier
                                .fillMaxWidth()
                                .padding(16.dp),
                            horizontalArrangement = Arrangement.SpaceBetween,
                            verticalAlignment = Alignment.CenterVertically
                        ) {
                            Column {
                                Text(place.name, fontSize = 18.sp)
                                Text("Lat: ${place.lat}  Lng: ${place.lng}", fontSize = 12.sp)
                            }

                            IconButton(
                                onClick = { viewModel.remove(place) }
                            ) {
                                Icon(
                                    Icons.Default.Delete,
                                    contentDescription = "Remover"
                                )
                            }
                        }
                    }
                }
            }
        }
    }
}