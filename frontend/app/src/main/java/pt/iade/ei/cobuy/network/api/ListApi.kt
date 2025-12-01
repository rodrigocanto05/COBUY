package pt.iade.ei.cobuy.network.api

import pt.iade.ei.cobuy.storage.model.ShoppingList
import retrofit2.Response
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import retrofit2.http.*


interface ListApi {

    // GET listas de um grupo
    @GET("api/groups/{groupId}/lists")
    suspend fun getGroupLists(
        @Path("groupId") groupId: Int,
        @Query("userId") userId: Int
    ): Response<List<ShoppingList>>

    // POST criar lista num grupo
    @POST("api/groups/{groupId}/lists")
    suspend fun createList(
        @Path("groupId") groupId: Int,
        @Query("userId") userId: Int,
        @Body body: CreateListRequest
    ): Response<ShoppingList>

    companion object {
        private const val BASE_URL = "http://10.0.2.2:8082/"   // 👉 usa o mesmo que já usas para o GroupApi

        val service: ListApi by lazy {
            Retrofit.Builder()
                .baseUrl(BASE_URL)
                .addConverterFactory(GsonConverterFactory.create())
                .build()
                .create(ListApi::class.java)
        }
    }
}
