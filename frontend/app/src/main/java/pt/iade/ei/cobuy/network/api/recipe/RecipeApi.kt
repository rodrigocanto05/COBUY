package pt.iade.ei.cobuy.network.api.recipe

import okhttp3.ResponseBody
import pt.iade.ei.cobuy.network.models.AddToListRequest
import pt.iade.ei.cobuy.network.models.RecipeIngredientDto
import pt.iade.ei.cobuy.storage.model.Recipe
import retrofit2.Response
import retrofit2.http.Body
import retrofit2.http.GET
import retrofit2.http.POST
import retrofit2.http.Path

interface RecipeApi {

    @GET("recipes")
    suspend fun getRecipes(): List<Recipe>

    @GET("recipes/{recipeId}/ingredients")
    suspend fun getRecipeIngredients(
        @Path("recipeId") recipeId: Int
    ): List<RecipeIngredientDto>

    @POST("recipes/{recipeId}/add-to-list/{listId}")
    suspend fun addRecipeIngredientsToList(
        @Path("recipeId") recipeId: Int,
        @Path("listId") listId: Int,
        @Body body: AddToListRequest
    ): Response<ResponseBody>
}


