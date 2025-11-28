package pt.iade.ei.cobuy.network.api

import pt.iade.ei.cobuy.storage.model.Recipe
import retrofit2.Response
import retrofit2.http.GET
import retrofit2.http.Query

interface RecipeApi {

    @GET("recipes")
    suspend fun getAllRecipes(
        @Query("userId") userId: Int? = null
    ): Response<List<Recipe>>
}
