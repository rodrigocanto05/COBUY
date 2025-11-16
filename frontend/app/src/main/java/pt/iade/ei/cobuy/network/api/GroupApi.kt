package pt.iade.ei.cobuy.network.api

import pt.iade.ei.cobuy.storage.model.Group
import pt.iade.ei.cobuy.storage.model.UserGroup
import retrofit2.Response
import retrofit2.http.*

import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

interface GroupApi {

    @GET("groups")
    suspend fun getAllGroups(): Response<List<Group>>

    @GET("groups/{id}")
    suspend fun getGroupById(@Path("id") id: Int): Response<Group>

    // 👇 AGORA COM userId NA QUERY
    @POST("groups")
    suspend fun createGroup(
        @Query("userId") userId: Int,
        @Body group: Group
    ): Response<Group>

    @GET("users/{userId}/memberships")
    suspend fun getUserGroups(
        @Path("userId") userId: Int
    ): Response<List<UserGroup>>

    companion object {
        private const val BASE_URL = "http://10.0.2.2:8082/"

        val service: GroupApi by lazy {
            Retrofit.Builder()
                .baseUrl(BASE_URL)
                .addConverterFactory(GsonConverterFactory.create())
                .build()
                .create(GroupApi::class.java)
        }
    }
}
