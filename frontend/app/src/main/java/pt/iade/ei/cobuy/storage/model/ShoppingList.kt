package pt.iade.ei.cobuy.storage.model

data class ShoppingList(
    val id: Int,
    val group: Group? = null,
    val title: String,
    val description: String? = null,
    val createdAt: String? = null
)
