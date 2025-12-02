package pt.iade.ei.cobuy.network.api

import pt.iade.ei.cobuy.storage.model.ResolveMarketRequest
import pt.iade.ei.cobuy.storage.model.Market
import retrofit2.http.Body
import retrofit2.http.POST

interface SupermarketApi {

    @POST("/supermarkets/resolve")
    suspend fun resolveMarket(
        @Body body: ResolveMarketRequest
    ): Market
}