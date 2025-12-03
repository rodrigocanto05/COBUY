package pt.iade.ei.cobuy.storage.model

data class SavedPlaceResponse(
    val id: Int,
    val supermarket: SupermarketResponse
)

data class SupermarketResponse(
    val id: Int,
    val name: String,
    val latitude: Double,
    val longitude: Double
)