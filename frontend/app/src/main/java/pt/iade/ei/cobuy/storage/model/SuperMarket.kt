package pt.iade.ei.cobuy.storage.model


data class SuperMarket(
    val id: Int,
    val name: String,
    val rating: Double? = null,
    val distance: Double? = null
)
