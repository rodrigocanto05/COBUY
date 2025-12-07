package pt.iade.ei.cobuy.network.repository

import android.util.Log
import pt.iade.ei.cobuy.network.api.ApiClient
import pt.iade.ei.cobuy.network.api.SupermarketApi
import pt.iade.ei.cobuy.storage.model.Market

class MapsRepository(
    private val backendApi: SupermarketApi = ApiClient.supermarketApi
) {
    suspend fun getSupermarkets(): List<Market> {
        return try {
            val result = backendApi.getSupermarkets()
            Log.d("MapsRepository", "Supermercados recebidos: ${result.size}")
            result
        } catch (e: Exception) {
            Log.e("MapsRepository", "Erro ao ir buscar supermercados", e)
            throw e
        }
    }
}
