package pt.iade.ei.cobuy.ui.screens.map

import android.annotation.SuppressLint
import android.widget.Toast
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.ArrowBack
import androidx.compose.material.icons.filled.Delete
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.text.style.TextOverflow
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavController
import androidx.navigation.compose.rememberNavController
import pt.iade.ei.cobuy.network.viewmodels.maps.SavedPlaceViewModel
import pt.iade.ei.cobuy.ui.theme.COBUYTheme

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun SavedLocationsScreen(
    navController: NavController,
    savedPlaceViewModel: SavedPlaceViewModel
) {

    val savedPlaces by savedPlaceViewModel.savedPlaces.collectAsState()
    val context = LocalContext.current

    Scaffold(
        topBar = {
            CenterAlignedTopAppBar(
                title = { Text("Locais Salvos", fontSize = 20.sp) },
                navigationIcon = {
                    IconButton(onClick = { navController.popBackStack() }) {
                        Icon(
                            imageVector = Icons.Default.ArrowBack,
                            contentDescription = "Voltar atrás",
                            tint = Color(0xFFFF9800) // mesma cor do MapScreen
                        )
                    }
                },
                colors = TopAppBarDefaults.centerAlignedTopAppBarColors(
                    containerColor = Color.White
                )
            )
        },
        containerColor = Color.White
    ) { padding ->

        if (savedPlaces.isEmpty()) {
            Box(
                modifier = Modifier
                    .fillMaxSize()
                    .padding(padding),
                contentAlignment = Alignment.Center
            ) {
                Text("Nenhum local salvo ainda!", fontSize = 16.sp)
            }
        } else {

            LazyColumn(
                modifier = Modifier
                    .fillMaxSize()
                    .padding(padding)
                    .padding(horizontal = 16.dp)
            ) {
                items(savedPlaces) { place ->

                    Card(
                        modifier = Modifier
                            .fillMaxWidth()
                            .padding(vertical = 8.dp),
                        shape = RoundedCornerShape(18.dp),
                        elevation = CardDefaults.cardElevation(4.dp),
                        colors = CardDefaults.cardColors(
                            containerColor = Color(0xFFF0ECF6)
                        )
                    ) {

                        Row(
                            modifier = Modifier
                                .fillMaxWidth()
                                .padding(18.dp),
                            verticalAlignment = Alignment.CenterVertically,
                            horizontalArrangement = Arrangement.SpaceBetween
                        ) {

                            Text(
                                text = place.name,
                                fontSize = 18.sp,
                                maxLines = 1,
                                overflow = TextOverflow.Ellipsis,
                                modifier = Modifier.weight(1f)
                            )

                            IconButton(
                                onClick = {
                                    savedPlaceViewModel.remove(place.id)
                                    Toast.makeText(
                                        context,
                                        "Removido dos favoritos!",
                                        Toast.LENGTH_SHORT
                                    ).show()
                                }
                            ) {
                                Icon(
                                    imageVector = Icons.Default.Delete,
                                    contentDescription = "Remover",
                                    tint = Color(0xFF333333)
                                )
                            }
                        }
                    }
                }
            }
        }
    }
}
@SuppressLint("ViewModelConstructorInComposable")
@Preview(showBackground = true, showSystemUi = true)
@Composable
fun SavedLocationsScreenPreview() {
    val navController = rememberNavController()
    val fakeViewModel = SavedPlaceViewModel()

    COBUYTheme {
        SavedLocationsScreen(
            navController = navController,
            savedPlaceViewModel = fakeViewModel
        )
    }
}


