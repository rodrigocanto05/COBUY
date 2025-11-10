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
    fun getUserMemberships(userId: Int, callback: (List<Membership>?, String?) -> Unit) {
        viewModelScope.launch(Dispatchers.IO) {
            try {
                Log.d("GROUP", "🔄 A obter memberships para userId=$userId ...")
                val response: Response<List<Membership>> = GroupApi.service.getUserMemberships(userId)

                if (response.isSuccessful) {
                    val memberships = response.body()
                    Log.d("GROUP", "✅ Memberships obtidos: ${memberships?.size ?: 0}")
                    withContext(Dispatchers.Main) {
                        callback(memberships, null)
                    }
                } else {
                    val errorMsg = "Erro HTTP: ${response.code()}"
                    Log.e("GROUP", errorMsg)
                    withContext(Dispatchers.Main) {
                        callback(null, errorMsg)
                    }
                }
            } catch (e: Exception) {
                Log.e("GROUP", "❌ Erro ao obter memberships: ${e.message}", e)
                withContext(Dispatchers.Main) {
                    callback(null, e.message)
                }
            }
        }
    }
}
