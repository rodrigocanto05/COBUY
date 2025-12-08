package pt.iade.ei.cobuy.network.api

import pt.iade.ei.cobuy.network.requests.CreateListRequest
import pt.iade.ei.cobuy.network.requests.LeaveGroupRequest
import pt.iade.ei.cobuy.network.viewmodels.SessionViewModel
import pt.iade.ei.cobuy.storage.model.Group
import pt.iade.ei.cobuy.storage.model.Membership
import pt.iade.ei.cobuy.storage.model.ShoppingList
import pt.iade.ei.cobuy.storage.model.UserGroup
import retrofit2.Response
import retrofit2.http.*
import retrofit2.http.HTTP


interface GroupApi {

    @GET("api/groups")
    suspend fun getAllGroups(): Response<List<Group>>

    @GET("api/groups/{id}")
    suspend fun getGroupById(@Path("id") id: Int): Response<Group>


    @GET("api/groups/user/{userId}")
    suspend fun getUserGroups(
        @Path("userId") userId: Int
    ): Response<List<UserGroup>>


    @POST("api/groups")
    suspend fun createGroup(
        @Query("userId") userId: Int =
            SessionViewModel.currentUserId
                ?: error("User não definido em SessionManager"),
        @Body group: Group
    ): Response<Group>


    @POST("api/groups/join/{code}")
    suspend fun joinGroup(
        @Path("code") code: String,
        @Query("userId") userId: Int =
            SessionViewModel.currentUserId
                ?: error("User não definido em SessionManager")
    ): Response<Group>


    @GET("api/lists/group/{groupId}")
    suspend fun getGroupLists(
        @Path("groupId") groupId: Int,
        @Query("userId") userId: Int =
            SessionViewModel.currentUserId
                ?: error("User não definido em SessionManager")
    ): Response<List<ShoppingList>>


    @POST("api/lists")
    suspend fun createList(
        @Query("userId") userId: Int =
            SessionViewModel.currentUserId
                ?: error("User não definido em SessionManager"),
        @Body body: CreateListRequest
    ): Response<ShoppingList>


    @GET("api/groups/{groupId}/members")
    suspend fun getGroupMembers(
        @Path("groupId") groupId: Int
    ): Response<List<Membership>>


    @HTTP(
        method = "DELETE",
        path = "api/memberships/leave",
        hasBody = true
    )
    suspend fun leaveGroup(
        @Body body: LeaveGroupRequest
    ): Response<Void>


    @DELETE("api/memberships/{groupId}/remove/{userId}")
    suspend fun removeMember(
        @Path("groupId") groupId: Int,
        @Path("userId") userId: Int,
        @Query("requesterId")
        requesterId: Int = SessionViewModel.currentUserId
            ?: error("User não definido em SessionManager")
    ): retrofit2.Response<Void>


    companion object {
        val service: GroupApi by lazy {
            ApiClient.backendRetrofit.create(GroupApi::class.java)
        }
    }
}
