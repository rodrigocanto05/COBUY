package pt.iade.ei.cobuy.viewmodel

import androidx.compose.runtime.mutableStateListOf
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import kotlinx.coroutines.launch
import pt.iade.ei.cobuy.network.api.RecipeApiClient
import pt.iade.ei.cobuy.storage.model.Recipe

class RecipesViewModel : ViewModel() {

    private val api = RecipeApiClient.api

    var recipes = mutableStateListOf<Recipe>()
        private set

    fun loadRecipes() {
        viewModelScope.launch {
            val response = api.getAllRecipes()
            if (response.isSuccessful) {
                recipes.clear()
                recipes.addAll(response.body() ?: emptyList())
            }
        }
    }
}
