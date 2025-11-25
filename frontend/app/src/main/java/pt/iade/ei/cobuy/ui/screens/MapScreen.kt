package pt.iade.ei.cobuy.ui.screens

import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.MyLocation
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.lifecycle.viewmodel.compose.viewModel
import androidx.navigation.NavController
import com.google.android.gms.maps.model.CameraPosition
import com.google.android.gms.maps.model.LatLng
import com.google.maps.android.compose.GoogleMap
import com.google.maps.android.compose.Marker
import com.google.maps.android.compose.MarkerState
import com.google.maps.android.compose.rememberCameraPositionState
import pt.iade.ei.cobuy.network.viewmodels.MapViewModel
import pt.iade.ei.cobuy.ui.components.bottombar.CoBuyBottomBar
import pt.iade.ei.cobuy.ui.components.buttons.PrimaryButton
import pt.iade.ei.cobuy.ui.components.topbar.CoBuyTopBar
import pt.iade.ei.cobuy.ui.navigation.NavPath
import pt.iade.ei.cobuy.ui.theme.OrangePrimary
import pt.iade.ei.cobuy.ui.theme.TextDark
import pt.iade.ei.cobuy.ui.theme.TextLight

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun MapScreen(
    navController: NavController,
    viewModel: MapViewModel = viewModel()
) {
    val uiState by viewModel.uiState.collectAsState()

    val iade = LatLng(38.78167, -9.10239)

    val cameraPositionState = rememberCameraPositionState {
        position = CameraPosition.fromLatLngZoom(iade, 14f)
    }

    Scaffold(
        topBar = { CoBuyTopBar("Supermercados Próximos", navController = navController) },
        bottomBar = { CoBuyBottomBar(navController) },

        floatingActionButton = {
            FloatingActionButton(
                onClick = {
                    cameraPositionState.position = CameraPosition.fromLatLngZoom(iade, 14f)
                },
                containerColor = OrangePrimary,
                shape = CircleShape
            ) {
                Icon(Icons.Default.MyLocation, "Localizar", tint = TextLight)
            }
        },

        containerColor = Color.White
    ) { padding ->
        Column(
            modifier = Modifier
                .fillMaxSize()
                .padding(padding)
                .padding(horizontal = 16.dp),
            horizontalAlignment = Alignment.CenterHorizontally
        ) {
            Text(
                text = "Aqui serão exibidos os supermercados próximos",
                style = MaterialTheme.typography.bodyLarge.copy(
                    color = TextDark,
                    fontSize = 15.sp
                ),
                modifier = Modifier.padding(vertical = 20.dp)
            )

            Box(
                modifier = Modifier
                    .fillMaxWidth()
                    .weight(1f)
            ) {
                GoogleMap(
                    modifier = Modifier.matchParentSize(),
                    cameraPositionState = cameraPositionState
                ) {
                    // IADE
                    Marker(
                        state = MarkerState(position = iade),
                        title = "IADE",
                        snippet = "Universidade Europeia"
                    )

                    // Supermercados (com marcador padrão do Google)
                    uiState.markets.forEach { market ->
                        Marker(
                            state = MarkerState(
                                position = LatLng(market.lat, market.lng)
                            ),
                            title = market.name,
                            snippet = "Supermercado"
                        )
                    }
                }

                if (uiState.isLoading) {
                    CircularProgressIndicator(
                        modifier = Modifier
                            .align(Alignment.Center)
                            .size(48.dp),
                        color = OrangePrimary
                    )
                }
            }

            uiState.error?.let {
                Text(
                    text = it,
                    color = Color.Red,
                    fontSize = 12.sp,
                    modifier = Modifier.padding(4.dp)
                )
            }

            PrimaryButton("Ver Locais Salvos") {
                navController.navigate(NavPath.SavedLocations.route)
            }

            Spacer(Modifier.height(16.dp))
        }
    }
}

@Preview(showBackground = true)
@Composable
fun MapScreenPreview() {
    MapScreen(navController = NavController(LocalContext.current))
}