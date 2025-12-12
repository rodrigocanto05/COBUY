package pt.iade.ei.cobuy.network.viewmodels.lists

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.launch
import pt.iade.ei.cobuy.network.api.lists.UserListsApi
import pt.iade.ei.cobuy.storage.model.ShoppingList

data class UserListsUiState(
    val isLoading: Boolean = false,
    val lists: List<ShoppingList> = emptyList(),
    val error: String? = null
)

class UserListsViewModel : ViewModel() {

    private val _uiState = MutableStateFlow(UserListsUiState())
    val uiState: StateFlow<UserListsUiState> = _uiState.asStateFlow()

    fun loadUserLists(userId: Int) {
        viewModelScope.launch {
            _uiState.value = _uiState.value.copy(isLoading = true, error = null)

            runCatching {
                UserListsApi.service.getUserLists(userId)
            }.onSuccess { lists ->
                _uiState.value = UserListsUiState(
                    isLoading = false,
                    lists = lists,
                    error = null
                )
            }.onFailure { e ->
                _uiState.value = UserListsUiState(
                    isLoading = false,
                    lists = emptyList(),
                    error = e.message ?: "Erro ao carregar listas"
                )
            }
        }
    }
}
