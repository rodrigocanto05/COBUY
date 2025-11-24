package pt.iade.ei.cobuy.network.api

import pt.iade.ei.cobuy.storage.model.Group
import pt.iade.ei.cobuy.storage.model.UserGroup
import retrofit2.Response
import retrofit2.http.*
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

interface GroupApi {

    @GET("api/groups")
    suspend fun getAllGroups(): Response<List<Group>>

    @GET("api/groups/{id}")
    suspend fun getGroupById(@Path("id") id: Int): Response<Group>

    @POST("api/groups/create")
    suspend fun createGroup(
        @Query("userId") userId: Int,
        @Body group: Group
    ): Response<Group>

    @GET("api/users/{userId}/memberships")
    suspend fun getUserGroups(
        @Path("userId") userId: Int
    ): Response<List<UserGroup>>

    @POST("api/groups/join/{code}")
    suspend fun joinGroup(
        @Path("code") code: String,
        @Query("userId") userId: Int
    ): Response<Group>

    companion object {
        val service: GroupApi by lazy {
            ApiClient.retrofit.create(GroupApi::class.java)
        }
    }
}
