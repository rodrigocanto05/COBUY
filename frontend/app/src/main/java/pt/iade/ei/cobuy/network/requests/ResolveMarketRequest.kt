package pt.iade.ei.cobuy.network.requests

data class ResolveMarketRequest(
    val name: String,
    val lat: Double,
    val lng: Double
)