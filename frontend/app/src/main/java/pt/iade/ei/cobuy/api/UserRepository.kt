package pt.iade.ei.cobuy.api

import pt.iade.ei.cobuy.model.AuthRequest
import pt.iade.ei.cobuy.model.AuthResponse

class UserRepository {
    private val apiService = CoBuyAPIService.getInstance()

    suspend fun register(authRequest: AuthRequest): AuthResponse {
        return apiService.register(authRequest)
    }

    suspend fun login(authRequest: AuthRequest): AuthResponse {
        return apiService.login(authRequest)
    }
}
