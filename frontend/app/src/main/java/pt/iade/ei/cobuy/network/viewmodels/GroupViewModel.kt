package pt.iade.ei.cobuy.network.viewmodels

import android.util.Log
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext
import pt.iade.ei.cobuy.network.api.GroupApi
import pt.iade.ei.cobuy.storage.model.Group
import pt.iade.ei.cobuy.storage.model.Membership
import pt.iade.ei.cobuy.storage.model.UserGroup
import retrofit2.Response

class GroupViewModel : ViewModel() {

    // 🔸 Criar grupo
    fun createGroup(groupName: String, callback: (Boolean, String?) -> Unit) {
        viewModelScope.launch(Dispatchers.IO) {
            try {
                val group = Group(name = groupName)
                val response = GroupApi.service.createGroup(group)

                if (response.isSuccessful) {
                    Log.d("GROUP", "✅ Grupo criado com sucesso: ${response.body()}")
                    withContext(Dispatchers.Main) {
                        callback(true, null)
                    }
                } else {
                    val errorMsg = "Erro HTTP: ${response.code()}"
                    Log.e("GROUP", errorMsg)
                    withContext(Dispatchers.Main) {
                        callback(false, errorMsg)
                    }
                }
            } catch (e: Exception) {
                Log.e("GROUP", "❌ Erro ao criar grupo: ${e.message}", e)
                withContext(Dispatchers.Main) {
                    callback(false, e.message)
                }
            }
        }
    }

    // 🔹 Buscar todos os grupos em que o utilizador está inserido
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
}
