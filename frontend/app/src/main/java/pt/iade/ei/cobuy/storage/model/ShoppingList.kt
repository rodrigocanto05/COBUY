package pt.iade.ei.cobuy.storage.model

data class ShoppingList(
    val id: Int,
    val group: Group? = null,
    val title: String,
    val createdAt: String? = null
)
