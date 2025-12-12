package pt.iade.ei.cobuy.network.api.recipe

import pt.iade.ei.cobuy.network.api.ApiClient

object RecipeApiProvider {
    val service: RecipeApi by lazy {
        ApiClient.backendRetrofit.create(RecipeApi::class.java)
    }
}
