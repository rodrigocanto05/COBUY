package pt.iade.ei.cobuy.api

import kotlinx.serialization.json.Json
import okhttp3.MediaType.Companion.toMediaType
import pt.iade.ei.cobuy.model.AuthRequest
import pt.iade.ei.cobuy.model.AuthResponse
import retrofit2.Retrofit
import com.jakewharton.retrofit2.converter.kotlinx.serialization.asConverterFactory
import pt.iade.ei.cobuy.model.Group
import pt.iade.ei.cobuy.model.ListItem
import pt.iade.ei.cobuy.model.Location
import retrofit2.http.Body
import retrofit2.http.GET
import retrofit2.http.POST
import retrofit2.http.Path


interface CoBuyAPIService {

    // Groups
    @GET("users/{userId}/groups")
    suspend fun getUserGroups(@Path("userId") userId: Int): List<Group>

    @GET("groups/{groupId}/items")
    suspend fun getGroupItems(@Path("groupId") groupId: Int): List<ListItem>

    // Users
    @POST("users/register")
    suspend fun register(@Body authRequest: AuthRequest): AuthResponse

    @POST("users/login")
    suspend fun login(@Body authRequest: AuthRequest): AuthResponse

    // Locations
    @GET("locations")
    suspend fun getSavedLocations(): List<Location>

    companion object {
        private const val BASE_URL = "http://10.0.2.2:8080/api/"

        fun getInstance(): CoBuyAPIService {
            val contentType = "application/json".toMediaType()
            val json = Json { ignoreUnknownKeys = true }
            return Retrofit.Builder()
                .baseUrl(BASE_URL)
                .addConverterFactory(json.asConverterFactory(contentType))
                .build()
                .create(CoBuyAPIService::class.java)
        }
    }
}
