package pt.iade.ei.cobuy

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.material3.Surface
import androidx.compose.ui.Modifier
import androidx.compose.foundation.layout.fillMaxSize
import pt.iade.ei.cobuy.network.api.ApiClient
import pt.iade.ei.cobuy.ui.navigation.appNavigation
import pt.iade.ei.cobuy.ui.theme.COBUYTheme

class MainActivity : ComponentActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        ApiClient.initialize(this)

        setContent {
            COBUYTheme {
                Surface(modifier = Modifier.fillMaxSize()) {
                    appNavigation()
                }
            }
        }
    }
}