package pt.iade.ei.cobuy.network.api.lists

import pt.iade.ei.cobuy.network.api.ApiClient
import pt.iade.ei.cobuy.network.requests.AddItemRequest
import pt.iade.ei.cobuy.network.requests.NetworkListItem
import retrofit2.Response
import retrofit2.http.Body
import retrofit2.http.DELETE
import retrofit2.http.GET
import retrofit2.http.PATCH
import retrofit2.http.POST
import retrofit2.http.Path
import retrofit2.http.Query

interface ListItemsService {

    @GET("api/lists/{listId}/items")
    suspend fun getItems(
        @Path("listId") listId: Int,
        @Query("userId") userId: Int
    ): List<NetworkListItem>

    @POST("api/lists/{listId}/items")
    suspend fun addItem(
        @Path("listId") listId: Int,
        @Body body: AddItemRequest
    ): NetworkListItem

    @PATCH("api/lists/{listId}/items/{itemId}/done")
    suspend fun markAsDone(
        @Path("listId") listId: Int,
        @Path("itemId") itemId: Int,
        @Query("userId") userId: Int
    ): NetworkListItem

    @DELETE("api/lists/{listId}/items/{itemId}")
    suspend fun deleteItem(
        @Path("listId") listId: Int,
        @Path("itemId") itemId: Int,
        @Query("userId") userId: Int
    ): Response<Unit>
}

object ListItemsApi {
    val service: ListItemsService =
        ApiClient.backendRetrofit.create(ListItemsService::class.java)
}
