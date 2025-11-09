package pt.iade.ei.cobuy.storage.model

data class SavedPlaces(
    val id: Int,
    val user: User? = null,
    val supermarket: SuperMarket? = null,
    val label: String? = null,
    val distance: Double? = null,
    val createdAt: String? = null
)
