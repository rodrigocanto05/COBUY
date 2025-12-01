package pt.iade.ei.cobuy.network.api

import pt.iade.ei.cobuy.storage.model.Group
import pt.iade.ei.cobuy.storage.model.Membership
import pt.iade.ei.cobuy.storage.model.ShoppingList
import pt.iade.ei.cobuy.storage.model.UserGroup
import retrofit2.Response
import retrofit2.http.*

// CreateListRequest agora está no ficheiro CreateListRequest.kt

interface GroupApi {

    @GET("api/groups")
    suspend fun getAllGroups(): Response<List<Group>>

    @GET("api/groups/{id}")
    suspend fun getGroupById(@Path("id") id: Int): Response<Group>

    @POST("api/groups")
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

    // Buscar listas de um grupo
    @GET("api/lists/group/{groupId}")
    suspend fun getGroupLists(
        @Path("groupId") groupId: Int,
        @Query("userId") userId: Int
    ): Response<List<ShoppingList>>

    // Criar lista num grupo
    @POST("api/lists")
    suspend fun createList(
        @Query("userId") userId: Int,
        @Body body: CreateListRequest
    ): Response<ShoppingList>

    @GET("api/groups/{groupId}/members")
    suspend fun getGroupMembers(
        @Path("groupId") groupId: Int
    ): Response<List<Membership>>



    companion object {
        val service: GroupApi by lazy {
            ApiClient.backendRetrofit.create(GroupApi::class.java)
        }
    }
}
