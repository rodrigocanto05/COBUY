package pt.iade.ei.cobuy.storage.model


data class RecipeIngredient(
    val id: Int,
    val recipe: Recipe? = null,
    val name: String,
    val qtyServing: Double,
    val unit: String? = null
)
