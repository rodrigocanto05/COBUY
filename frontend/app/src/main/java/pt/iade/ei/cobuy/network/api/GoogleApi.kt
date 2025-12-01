package pt.iade.ei.cobuy.network.api

import pt.iade.ei.cobuy.storage.model.PlacesResponse
import retrofit2.http.GET
import retrofit2.http.Query

interface GoogleApi {

    @GET("place/nearbysearch/json")
    suspend fun getNearbySupermarkets(
        @Query("location") location: String,
        @Query("radius") radius: Int,
        @Query("type") type: String = "supermarket",
        @Query("key") key: String
    ): PlacesResponse

    companion object {
        const val API_KEY = "AIzaSyCOKtJhEST4UIMJk2hqe3CiRR8_KN-CGqA"
    }
}