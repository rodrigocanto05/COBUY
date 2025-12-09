package pt.iade.ei.cobuy.network.api.lists

data class NetworkListItem(
    val id: Int,
    val qty: Double? = null,
    val done: Boolean = false,
    val updatedAt: String? = null,
    val item: NetworkItem,
    val unit: NetworkUnit
)

data class NetworkItem(
    val id: Int,
    val name: String
)

data class NetworkUnit(
    val id: Int,
    val name: String
)
