package pt.iade.ei.cobuy.network.api.lists

import com.google.gson.annotations.SerializedName
import pt.iade.ei.cobuy.network.api.ApiClient
import retrofit2.http.Body
import retrofit2.http.GET
import retrofit2.http.POST
import retrofit2.http.Path
import retrofit2.http.Query
import java.math.BigDecimal

data class ListItemCreateRequest(
    @SerializedName("item_id") val itemId: Int,
    @SerializedName("qty") val qty: BigDecimal,
    @SerializedName("unit_id") val unitId: Int
)

interface ListItemsService {

    @GET("api/lists/{listId}/items")
    suspend fun getItems(
        @Path("listId") listId: Int,
        @Query("userId") userId: Int
    ): List<NetworkListItem>

    @POST("api/lists/{listId}/items")
    suspend fun addItem(
        @Path("listId") listId: Int,
        @Query("userId") userId: Int,
        @Body body: ListItemCreateRequest
    ): NetworkListItem
}

object ListItemsApi {
    val service: ListItemsService =
        ApiClient.backendRetrofit.create(ListItemsService::class.java)
}
