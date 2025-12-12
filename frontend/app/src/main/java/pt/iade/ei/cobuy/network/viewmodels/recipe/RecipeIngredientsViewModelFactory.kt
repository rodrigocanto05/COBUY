package pt.iade.ei.cobuy.viewmodel.recipe

import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import pt.iade.ei.cobuy.network.api.recipe.RecipeApi

class RecipeIngredientsViewModelFactory(
    private val api: RecipeApi
) : ViewModelProvider.Factory {

    override fun <T : ViewModel> create(modelClass: Class<T>): T {
        if (modelClass.isAssignableFrom(RecipeIngredientsViewModel::class.java)) {
            @Suppress("UNCHECKED_CAST")
            return RecipeIngredientsViewModel(api) as T
        }
        throw IllegalArgumentException("Unknown ViewModel class")
    }
}
