package pt.iade.ei.cobuy.network.api

import pt.iade.ei.cobuy.network.requests.CreateListRequest
import pt.iade.ei.cobuy.storage.model.ShoppingList
import retrofit2.Response
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import retrofit2.http.*


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
        private const val BASE_URL = "http://10.0.2.2:8082/"

        val service: ShoppingListApi by lazy {
            Retrofit.Builder()
                .baseUrl(BASE_URL)
                .addConverterFactory(GsonConverterFactory.create())
                .build()
                .create(ShoppingListApi::class.java)
        }
    }
}
