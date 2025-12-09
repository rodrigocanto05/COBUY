package pt.iade.ei.cobuy.storage.model

data class ListItem(
    val id: Int,
    val qty: Double?,
    val done: Boolean = false,
    val updatedAt: String? = null,
    val item: Item,
    val unit: Unit
)

data class Item(
    val id: Int,
    val name: String
)

data class Unit(
    val id: Int,
    val name: String
)
