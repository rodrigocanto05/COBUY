package pt.iade.ei.cobuy.network.repository

import pt.iade.ei.cobuy.network.api.ApiClient
import pt.iade.ei.cobuy.network.api.maps.SavePlaceRequest
import pt.iade.ei.cobuy.network.api.maps.SavedPlacesApi
import pt.iade.ei.cobuy.storage.model.SavedPlace

class SavedPlacesRepository(
    private val api: SavedPlacesApi = ApiClient.savedPlacesApi
) {

    suspend fun getSavedPlaces(): List<SavedPlace> {
        return api.getSavedPlaces().map { resp ->
            SavedPlace(
                id = resp.id,
                name = resp.supermarket.name,
                lat = resp.supermarket.latitude,
                lng = resp.supermarket.longitude
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


    suspend fun deletePlace(id: Int) {
        api.deletePlace(id)
    }
}