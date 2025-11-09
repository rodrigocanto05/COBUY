package pt.iade.ei.cobuy.storage.model

import kotlinx.serialization.Serializable

@Serializable

data class ListItem(
    val id: Int,
    val name: String,
    val qty: Double? = null,
    val unit: String? = null,
    val done: Boolean = false,
    val updatedAt: String? = null
)
