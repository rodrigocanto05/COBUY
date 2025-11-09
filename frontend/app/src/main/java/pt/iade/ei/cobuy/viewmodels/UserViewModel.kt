package pt.iade.ei.cobuy.viewmodels

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.launch
import pt.iade.ei.cobuy.api.UserRepository
import pt.iade.ei.cobuy.model.AuthRequest
import pt.iade.ei.cobuy.model.AuthResponse

class UserViewModel(private val repository: UserRepository = UserRepository()) : ViewModel() {

    private val _authResponse = MutableStateFlow<AuthResponse?>(null)
    val authResponse: StateFlow<AuthResponse?> = _authResponse

    fun register(request: AuthRequest) {
        viewModelScope.launch {
            repository.register(request)
        }
    }

    fun login(request: AuthRequest) {
        viewModelScope.launch {
            repository.login(request)
        }
    }
}
