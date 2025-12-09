package pt.iade.ei.cobuy.network.api.lists

import com.google.gson.annotations.SerializedName
import java.math.BigDecimal

data class CreateListItemRequest(
    @SerializedName("item_id") val itemId: Int,
    @SerializedName("qty") val qty: BigDecimal,
    @SerializedName("unit_id") val unitId: Int
)
