package pt.iade.ei.cobuy.storage.model

import kotlinx.serialization.Serializable

@Serializable
data class Group(
    val id: Int? = null,
    val name: String,
    val createdAt: String? = null
)


