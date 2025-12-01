package pt.iade.ei.cobuy.network.viewmodels

import android.util.Log
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext
import pt.iade.ei.cobuy.network.api.GroupApi
import pt.iade.ei.cobuy.storage.model.Group
import pt.iade.ei.cobuy.storage.model.ShoppingList
import pt.iade.ei.cobuy.storage.model.UserGroup

class GroupViewModel : ViewModel() {

    // ----------------------------------------------------------
    // CREATE GROUP
    // ----------------------------------------------------------
    fun createGroup(
        userId: Int,
        groupName: String,
        callback: (Boolean, String?) -> Unit
    ) {
        viewModelScope.launch(Dispatchers.IO) {
            try {
                val group = Group(name = groupName)
                val response = GroupApi.service.createGroup(userId, group)

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

    // ----------------------------------------------------------
    // GET USER GROUPS
    // ----------------------------------------------------------
    fun getUserGroups(
        userId: Int,
        onResult: (List<UserGroup>?, String?) -> Unit
    ) {
        viewModelScope.launch {
            try {
                val response = GroupApi.service.getUserGroups(userId)

                if (response.isSuccessful) {
                    onResult(response.body(), null)
                } else {
                    onResult(null, "Erro ${response.code()}: ${response.message()}")
                }

            } catch (e: Exception) {
                onResult(null, e.localizedMessage ?: "Erro desconhecido")
            }
        }
    }

    // ----------------------------------------------------------
    // GET LISTS OF A GROUP
    // ----------------------------------------------------------
    fun getGroupLists(
        groupId: Int,
        userId: Int,
        onResult: (List<ShoppingList>?, String?) -> Unit
    ) {
        viewModelScope.launch(Dispatchers.IO) {
            try {
                val response = GroupApi.service.getGroupLists(groupId, userId)

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

    // ----------------------------------------------------------
    // JOIN GROUP
    // ----------------------------------------------------------
    fun joinGroup(
        code: String,
        userId: Int,
        callback: (Group?, String?) -> Unit
    ) {
        viewModelScope.launch {
            try {
                val response = GroupApi.service.joinGroup(code, userId)

                if (response.isSuccessful) {
                    callback(response.body(), null)
                } else {
                    callback(null, "Código inválido ou já estás no grupo")
                }

            } catch (e: Exception) {
                callback(null, e.message ?: "Erro desconhecido")
            }
        }
    }
}
