package pt.iade.ei.cobuy.viewmodel.recipe

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.launch
import pt.iade.ei.cobuy.network.api.recipe.RecipeApi
import pt.iade.ei.cobuy.network.models.AddToListRequest
import pt.iade.ei.cobuy.network.models.RecipeIngredientDto

class RecipeIngredientsViewModel(
    private val api: RecipeApi
) : ViewModel() {

    private val _ingredients = MutableStateFlow<List<RecipeIngredientDto>>(emptyList())
    val ingredients: StateFlow<List<RecipeIngredientDto>> = _ingredients

    private val _error = MutableStateFlow<String?>(null)
    val error: StateFlow<String?> = _error

    fun load(recipeId: Int) {
        viewModelScope.launch {
            runCatching { api.getRecipeIngredients(recipeId) }
                .onSuccess {
                    _ingredients.value = it
                    _error.value = null
                }
                .onFailure {
                    _error.value = it.message ?: "Erro ao carregar ingredientes"
                }
        }
    }

    fun addOneIngredient(
        recipeId: Int,
        listId: Int,
        userId: Int,
        ingredientRgiId: Int,
        onSuccess: (String) -> Unit,
        onFailure: (String) -> Unit
    ) {
        viewModelScope.launch {
            val res = runCatching {
                api.addRecipeIngredientsToList(
                    recipeId = recipeId,
                    listId = listId,
                    body = AddToListRequest(userId = userId, ingredients = listOf(ingredientRgiId))
                )
            }.getOrElse {
                onFailure(it.message ?: "Erro de rede")
                return@launch
            }

            if (res.isSuccessful) {
                val msg = res.body()?.string()?.ifBlank { "Ingrediente adicionado" }
                    ?: "Ingrediente adicionado"
                onSuccess(msg)
            } else {
                val err = res.errorBody()?.string()
                onFailure("Erro ${res.code()}: ${err ?: "pedido falhou"}")
            }
        }
    }
}
