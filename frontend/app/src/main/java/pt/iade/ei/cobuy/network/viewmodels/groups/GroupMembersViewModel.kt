package pt.iade.ei.cobuy.network.viewmodels.groups

import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.setValue
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import kotlinx.coroutines.launch
import pt.iade.ei.cobuy.network.api.GroupApi
import pt.iade.ei.cobuy.network.requests.LeaveGroupRequest
import pt.iade.ei.cobuy.network.viewmodels.SessionViewModel
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


    fun removeMember(
        groupId: Int,
        memberId: Int,
        onResult: (Boolean, String?) -> Unit
    ) {
        val requesterId = SessionViewModel.currentUserId
        if (requesterId == null) {
            onResult(false, "Utilizador não autenticado")
            return
        }

        viewModelScope.launch {
            try {
                val response =
                    GroupApi.service.removeMember(groupId, memberId, requesterId)
                if (response.isSuccessful) {
                    uiState = uiState.copy(
                        members = uiState.members.filterNot { it.id == memberId }
                    )
                    onResult(true, null)
                } else {
                    onResult(
                        false,
                        "Erro ${response.code()}: ${response.errorBody()?.string()}"
                    )
                }
            } catch (e: Exception) {
                onResult(false, e.message ?: "Erro desconhecido")
            }
        }
    }

    fun leaveGroup(
        groupId: Int,
        onResult: (Boolean, String?) -> Unit
    ) {
        val userId = SessionViewModel.currentUserId
        if (userId == null) {
            onResult(false, "Utilizador não autenticado")
            return
        }

        viewModelScope.launch {
            try {
                val body = LeaveGroupRequest(userId = userId, groupId = groupId)
                val response = GroupApi.service.leaveGroup(body)
                if (response.isSuccessful) {
                    onResult(true, null)
                } else {
                    onResult(
                        false,
                        "Erro ${response.code()}: ${response.errorBody()?.string()}"
                    )
                }
            } catch (e: Exception) {
                onResult(false, e.message ?: "Erro desconhecido")
            }
        }
    }

}
