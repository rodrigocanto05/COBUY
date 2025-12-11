package pt.iade.ei.cobuy.network.viewmodels.lists

import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.setValue
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import kotlinx.coroutines.launch
import pt.iade.ei.cobuy.network.api.lists.ListItemsApi
import pt.iade.ei.cobuy.network.requests.AddItemRequest
import pt.iade.ei.cobuy.network.viewmodels.SessionViewModel
import pt.iade.ei.cobuy.storage.model.ListItem

data class ListItemsUiState(
    val isLoading: Boolean = false,
    val items: List<ListItem> = emptyList(),
    val error: String? = null
)

class ListItemsViewModel : ViewModel() {

    private val api = ListItemsApi.service

    var uiState by mutableStateOf(ListItemsUiState())
        private set

    fun loadItems(listId: Int) {
        viewModelScope.launch {
            uiState = uiState.copy(isLoading = true, error = null)
            try {
                val userId = SessionViewModel.currentUserId
                    ?: throw IllegalStateException("Utilizador não autenticado")

                val result = api.getItems(listId, userId)

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
        name: String,
        qty: Double,
        unitId: Int,
        onResult: (Boolean, String?) -> Unit
    ) {
        viewModelScope.launch {
            try {
                val userId = SessionViewModel.currentUserId
                    ?: throw IllegalStateException("Utilizador não autenticado")

                val body = AddItemRequest(
                    name = name,
                    qty = qty,
                    unitId = unitId,
                    userId = userId
                )

                val created = api.addItem(listId, body)

                uiState = uiState.copy(
                    items = uiState.items + created
                )

                onResult(true, null)
            } catch (e: Exception) {
                onResult(false, e.message ?: "Erro ao adicionar item")
            }
        }
    }

    fun toggleDone(
        listId: Int,
        itemId: Int,
        onResult: (Boolean, String?) -> Unit
    ) {
        viewModelScope.launch {
            try {
                val userId = SessionViewModel.currentUserId
                    ?: throw IllegalStateException("Utilizador não autenticado")

                val updated = api.markAsDone(listId, itemId, userId)

                uiState = uiState.copy(
                    items = uiState.items.map {
                        if (it.id == updated.id) updated else it
                    }
                )

                onResult(true, null)
            } catch (e: Exception) {
                onResult(false, e.message ?: "Erro ao atualizar item")
            }
        }
    }

    fun deleteItem(
        listId: Int,
        itemId: Int,
        onResult: (Boolean, String?) -> Unit
    ) {
        viewModelScope.launch {
            val previousItems = uiState.items
            uiState = uiState.copy(
                items = previousItems.filterNot { it.id == itemId }
            )

            try {
                val userId = SessionViewModel.currentUserId
                    ?: throw IllegalStateException("Utilizador não autenticado")

                api.deleteItem(listId, itemId, userId)

                onResult(true, null)
            } catch (e: Exception) {
                uiState = uiState.copy(items = previousItems)
                onResult(false, e.message ?: "Erro ao remover item")
            }
        }
    }
}
