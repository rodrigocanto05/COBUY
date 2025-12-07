package pt.iade.ei.cobuy.network.viewmodels.auth

import android.content.Context
import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import pt.iade.ei.cobuy.storage.utils.TokenManager

class AuthViewModelFactory(private val context: Context) : ViewModelProvider.Factory {
    @Suppress("UNCHECKED_CAST")
    override fun <T : ViewModel> create(modelClass: Class<T>): T {
        if (modelClass.isAssignableFrom(AuthViewModel::class.java)) {
            val tokenManager = TokenManager(context)
            return AuthViewModel(tokenManager) as T
        }
        throw IllegalArgumentException("Unknown ViewModel class")
    }
}