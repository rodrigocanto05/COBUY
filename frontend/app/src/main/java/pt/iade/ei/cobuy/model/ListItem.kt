package pt.iade.ei.cobuy.model

import kotlinx.serialization.Serializable

@Serializable
data class ListItem(
    val id: Int,
    val name: String,
    val quantity: Int,
    val done: Boolean
)
