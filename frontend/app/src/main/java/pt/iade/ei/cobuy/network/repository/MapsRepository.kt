package pt.iade.ei.cobuy.network.repository

import pt.iade.ei.cobuy.network.api.ApiClient
import pt.iade.ei.cobuy.network.api.GoogleApi
import pt.iade.ei.cobuy.network.api.SupermarketApi
import pt.iade.ei.cobuy.storage.model.ResolveMarketRequest
import pt.iade.ei.cobuy.storage.model.Market

class MapsRepository(
    private val googleApi: GoogleApi = ApiClient.googleApi,
    private val backendApi: SupermarketApi = ApiClient.supermarketApi
) {

    suspend fun getSupermarkets(): List<Market> {

        // 1️⃣ Buscar supermercados do Google
        val response = googleApi.getNearbySupermarkets(
            location = "38.78167,-9.10239",
            radius = 10000,
            type = "supermarket",
            apiKey = GoogleApi.API_KEY
        )

        // 2️⃣ Enviar cada mercado para o backend
        return response.results.map { result ->

            val req = ResolveMarketRequest(
                name = result.name ?: "Supermercado",
                lat = result.geometry.location.lat,
                lng = result.geometry.location.lng
            )

            // Chamar o backend corretamente com JSON
            val backendMarket = backendApi.resolveMarket(req)

            // Converter para Market utilizado no app
            Market(
                id = backendMarket.id,
                name = backendMarket.name,
                lat = backendMarket.lat,
                lng = backendMarket.lng
            )
        }
    }
}