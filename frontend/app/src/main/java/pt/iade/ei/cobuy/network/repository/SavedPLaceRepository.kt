package pt.iade.ei.cobuy.network.repository

import pt.iade.ei.cobuy.network.api.ApiClient
import pt.iade.ei.cobuy.network.api.SavePlaceRequest
import pt.iade.ei.cobuy.network.api.SavedPlacesApi
import pt.iade.ei.cobuy.storage.model.SavedPlace

class SavedPlacesRepository(
    private val api: SavedPlacesApi = ApiClient.savedPlacesApi
) {

    // 🔥 Buscar favoritos do backend
    suspend fun getSavedPlaces(): List<SavedPlace> {
        return api.getSavedPlaces().map { resp ->
            SavedPlace(
                id = resp.id,                          // ID do favorito
                name = resp.supermarket.name,          // Nome do supermercado
                lat = resp.supermarket.latitude,       // latitude do supermercado
                lng = resp.supermarket.longitude       // longitude do supermercado
            )
        }
    }

    suspend fun savePlace(supermarketId: Int): SavedPlace {
        val resp = api.savePlace(SavePlaceRequest(supermarketId))

        return SavedPlace(
            id = resp.id,
            name = resp.supermarket.name,
            lat = resp.supermarket.latitude,
            lng = resp.supermarket.longitude
        )
    }

    // 🔥 Remover favorito
    suspend fun deletePlace(id: Int) {
        api.deletePlace(id)
    }
}