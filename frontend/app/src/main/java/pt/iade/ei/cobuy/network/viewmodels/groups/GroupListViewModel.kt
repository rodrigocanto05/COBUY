package pt.iade.ei.cobuy.network.viewmodels.groups

import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.setValue
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import kotlinx.coroutines.launch
import pt.iade.ei.cobuy.network.api.GroupApi
import pt.iade.ei.cobuy.network.requests.CreateListRequest
import pt.iade.ei.cobuy.storage.model.ShoppingList

data class GroupListsUiState(
    val isLoading: Boolean = false,
    val lists: List<ShoppingList> = emptyList(),
    val error: String? = null
)

class GroupListsViewModel : ViewModel() {

    var uiState by mutableStateOf(GroupListsUiState())
        private set

    fun loadGroupLists(groupId: Int, userId: Int) {
        viewModelScope.launch {
            uiState = uiState.copy(isLoading = true, error = null)

            try {
                val response = GroupApi.service.getGroupLists(groupId, userId)

                if (response.isSuccessful) {
                    val body = response.body().orEmpty()
                    uiState = uiState.copy(
                        isLoading = false,
                        lists = body,
                        error = null
                    )
                } else {
                    uiState = uiState.copy(
                        isLoading = false,
                        error = "Erro HTTP: ${response.code()}"
                    )
                }
            } catch (e: Exception) {
                uiState = uiState.copy(
                    isLoading = false,
                    error = e.message ?: "Erro desconhecido"
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
                val body = CreateListRequest(
                    groupId = groupId,
                    title = title
                )

                val response = GroupApi.service.createList(
                    userId = userId,
                    body = body
                )

                if (response.isSuccessful) {
                    // Depois de criar, volta a carregar as listas para garantir sincronização
                    loadGroupLists(groupId, userId)
                } else {
                    uiState = uiState.copy(
                        isLoading = false,
                        error = "Erro HTTP: ${response.code()}"
                    )
                }
            } catch (e: Exception) {
                uiState = uiState.copy(
                    isLoading = false,
                    error = e.message ?: "Erro desconhecido"
                )
            }
        }
    }

    fun clearError() {
        uiState = uiState.copy(error = null)
    }
}
