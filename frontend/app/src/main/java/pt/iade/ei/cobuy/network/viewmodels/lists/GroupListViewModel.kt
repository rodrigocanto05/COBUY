package pt.iade.ei.cobuy.network.viewmodels.lists

import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.setValue
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import kotlinx.coroutines.launch
import pt.iade.ei.cobuy.network.api.lists.ShoppingListApi
import pt.iade.ei.cobuy.network.repository.ShoppingListRepository
import pt.iade.ei.cobuy.storage.model.ShoppingList

data class GroupListsUiState(
    val isLoading: Boolean = false,
    val lists: List<ShoppingList> = emptyList(),
    val error: String? = null
)

class GroupListsViewModel : ViewModel() {

    private val repository = ShoppingListRepository(ShoppingListApi.service)

    var uiState by mutableStateOf(GroupListsUiState())
        private set

    fun loadGroupLists(groupId: Int, userId: Int) {
        viewModelScope.launch {
            uiState = uiState.copy(isLoading = true, error = null)

            try {
                val lists = repository.getListsForGroup(groupId, userId)
                uiState = uiState.copy(
                    isLoading = false,
                    lists = lists,
                    error = null
                )
            } catch (e: Exception) {
                uiState = uiState.copy(
                    isLoading = false,
                    error = e.message ?: "Erro ao carregar listas"
                )
            }
        }
    }

    fun createList(
        groupId: Int,
        userId: Int,
        title: String,
        description: String?
    ) {
        viewModelScope.launch {
            uiState = uiState.copy(isLoading = true, error = null)

            try {
                val created = repository.createList(
                    groupId = groupId,
                    title = title,
                    userId = userId
                )

                uiState = uiState.copy(
                    isLoading = false,
                    lists = uiState.lists + created,
                    error = null
                )
            } catch (e: Exception) {
                uiState = uiState.copy(
                    isLoading = false,
                    error = e.message ?: "Erro ao criar lista"
                )
            }
        }
    }

    fun deleteList(
        listId: Int,
        userId: Int,
        onResult: (Boolean, String?) -> Unit
    ) {
        viewModelScope.launch {
            try {
                repository.deleteList(listId, userId)

                uiState = uiState.copy(
                    lists = uiState.lists.filterNot { it.id == listId }
                )

                onResult(true, null)
            } catch (e: Exception) {
                onResult(false, e.message ?: "Erro ao apagar lista")
            }
        }
    }
}
