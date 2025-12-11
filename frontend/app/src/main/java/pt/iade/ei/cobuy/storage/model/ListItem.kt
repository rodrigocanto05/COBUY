package pt.iade.ei.cobuy.storage.model

data class ListItem(
    val id: Int,
    val qty: Double?,
    val done: Boolean,
    val updatedAt: String? = null,
    val item: Item,
    val unit: Unit,
    val list: ShoppingListReference? = null,
    val user: UserReference? = null
)

data class Item(
    val id: Int,
    val name: String
)

data class Unit(
    val id: Int,
    val name: String
)

data class ShoppingListReference(
    val id: Int
)

data class UserReference(
    val id: Int,
    val name: String? = null,
    val email: String? = null,
    val gender: String? = null,
    val createdAt: String? = null
)
