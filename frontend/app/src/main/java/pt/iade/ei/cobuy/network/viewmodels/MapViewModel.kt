package pt.iade.ei.cobuy.network.viewmodels

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.update
import kotlinx.coroutines.launch
import pt.iade.ei.cobuy.network.repository.MapsRepository
import pt.iade.ei.cobuy.storage.model.Market

data class MapUiState(
    val isLoading: Boolean = false,
    val markets: List<Market> = emptyList(),
    val error: String? = null
)

class MapViewModel(
    private val repository: MapsRepository = MapsRepository()
) : ViewModel() {

    private val _uiState = MutableStateFlow(MapUiState())
    val uiState: StateFlow<MapUiState> = _uiState

    init {
        loadNearbySupermarkets()
    }

    fun loadNearbySupermarkets() {
        viewModelScope.launch {
            _uiState.update { it.copy(isLoading = true, error = null) }

            try {
                val markets = repository.getSupermarkets()

                val allowedKeywords = listOf(
                    "lidl",
                    "pingo",
                    "continente",
                    "auchan",
                    "mercadona",
                    "intermarch",
                    "supercor",
                    "spar",
                    "mini preço",
                    "minipreço"
                )

                val filtered = markets.filter { m ->
                    val name = m.name.lowercase()
                    allowedKeywords.any { key -> name.contains(key) }
                }

                _uiState.update {
                    it.copy(
                        isLoading = false,
                        markets = filtered
                    )
                }

            } catch (e: Exception) {
                _uiState.update {
                    it.copy(
                        isLoading = false,
                        error = e.message ?: "Erro ao carregar supermercados"
                    )
                }
            }
        }
    }
}