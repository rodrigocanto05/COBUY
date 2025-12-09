package pt.iade.ei.cobuy.network.viewmodels.lists

import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.setValue
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import kotlinx.coroutines.launch
import pt.iade.ei.cobuy.network.repositories.UnitRepository
import pt.iade.ei.cobuy.storage.model.Unit as UnitModel

data class UnitsUiState(
    val isLoading: Boolean = false,
    val units: List<UnitModel> = emptyList(),
    val error: String? = null
)

class UnitViewModel(
    private val repository: UnitRepository = UnitRepository()
) : ViewModel() {

    var uiState by mutableStateOf(UnitsUiState())
        private set

    fun loadUnits() {
        viewModelScope.launch {
            uiState = uiState.copy(isLoading = true, error = null)
            try {
                val result = repository.getUnits()
                uiState = uiState.copy(
                    isLoading = false,
                    units = result,
                    error = null
                )
            } catch (e: Exception) {
                uiState = uiState.copy(
                    isLoading = false,
                    error = e.message ?: "Erro ao carregar unidades"
                )
            }
        }
    }
}
