package pt.iade.ei.cobuy.network.viewmodels

import android.util.Log
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext
import pt.iade.ei.cobuy.network.api.*
import pt.iade.ei.cobuy.storage.utils.TokenManager

class AuthViewModel(private val tokenManager: TokenManager) : ViewModel() {

    private val api = ApiClient.retrofit.create(AuthApi::class.java)

    // ---------------- LOGIN ----------------
    fun login(email: String, password: String, onResult: (Boolean, String?) -> Unit = { _, _ -> }) {
        viewModelScope.launch(Dispatchers.IO) {
            try {
                val res = api.login(LoginRequest(email, password))
                if (res.isSuccessful) {
                    val token = res.body()?.token
                    if (!token.isNullOrEmpty()) {
                        tokenManager.saveToken(token)
                        Log.d("API", "Login OK — token guardado")
                        withContext(Dispatchers.Main) {
                            onResult(true, null)
                        }
                    } else {
                        withContext(Dispatchers.Main) {
                            onResult(false, "Token vazio")
                        }
                    }
                } else {
                    withContext(Dispatchers.Main) {
                        onResult(false, "HTTP ${res.code()} ${res.message()}")
                    }
                }
            } catch (e: Exception) {
                Log.e("API", "Erro no login: ${e.message}")
                withContext(Dispatchers.Main) {
                    onResult(false, e.message)
                }
            }
        }
    }

    // ---------------- REGISTO ----------------
    fun register(email: String, password: String, onResult: (Boolean, String?) -> Unit = { _, _ -> }) {
        viewModelScope.launch(Dispatchers.IO) {
            try {
                val res = api.register(
                    RegisterRequest(
                        name = email.substringBefore("@"),
                        email = email,
                        password = password
                    )
                )
                if (res.isSuccessful) {
                    val token = res.body()?.token
                    if (!token.isNullOrEmpty()) {
                        tokenManager.saveToken(token)
                        Log.d("API", "Registo OK — token guardado")
                        withContext(Dispatchers.Main) {
                            onResult(true, null)
                        }
                    } else {
                        withContext(Dispatchers.Main) {
                            onResult(false, "Token vazio")
                        }
                    }
                } else {
                    withContext(Dispatchers.Main) {
                        onResult(false, "HTTP ${res.code()} ${res.message()}")
                    }
                }
            } catch (e: Exception) {
                Log.e("API", "Erro no registo: ${e.message}")
                withContext(Dispatchers.Main) {
                    onResult(false, e.message)
                }
            }
        }
    }
}
