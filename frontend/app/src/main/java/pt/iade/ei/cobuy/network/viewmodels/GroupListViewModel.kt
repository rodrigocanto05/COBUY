package pt.iade.ei.cobuy.network.viewmodels

import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.setValue
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import kotlinx.coroutines.launch
import pt.iade.ei.cobuy.network.api.GroupApi
import pt.iade.ei.cobuy.network.api.CreateListRequest
import pt.iade.ei.cobuy.storage.model.ShoppingList

data class GroupListsUiState(
    val isLoading: Boolean = false,
    val lists: List<ShoppingList> = emptyList(),
    val error: String? = null
)

class GroupListsViewModel : ViewModel() {

    var uiState by mutableStateOf(GroupListsUiState())
        private set

    /**
     * Carrega as listas de um grupo específico para um dado user
     */
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
        description: String?, // este podes já remover
        callback: (Boolean, String?) -> Unit
    ) {
        viewModelScope.launch {
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
                    loadGroupLists(groupId, userId)
                    callback(true, null)
                } else {
                    val msg = "Erro HTTP: ${response.code()}"
                    callback(false, msg)
                }
            } catch (e: Exception) {
                callback(false, e.message ?: "Erro desconhecido")
            }
        }
    }


    fun clearError() {
        uiState = uiState.copy(error = null)
    }
}
