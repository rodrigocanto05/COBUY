package pt.iade.ei.cobuy.storage.model

data class ResolveMarketRequest(
    val name: String,
    val lat: Double,
    val lng: Double
)