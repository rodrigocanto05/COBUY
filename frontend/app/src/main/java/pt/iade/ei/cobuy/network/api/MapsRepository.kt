package pt.iade.ei.cobuy.network.api

import pt.iade.ei.cobuy.network.api.ApiClient

class MapsRepository(
    private val api: GoogleApi = ApiClient.googleApi
) {

    suspend fun getSupermarkets(): List<Market> {
        val response = api.getNearbySupermarkets(
            location = "38.78167,-9.10239",
            radius = 10000,
            type = "supermarket",
            key = GoogleApi.API_KEY
        )
        return response.results.map {
            Market(
                name = it.name ?: "Supermercado",
                lat = it.geometry.location.lat,
                lng = it.geometry.location.lng
            )
        }
    }
}