package pt.iade.ei.cobuy.ui.screens

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.draw.shadow
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.lifecycle.viewmodel.compose.viewModel
import androidx.navigation.NavController
import com.google.android.gms.maps.model.BitmapDescriptorFactory
import com.google.android.gms.maps.model.CameraPosition
import com.google.android.gms.maps.model.LatLng
import com.google.maps.android.compose.GoogleMap
import com.google.maps.android.compose.Marker
import com.google.maps.android.compose.MarkerState
import com.google.maps.android.compose.rememberCameraPositionState
import pt.iade.ei.cobuy.network.viewmodels.MapViewModel
import pt.iade.ei.cobuy.network.viewmodels.SavedPlaceViewModel
import pt.iade.ei.cobuy.storage.model.Market
import pt.iade.ei.cobuy.storage.model.SavedPlace
import pt.iade.ei.cobuy.ui.components.bottombar.CoBuyBottomBar
import pt.iade.ei.cobuy.ui.components.buttons.PrimaryButton
import pt.iade.ei.cobuy.ui.components.topbar.CoBuyTopBar
import pt.iade.ei.cobuy.ui.navigation.NavPath
import pt.iade.ei.cobuy.ui.theme.OrangePrimary

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun MapScreen(
    navController: NavController,
    viewModel: MapViewModel = viewModel(),
    savedPlaceViewModel: SavedPlaceViewModel = viewModel()
) {
    val uiState by viewModel.uiState.collectAsState()

    // ➤ Estado para quando o user clica num supermercado
    var selectedMarket by remember { mutableStateOf<Market?>(null) }

    val iade = LatLng(38.78167, -9.10239)
    val cameraPositionState = rememberCameraPositionState {
        position = CameraPosition.fromLatLngZoom(iade, 14f)
    }

    Scaffold(
        topBar = { CoBuyTopBar("Supermercados Próximos", navController) },
        bottomBar = { CoBuyBottomBar(navController) },
        containerColor = Color.White
    ) { padding ->

        Column(
            modifier = Modifier
                .fillMaxSize()
                .padding(padding)
                .padding(horizontal = 16.dp),
            horizontalAlignment = Alignment.CenterHorizontally
        ) {

            Spacer(Modifier.height(6.dp))

            // ---------- MAPA ----------
            Box(
                modifier = Modifier
                    .fillMaxWidth()
                    .weight(1f)
                    .clip(RoundedCornerShape(16.dp))
                    .shadow(4.dp, RoundedCornerShape(16.dp))
            ) {
                GoogleMap(
                    modifier = Modifier.matchParentSize(),
                    cameraPositionState = cameraPositionState
                ) {

                    // IADE marker
                    Marker(
                        state = MarkerState(position = iade),
                        title = "IADE",
                        snippet = "Universidade Europeia",
                        icon = BitmapDescriptorFactory.defaultMarker(BitmapDescriptorFactory.HUE_RED)
                    )

                    // Supermercados
                    uiState.markets.forEach { market ->
                        Marker(
                            state = MarkerState(LatLng(market.lat, market.lng)),
                            title = market.name,
                            onClick = {
                                selectedMarket = market
                                true
                            },
                            icon = BitmapDescriptorFactory.defaultMarker(BitmapDescriptorFactory.HUE_ORANGE)
                        )
                    }
                }

                if (uiState.isLoading) {
                    CircularProgressIndicator(
                        modifier = Modifier.align(Alignment.Center),
                        color = OrangePrimary
                    )
                }

                // ---------- CARD DE GUARDAR ----------
                selectedMarket?.let { market ->
                    Card(
                        modifier = Modifier
                            .align(Alignment.BottomCenter)
                            .padding(12.dp)
                            .fillMaxWidth(),
                        shape = RoundedCornerShape(16.dp),
                        elevation = CardDefaults.cardElevation(8.dp)
                    ) {
                        Column(
                            modifier = Modifier.padding(16.dp),
                            horizontalAlignment = Alignment.CenterHorizontally
                        ) {
                            Text(market.name, fontSize = 18.sp)

                            Spacer(Modifier.height(12.dp))

                            Button(
                                onClick = {
                                    savedPlaceViewModel.save(
                                        SavedPlace(
                                            name = market.name,
                                            lat = market.lat,
                                            lng = market.lng
                                        )
                                    )
                                    selectedMarket = null
                                },
                                colors = ButtonDefaults.buttonColors(OrangePrimary)
                            ) {
                                Text("Guardar nos Favoritos", color = Color.White)
                            }

                            Spacer(modifier = Modifier.height(8.dp))

                            TextButton(onClick = { selectedMarket = null }) {
                                Text("Cancelar")
                            }
                        }
                    }
                }
            }

            Spacer(Modifier.height(20.dp))

            // ---------- BOTÃO PARA VER FAVORITOS ----------
            PrimaryButton(
                text = "Ver Supermercados Favoritos",
                modifier = Modifier
                    .fillMaxWidth(0.88f)
            ) {
                navController.navigate(NavPath.SavedLocations.route)
            }

            Spacer(Modifier.height(14.dp))
        }
    }
}