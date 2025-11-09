package pt.iade.ei.cobuy.model

import kotlinx.serialization.Serializable

@Serializable
data class Location(
    val id: Int,
    val name: String,
    val address: String,
    val latitude: Double,
    val longitude: Double
)
