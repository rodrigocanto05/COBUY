package pt.iade.ei.cobuy.network.requests

import com.google.gson.annotations.SerializedName

data class CreateListRequest(
    @SerializedName("group_id") val groupId: Int,
    val title: String
)