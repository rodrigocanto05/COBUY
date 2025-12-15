package pt.iade.ei.cobuy.viewmodel.recipe

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.launch
import pt.iade.ei.cobuy.network.api.recipe.RecipeApiProvider
import pt.iade.ei.cobuy.storage.model.Recipe

class RecipesViewModel : ViewModel() {

    private val api = RecipeApiProvider.service

    private val _recipes = MutableStateFlow<List<Recipe>>(emptyList())
    val recipe: StateFlow<List<Recipe>> = _recipes

    fun load() {
        viewModelScope.launch {
            runCatching { api.getRecipes() }
                .onSuccess { _recipes.value = it }
                .onFailure { _recipes.value = emptyList() }
        }
    }
}
