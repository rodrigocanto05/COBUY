package pt.iade.ei.cobuy.network.api.lists

import pt.iade.ei.cobuy.network.api.ApiClient
import pt.iade.ei.cobuy.storage.model.ShoppingList
import retrofit2.Response
import retrofit2.http.Body
import retrofit2.http.DELETE
import retrofit2.http.GET
import retrofit2.http.POST
import retrofit2.http.Path
import retrofit2.http.Query

interface ShoppingListApi {

    @GET("api/lists/group/{groupId}")
    suspend fun getListsByGroup(
        @Path("groupId") groupId: Int,
        @Query("userId") userId: Int
    ): Response<List<ShoppingList>>

    @POST("api/lists")
    suspend fun createList(
        @Body body: CreateListBody,
        @Query("userId") userId: Int
    ): Response<ShoppingList>

    @GET("api/lists/user/{userId}")
    suspend fun getListsForUser(
        @Path("userId") userId: Int
    ): Response<List<ShoppingList>>



    @DELETE("api/lists/{listId}")
    suspend fun deleteList(
        @Path("listId") listId: Int,
        @Query("userId") userId: Int
    ): Response<Unit>

    data class CreateListBody(
        val title: String,
        val group_id: Int
    )

    companion object {
        val service: ShoppingListApi by lazy {
            ApiClient.backendRetrofit.create(ShoppingListApi::class.java)
        }
    }
}
