package pt.iade.ei.cobuy.network.viewmodels

import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.setValue
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import kotlinx.coroutines.launch
import pt.iade.ei.cobuy.network.api.GroupApi
import pt.iade.ei.cobuy.storage.model.Membership

data class GroupMembersUiState(
    val isLoading: Boolean = false,
    val members: List<Membership> = emptyList(),
    val error: String? = null
)

class GroupMembersViewModel : ViewModel() {

    var uiState by mutableStateOf(GroupMembersUiState())
        private set

    fun loadMembers(groupId: Int) {
        viewModelScope.launch {
            uiState = uiState.copy(isLoading = true, error = null)

            try {
                val response = GroupApi.service.getGroupMembers(groupId)

                if (response.isSuccessful) {
                    val body = response.body().orEmpty()
                    uiState = uiState.copy(
                        isLoading = false,
                        members = body,
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

    fun clearError() {
        uiState = uiState.copy(error = null)
    }
}
