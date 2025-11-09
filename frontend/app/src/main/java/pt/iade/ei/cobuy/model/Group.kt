package pt.iade.ei.cobuy.model

import kotlinx.serialization.Serializable

@Serializable
data class Group(
    val id: Int,
    val name: String,
    val description: String
)
