package pt.iade.ei.cobuy.network.models

data class RecipeIngredientDto(
    val id: Int,
    val ingredient: String,
    val unit: String,
    val qty: Double
)

data class AddToListRequest(
    val userId: Int,
    val ingredients: List<Int>
)
