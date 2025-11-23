package pt.iade.ei.cobuy.network.viewmodels

import android.util.Log
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.State
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext
import pt.iade.ei.cobuy.network.api.*
import pt.iade.ei.cobuy.storage.model.User
import pt.iade.ei.cobuy.storage.utils.TokenManager

class AuthViewModel(private val tokenManager: TokenManager) : ViewModel() {

    private val api = ApiClient.retrofit.create(AuthApi::class.java)

    // ---------------- USER STATE ----------------
    private val _currentUser = mutableStateOf<User?>(null)
    val currentUser: State<User?> = _currentUser

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

                        // Depois do login, buscar o perfil real
                        loadUser()

                        withContext(Dispatchers.Main) { onResult(true, null) }
                    } else {
                        withContext(Dispatchers.Main) { onResult(false, "Token vazio") }
                    }
                } else {
                    withContext(Dispatchers.Main) { onResult(false, "Erro HTTP ${res.code()}") }
                }

            } catch (e: Exception) {
                withContext(Dispatchers.Main) { onResult(false, e.message) }
            }
        }
    }

    // ---------------- REGISTO ----------------
    fun register(
        email: String,
        password: String,
        onResult: (Boolean, String?) -> Unit = { _, _ -> }
    ) {
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

                        // Buscar info real do user
                        loadUser()

                        withContext(Dispatchers.Main) { onResult(true, null) }
                    } else {
                        withContext(Dispatchers.Main) { onResult(false, "Token vazio") }
                    }

                } else {
                    withContext(Dispatchers.Main) { onResult(false, "Erro HTTP ${res.code()}") }
                }

            } catch (e: Exception) {
                withContext(Dispatchers.Main) { onResult(false, e.message) }
            }
        }
    }

    // ---------------- GET USER LOGADO ----------------
    fun loadUser() {
        viewModelScope.launch(Dispatchers.IO) {
            try {
                val token = tokenManager.getToken() ?: return@launch

                val res = api.getMe("Bearer $token")

                if (res.isSuccessful) {
                    _currentUser.value = res.body()
                    Log.d("API", "User carregado: ${res.body()}")
                } else {
                    Log.e("API", "Erro ao carregar user: ${res.code()}")
                }

            } catch (e: Exception) {
                Log.e("API", "Erro loadUser(): ${e.message}")
            }
        }
    }
}
