package pt.iade.ei.cobuy.network.api.auth

import pt.iade.ei.cobuy.storage.model.User
import retrofit2.Response
import retrofit2.http.Body
import retrofit2.http.GET
import retrofit2.http.Header
import retrofit2.http.PUT

interface ProfileApi {

    @GET("me")
    suspend fun getProfile(
        @Header("Authorization") token: String
    ): Response<User>

    @PUT("me")
    suspend fun updateProfile(
        @Header("Authorization") token: String,
        @Body body: UpdateProfileRequest
    ): Response<Unit>

    @PUT("me/email")
    suspend fun updateEmail(
        @Header("Authorization") token: String,
        @Body body: UpdateEmailRequest
    ): Response<Unit>

    @PUT("me/password")
    suspend fun updatePassword(
        @Header("Authorization") token: String,
        @Body body: UpdatePasswordRequest
    ): Response<Unit>

}

data class UpdateProfileRequest(
    val name: String?,
    val gender: String?
)
data class UpdateEmailRequest(val email: String)
data class UpdatePasswordRequest(
    val oldPassword: String,
    val newPassword: String
)


