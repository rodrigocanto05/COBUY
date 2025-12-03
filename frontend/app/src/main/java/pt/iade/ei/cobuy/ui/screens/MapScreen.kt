package pt.iade.ei.cobuy.ui.screens

import android.content.Intent
import android.net.Uri
import android.widget.Toast
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
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
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
import pt.iade.ei.cobuy.network.viewmodels.SaveResult
import pt.iade.ei.cobuy.network.viewmodels.SavedPlaceViewModel
import pt.iade.ei.cobuy.storage.model.Market
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
    val savedPlaces by savedPlaceViewModel.savedPlaces.collectAsState()

    val context = LocalContext.current

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

            // MAPA
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

                    Marker(
                        state = MarkerState(position = iade),
                        title = "IADE",
                        icon = BitmapDescriptorFactory.defaultMarker(BitmapDescriptorFactory.HUE_RED)
                    )

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

                // CARD FIXO
                selectedMarket?.let { market ->
                    Card(
                        modifier = Modifier
                            .align(Alignment.BottomCenter)
                            .padding(start = 16.dp, end = 16.dp, bottom = 140.dp)
                            .fillMaxWidth(),
                        shape = RoundedCornerShape(18.dp),
                        elevation = CardDefaults.cardElevation(10.dp)
                    ) {

                        Column(
                            modifier = Modifier
                                .fillMaxWidth()
                                .padding(20.dp),
                            horizontalAlignment = Alignment.CenterHorizontally
                        ) {

                            Text(
                                text = market.name,
                                fontSize = 20.sp,
                                fontWeight = FontWeight.Medium,
                                textAlign = TextAlign.Center
                            )

                            Spacer(Modifier.height(16.dp))

                            // BOTÃO GUARDAR (NOVO)
                            Button(
                                onClick = {
                                    savedPlaceViewModel.save(market.id) { result ->

                                        val message = when (result) {
                                            SaveResult.ADDED -> "Adicionado aos favoritos!"
                                            SaveResult.ALREADY_EXISTS -> "Este supermercado já está nos Locais Salvos."
                                            SaveResult.ERROR -> "Erro ao guardar supermercado."
                                        }

                                        Toast.makeText(context, message, Toast.LENGTH_SHORT).show()
                                    }

                                    selectedMarket = null
                                },
                                colors = ButtonDefaults.buttonColors(OrangePrimary),
                                modifier = Modifier
                                    .fillMaxWidth(0.7f)
                                    .height(50.dp),
                                shape = RoundedCornerShape(30.dp)
                            ) {
                                Text("Guardar nos Favoritos", color = Color.White)
                            }

                            Spacer(Modifier.height(12.dp))

                            // Ver rotas
                            Button(
                                onClick = {
                                    val uri = Uri.parse(
                                        "google.navigation:q=${market.lat},${market.lng}"
                                    )
                                    val intent = Intent(Intent.ACTION_VIEW, uri)
                                    intent.setPackage("com.google.android.apps.maps")
                                    context.startActivity(intent)
                                },
                                modifier = Modifier
                                    .fillMaxWidth(0.7f)
                                    .height(46.dp),
                                colors = ButtonDefaults.buttonColors(Color(0xFF2F80ED)),
                                shape = RoundedCornerShape(30.dp)
                            ) {
                                Text("Ver rotas", color = Color.White)
                            }

                            Spacer(Modifier.height(12.dp))

                            TextButton(onClick = { selectedMarket = null }) {
                                Text(
                                    "Cancelar",
                                    color = OrangePrimary,
                                    textAlign = TextAlign.Center
                                )
                            }
                        }
                    }
                }
            }

            Spacer(Modifier.height(20.dp))

            PrimaryButton(
                text = "Ver Locais Salvos",
                modifier = Modifier.fillMaxWidth(0.88f)
            ) {
                navController.navigate(NavPath.SavedLocations.route)
            }

            Spacer(Modifier.height(14.dp))
        }
    }
}