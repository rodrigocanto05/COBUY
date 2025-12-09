package pt.iade.ei.cobuy.network.api

import pt.iade.ei.cobuy.network.api.ApiClient
import pt.iade.ei.cobuy.storage.model.Unit as UnitModel
import retrofit2.http.GET

interface UnitApi {
    @GET("units")
    suspend fun getUnits(): List<UnitModel>
}

object UnitsApi {
    val service: UnitApi =
        ApiClient.backendRetrofit.create(UnitApi::class.java)
}
