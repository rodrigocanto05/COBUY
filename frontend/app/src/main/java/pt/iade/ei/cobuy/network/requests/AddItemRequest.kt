package pt.iade.ei.cobuy.network.requests



data class AddItemRequest(
    val name: String,
    val qty: Double?,
    val unitId: Int,
    val userId: Int
)


