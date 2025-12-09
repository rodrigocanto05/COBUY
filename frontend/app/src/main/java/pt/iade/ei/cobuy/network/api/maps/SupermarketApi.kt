package pt.iade.ei.cobuy.network.api.maps

import pt.iade.ei.cobuy.storage.model.ResolveMarketRequest
import pt.iade.ei.cobuy.storage.model.Market
import retrofit2.http.Body
import retrofit2.http.GET
import retrofit2.http.POST

interface SupermarketApi {

    @GET("/supermarkets")
    suspend fun getSupermarkets(): List<Market>

    @POST("/supermarkets/resolve")
    suspend fun resolveMarket(
        @Body body: ResolveMarketRequest
    ): Market
}
