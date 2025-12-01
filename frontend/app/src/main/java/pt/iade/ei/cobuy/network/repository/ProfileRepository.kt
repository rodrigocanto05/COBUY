package pt.iade.ei.cobuy.network.repository

import android.util.Log
import pt.iade.ei.cobuy.network.api.ProfileApi
import pt.iade.ei.cobuy.network.api.UpdateEmailRequest
import pt.iade.ei.cobuy.network.api.UpdatePasswordRequest
import pt.iade.ei.cobuy.network.api.UpdateProfileRequest
import pt.iade.ei.cobuy.storage.model.User
import pt.iade.ei.cobuy.storage.utils.TokenManager

class ProfileRepository(
    private val api: ProfileApi,
    private val tokenManager: TokenManager
) {

    suspend fun loadUser(): User? {
        return try {
            val token = tokenManager.getToken() ?: return null
            val res = api.getProfile("Bearer $token")

            if (res.isSuccessful) res.body()
            else null

        } catch (e: Exception) {
            Log.e("API", "Erro loadUser(): ${e.message}")
            null
        }
    }

    suspend fun updatePassword(oldPassword: String, newPassword: String): Boolean {
        return try {
            val token = tokenManager.getToken() ?: return false
            val req = UpdatePasswordRequest(oldPassword, newPassword)
            api.updatePassword("Bearer $token", req).isSuccessful
        } catch (e: Exception) {
            false
        }
    }


    suspend fun updateUser(name: String?, gender: String?): Boolean {
        return try {
            val token = tokenManager.getToken() ?: return false
            val request = UpdateProfileRequest(name, gender)
            api.updateProfile("Bearer $token", request).isSuccessful
        } catch (e: Exception) {
            false
        }
    }

    suspend fun updateEmail(email: String): Boolean {
        return try {
            val token = tokenManager.getToken() ?: return false
            val request = UpdateEmailRequest(email)
            api.updateEmail("Bearer $token", request).isSuccessful
        } catch (e: Exception) {
            false
        }
    }
}