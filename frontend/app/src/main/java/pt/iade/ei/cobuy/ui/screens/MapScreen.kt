package pt.iade.ei.cobuy.ui.screens

import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.MyLocation
import androidx.compose.material3.*
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavController
import com.google.android.gms.maps.model.CameraPosition
import com.google.android.gms.maps.model.LatLng
import com.google.maps.android.compose.GoogleMap
import com.google.maps.android.compose.Marker
import com.google.maps.android.compose.MarkerState
import com.google.maps.android.compose.rememberCameraPositionState
import pt.iade.ei.cobuy.ui.components.buttons.PrimaryButton
import pt.iade.ei.cobuy.ui.components.topbar.CoBuyTopBar
import pt.iade.ei.cobuy.ui.components.bottombar.CoBuyBottomBar
import pt.iade.ei.cobuy.ui.navigation.NavPath
import pt.iade.ei.cobuy.ui.theme.OrangePrimary
import pt.iade.ei.cobuy.ui.theme.TextDark
import pt.iade.ei.cobuy.ui.theme.TextLight

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun MapScreen(navController: NavController) {
    Scaffold(
        topBar = { CoBuyTopBar("Supermercados Próximos", navController = navController) },


        bottomBar = { CoBuyBottomBar(navController) },

        floatingActionButton = {
            FloatingActionButton(
                onClick = { /* TODO: Atualizar localização */ },
                containerColor = OrangePrimary,
                shape = CircleShape
            ) {
                Icon(
                    imageVector = Icons.Default.MyLocation,
                    contentDescription = "Localizar",
                    tint = TextLight
                )
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
                text = "Aqui será exibido o mapa com os supermercados próximos",
                style = MaterialTheme.typography.bodyLarge.copy(
                    color = TextDark,
                    fontSize = 15.sp
                ),
                modifier = Modifier.padding(vertical = 20.dp)
            )

            val iade = LatLng(38.78167, -9.10239) // Coordenadas do IADE
            val cameraPositionState = rememberCameraPositionState {
                position = CameraPosition.fromLatLngZoom(iade, 15f)
            }
            GoogleMap(
                modifier = Modifier
                    .fillMaxWidth()
                    .weight(1f),
                cameraPositionState = cameraPositionState
            ) {
                Marker(
                    state = MarkerState(position = iade),
                    title = "IADE",
                    snippet = "Universidade Europeia"
                )
            }

            Spacer(modifier = Modifier.height(24.dp))

            PrimaryButton("Ver Locais Salvos") {
                navController.navigate(NavPath.SavedLocations.route)
            }

            Spacer(modifier = Modifier.height(16.dp))
        }
    }
}

@Preview(showBackground = true)
@Composable
fun MapScreenPreview() {
    MapScreen(navController = NavController(LocalContext.current))
}
