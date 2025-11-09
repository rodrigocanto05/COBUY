package pt.iade.ei.cobuy.api

import pt.iade.ei.cobuy.model.Location

class LocationRepository {
    private val apiService = CoBuyAPIService.getInstance()

    suspend fun getSavedLocations(): List<Location> {
        return apiService.getSavedLocations()
    }
}
