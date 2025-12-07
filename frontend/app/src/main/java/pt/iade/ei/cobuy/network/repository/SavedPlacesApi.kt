package pt.iade.ei.cobuy.network.repository

import pt.iade.ei.cobuy.storage.model.SavedPlaceResponse
import retrofit2.http.*

interface SavedPlacesApi {

    @GET("/saved-places")
    suspend fun getSavedPlaces(): List<SavedPlaceResponse>

    @POST("/saved-places")
    suspend fun savePlace(@Body body: SavePlaceRequest): SavedPlaceResponse

    @DELETE("/saved-places/{id}")
    suspend fun deletePlace(@Path("id") id: Int)
}

data class SavePlaceRequest(
    val supermarketId: Int
)