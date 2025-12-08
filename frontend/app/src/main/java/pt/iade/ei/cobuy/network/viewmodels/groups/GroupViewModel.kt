package pt.iade.ei.cobuy.network.viewmodels.groups

import android.util.Log
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext
import pt.iade.ei.cobuy.network.api.GroupApi
import pt.iade.ei.cobuy.network.viewmodels.SessionViewModel
import pt.iade.ei.cobuy.storage.model.Group
import pt.iade.ei.cobuy.storage.model.ShoppingList
import pt.iade.ei.cobuy.storage.model.UserGroup

class GroupViewModel : ViewModel() {

    fun createGroup(
        userId: Int,
        groupName: String,
        callback: (Boolean, String?) -> Unit
    ) {
        viewModelScope.launch(Dispatchers.IO) {
            try {
                val group = Group(name = groupName)
                val response = GroupApi.Companion.service.createGroup(userId, group)

                if (response.isSuccessful) {
                    Log.d("GROUP", "✅ Grupo criado com sucesso: ${response.body()}")
                    withContext(Dispatchers.Main) { callback(true, null) }
                } else {
                    val msg = "Erro HTTP: ${response.code()}"
                    Log.e("GROUP", msg)
                    withContext(Dispatchers.Main) { callback(false, msg) }
                }

            } catch (e: Exception) {
                Log.e("GROUP", "❌ Erro ao criar grupo: ${e.message}")
                withContext(Dispatchers.Main) { callback(false, e.message) }
            }
        }
    }

    fun getUserGroups(
        onResult: (List<UserGroup>?, String?) -> Unit
    ) {
        val userId = SessionViewModel.currentUserId
        if (userId == null || userId <= 0) {
            onResult(null, "Utilizador não autenticado (id inválido)")
            return
        }

        viewModelScope.launch(Dispatchers.IO) {
            try {
                Log.d("GROUPS", "A buscar grupos do utilizador $userId")

                val response = GroupApi.Companion.service.getUserGroups(userId)

                if (response.isSuccessful) {
                    val body = response.body() ?: emptyList()
                    Log.d(
                        "GROUPS",
                        "Resposta OK (${response.code()}), recebidos ${body.size} grupos"
                    )

                    withContext(Dispatchers.Main) {
                        onResult(body, null)
                    }
                } else {
                    val errorBody = response.errorBody()?.string()
                    val msg = "Erro ${response.code()}: $errorBody"
                    Log.e("GROUPS", msg)

                    withContext(Dispatchers.Main) {
                        onResult(null, msg)
                    }
                }

            } catch (e: Exception) {
                Log.e("GROUPS", "Excepção ao buscar grupos", e)
                withContext(Dispatchers.Main) {
                    onResult(null, e.localizedMessage ?: "Erro desconhecido")
                }
            }
        }
    }

    fun getGroupLists(
        groupId: Int,
        userId: Int,
        onResult: (List<ShoppingList>?, String?) -> Unit
    ) {
        viewModelScope.launch(Dispatchers.IO) {
            try {
                val response = GroupApi.Companion.service.getGroupLists(groupId, userId)

                if (response.isSuccessful) {
                    val body = response.body() ?: emptyList()
                    withContext(Dispatchers.Main) {
                        onResult(body, null)
                    }
                } else {
                    withContext(Dispatchers.Main) {
                        onResult(
                            null,
                            "Erro ${response.code()}: ${response.message()}"
                        )
                    }
                }
            } catch (e: Exception) {
                withContext(Dispatchers.Main) {
                    onResult(null, e.localizedMessage ?: "Erro desconhecido")
                }
            }
        }
    }

    fun getGroupCode(
        groupId: Int,
        callback: (String?, String?) -> Unit
    ) {
        viewModelScope.launch(Dispatchers.IO) {
            try {
                val response = GroupApi.service.getGroupById(groupId)

                if (response.isSuccessful) {
                    val group = response.body()
                    val code = group?.code

                    withContext(Dispatchers.Main) {
                        callback(code, null)
                    }
                } else {
                    val msg = "Erro ${response.code()}: ${response.message()}"
                    withContext(Dispatchers.Main) {
                        callback(null, msg)
                    }
                }
            } catch (e: Exception) {
                withContext(Dispatchers.Main) {
                    callback(null, e.message ?: "Erro desconhecido")
                }
            }
        }
    }

    fun joinGroup(
        code: String,
        userId: Int,
        callback: (Group?, String?) -> Unit
    ) {
        viewModelScope.launch(Dispatchers.IO) {
            try {
                val response = GroupApi.Companion.service.joinGroup(code, userId)

                if (response.isSuccessful) {
                    withContext(Dispatchers.Main) {
                        callback(response.body(), null)
                    }
                } else {
                    withContext(Dispatchers.Main) {
                        callback(null, "Código inválido ou já estás no grupo")
                    }
                }

            } catch (e: Exception) {
                withContext(Dispatchers.Main) {
                    callback(null, e.message ?: "Erro desconhecido")
                }
            }
        }
    }
}