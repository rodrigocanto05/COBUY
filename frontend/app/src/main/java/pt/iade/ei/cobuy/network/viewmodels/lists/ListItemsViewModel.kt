package pt.iade.ei.cobuy.network.viewmodels.lists

import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.setValue
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import kotlinx.coroutines.launch
import pt.iade.ei.cobuy.network.api.lists.ListItemsApi
import pt.iade.ei.cobuy.network.api.lists.ListItemCreateRequest
import pt.iade.ei.cobuy.network.api.lists.NetworkListItem
import pt.iade.ei.cobuy.network.viewmodels.SessionViewModel
import pt.iade.ei.cobuy.storage.model.ListItem
import java.math.BigDecimal

data class ListItemsUiState(
    val isLoading: Boolean = false,
    val items: List<ListItem> = emptyList(),
    val error: String? = null
)

class ListItemsViewModel : ViewModel() {

    private val api = ListItemsApi.service

    var uiState by mutableStateOf(ListItemsUiState())
        private set


    private fun NetworkListItem.toDomain(): ListItem {
        return ListItem(
            id = id,
            name = item.name,
            qty = qty,
            unit = unit.name,
            done = done,
            updatedAt = updatedAt
        )
    }

    fun loadItems(listId: Int) {
        viewModelScope.launch {
            uiState = uiState.copy(isLoading = true, error = null)

            try {
                val userId = SessionViewModel.currentUserId
                    ?: throw IllegalStateException("User não autenticado")

                val result = api.getItems(listId, userId)
                    .map { it.toDomain() }

                uiState = uiState.copy(
                    isLoading = false,
                    items = result,
                    error = null
                )
            } catch (e: Exception) {
                uiState = uiState.copy(
                    isLoading = false,
                    error = e.message ?: "Erro ao carregar itens"
                )
            }
        }
    }

    fun addItem(
        listId: Int,
        userId: Int,
        itemId: Int,
        qty: Double,
        unitId: Int,
        onResult: (Boolean, String?) -> Unit
    ) {
        viewModelScope.launch {
            try {
                val body = ListItemCreateRequest(
                    itemId = itemId,
                    qty = BigDecimal(qty),
                    unitId = unitId
                )

                val createdNetwork = api.addItem(
                    listId = listId,
                    userId = userId,
                    body = body
                )

                val created = createdNetwork.toDomain()

                uiState = uiState.copy(
                    items = uiState.items + created
                )

                onResult(true, null)
            } catch (e: Exception) {
                onResult(false, e.message ?: "Erro ao adicionar item")
            }
        }
    }
}
