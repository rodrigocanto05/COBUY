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
import pt.iade.ei.cobuy.network.api.auth.AuthApi
import pt.iade.ei.cobuy.network.api.auth.LoginRequest
import pt.iade.ei.cobuy.network.api.auth.ProfileApi
import pt.iade.ei.cobuy.network.api.auth.RegisterRequest
import pt.iade.ei.cobuy.network.repository.ProfileRepository
import pt.iade.ei.cobuy.storage.model.User
import pt.iade.ei.cobuy.storage.utils.TokenManager

class AuthViewModel(private val tokenManager: TokenManager) : ViewModel() {

    private val authApi = ApiClient.backendRetrofit.create(AuthApi::class.java)
    private val profileApi = ApiClient.backendRetrofit.create(ProfileApi::class.java)

    private val repository = ProfileRepository(profileApi, tokenManager)

    private val _currentUser = mutableStateOf<User?>(null)
    val currentUser: State<User?> = _currentUser

    fun login(email: String, password: String, onResult: (Boolean, String?) -> Unit) {
        viewModelScope.launch(Dispatchers.IO) {

            val res = authApi.login(LoginRequest(email, password))

            if (res.isSuccessful) {
                val token = res.body()?.token
                if (!token.isNullOrEmpty()) {
                    tokenManager.saveToken(token)
                    loadUser()
                    withContext(Dispatchers.Main) { onResult(true, null) }
                } else {
                    withContext(Dispatchers.Main) { onResult(false, "Token vazio") }
                }
            } else {
                withContext(Dispatchers.Main) { onResult(false, "Credenciais inválidas") }
            }
        }
    }

    fun updatePassword(oldPass: String, newPass: String, onResult: (Boolean) -> Unit) {
        viewModelScope.launch(Dispatchers.IO) {

            val success = repository.updatePassword(oldPass, newPass)

            withContext(Dispatchers.Main) { onResult(success) }
        }
    }


    fun register(
        name: String,
        email: String,
        password: String,
        gender: String,
        onResult: (Boolean, String?) -> Unit
    )

    {
        viewModelScope.launch(Dispatchers.IO) {

            val res = authApi.register(
                RegisterRequest(
                    name = name,
                    email = email,
                    password = password,
                    gender = gender
                )
            )


            if (res.isSuccessful) {
                val token = res.body()?.token
                if (!token.isNullOrEmpty()) {
                    tokenManager.saveToken(token)

                    loadUser()

                    withContext(Dispatchers.Main) { onResult(true, null) }
                } else {
                    withContext(Dispatchers.Main) { onResult(false, "Token vazio") }
                }
            } else {
                withContext(Dispatchers.Main) { onResult(false, "Falha no registo") }
            }
        }
    }

    fun loadUser() {
        viewModelScope.launch(Dispatchers.IO) {
            val user = repository.loadUser()
            _currentUser.value = user
            SessionViewModel.currentUserId = user?.id

            Log.d("AUTH", "Loaded user: id=${user?.id}, name=${user?.name}")
        }
    }

    fun updateUser(name: String?, gender: String?, onResult: (Boolean) -> Unit) {
        viewModelScope.launch(Dispatchers.IO) {
            val success = repository.updateUser(name, gender)
            if (success) loadUser()

            withContext(Dispatchers.Main) { onResult(success) }
        }
    }

    fun updateEmail(email: String, onResult: (Boolean) -> Unit) {
        viewModelScope.launch(Dispatchers.IO) {
            val success = repository.updateEmail(email)
            if (success) loadUser()

            withContext(Dispatchers.Main) { onResult(success) }
        }
    }
}
