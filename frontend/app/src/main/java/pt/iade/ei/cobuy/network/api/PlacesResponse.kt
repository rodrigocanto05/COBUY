package pt.iade.ei.cobuy.network.api

data class PlacesResponse(
    val results: List<PlaceResult>
)

data class PlaceResult(
    val name: String?,
    val geometry: Geometry
)

data class Geometry(
    val location: Location
)

data class Location(
    val lat: Double,
    val lng: Double
)

data class Market(
    val name: String,
    val lat: Double,
    val lng: Double
)