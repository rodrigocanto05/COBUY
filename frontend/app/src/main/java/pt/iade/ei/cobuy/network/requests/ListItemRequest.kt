package pt.iade.ei.cobuy.network.requests

import pt.iade.ei.cobuy.storage.model.Item
import pt.iade.ei.cobuy.storage.model.ListItem
import pt.iade.ei.cobuy.storage.model.Unit as StorageUnit

data class NetworkListItem(
    val id: Int,
    val qty: Double?,
    val done: Boolean,
    val updatedAt: String?,
    val item: Item,
    val unit: StorageUnit
)

fun NetworkListItem.toDomain(): ListItem {
    return ListItem(
        id = id,
        qty = qty,
        done = done,
        updatedAt = updatedAt,
        item = item,
        unit = unit
    )
}
