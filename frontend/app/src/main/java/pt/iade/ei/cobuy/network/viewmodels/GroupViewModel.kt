package pt.iade.ei.cobuy.network.viewmodels

import android.util.Log
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext
import pt.iade.ei.cobuy.network.api.GroupApi
import pt.iade.ei.cobuy.storage.model.Group

class GroupViewModel : ViewModel() {

    fun createGroup(groupName: String, callback: (Boolean, String?) -> Unit) {
        viewModelScope.launch(Dispatchers.IO) {
            try {
                val group = Group(name = groupName)
                val response = GroupApi.service.createGroup(group)

                if (response.isSuccessful) {
                    Log.d("GROUP", "Grupo criado: ${response.body()}")
                    // 👇 volta para o main thread antes de chamar o callback
                    withContext(Dispatchers.Main) {
                        callback(true, null)
                    }
                } else {
                    Log.e("GROUP", "Erro HTTP: ${response.code()}")
                    withContext(Dispatchers.Main) {
                        callback(false, "Erro HTTP: ${response.code()}")
                    }
                }
            } catch (e: Exception) {
                Log.e("GROUP", "Erro: ${e.message}")
                withContext(Dispatchers.Main) {
                    callback(false, e.message)
                }
            }
        }
    }
}
