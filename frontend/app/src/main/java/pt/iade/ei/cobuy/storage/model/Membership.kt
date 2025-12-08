package pt.iade.ei.cobuy.storage.model


data class Membership(
    val id: Int,
    val name: String?,
    val role: String,
    val email: String? = null
)

