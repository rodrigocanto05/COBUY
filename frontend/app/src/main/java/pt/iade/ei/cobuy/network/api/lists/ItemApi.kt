package pt.iade.ei.cobuy.network.api.lists

import pt.iade.ei.cobuy.network.api.ApiClient
import retrofit2.http.Body
import retrofit2.http.POST

interface ItemApi {


    data class CreateItemBody(
        val name: String,
        val unitId: Int
    )


    data class ItemDTO(
        val id: Int,
        val name: String,
        val unitId: Int,
        val unitName: String
    )

    @POST("items")
    suspend fun createItem(
        @Body body: CreateItemBody
    ): ItemDTO
}

object ItemsApi {
    val service: ItemApi = ApiClient.backendRetrofit.create(ItemApi::class.java)
}
