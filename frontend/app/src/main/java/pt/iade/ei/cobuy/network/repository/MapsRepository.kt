package pt.iade.ei.cobuy.network.repository

import pt.iade.ei.cobuy.network.api.ApiClient
import pt.iade.ei.cobuy.network.api.GoogleApi
import pt.iade.ei.cobuy.storage.model.Market

class MapsRepository(
    private val api: GoogleApi = ApiClient.googleApi
) {

    suspend fun getSupermarkets(): List<Market> {
        val response = api.getNearbySupermarkets(
            location = "38.78167,-9.10239",
            radius = 10000,
            type = "supermarket",
            key = GoogleApi.Companion.API_KEY
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