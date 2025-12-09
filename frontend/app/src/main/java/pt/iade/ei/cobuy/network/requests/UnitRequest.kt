package pt.iade.ei.cobuy.network.repositories

import pt.iade.ei.cobuy.network.api.UnitsApi
import pt.iade.ei.cobuy.storage.model.Unit as UnitModel

class UnitRepository(
    private val api: UnitsApi = UnitsApi
) {
    suspend fun getUnits(): List<UnitModel> {
        return api.service.getUnits()
    }
}
