package pt.iade.ei.cobuy.network.api.lists

import pt.iade.ei.cobuy.network.api.ApiClient
import pt.iade.ei.cobuy.storage.model.ShoppingList
import retrofit2.http.GET
import retrofit2.http.Path

interface UserListsService {

    @GET("api/lists/user/{userId}")
    suspend fun getUserLists(
        @Path("userId") userId: Int
    ): List<ShoppingList>
}

object UserListsApi {
    val service: UserListsService by lazy {
        ApiClient.backendRetrofit.create(UserListsService::class.java)
    }
}
