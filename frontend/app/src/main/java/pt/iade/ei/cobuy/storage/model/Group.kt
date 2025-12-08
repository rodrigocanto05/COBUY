package pt.iade.ei.cobuy.storage.model

import kotlinx.serialization.Serializable
import pt.iade.ei.cobuy.storage.model.User

@Serializable
data class Group(
    val id: Int? = null,
    val name: String,
    val code: String? = null,
    val createdAt: String? = null
)


