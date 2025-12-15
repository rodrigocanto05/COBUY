package pt.iade.ei.cobuy.network.api.maps

import pt.iade.ei.cobuy.network.requests.SavedPlaceResponse
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