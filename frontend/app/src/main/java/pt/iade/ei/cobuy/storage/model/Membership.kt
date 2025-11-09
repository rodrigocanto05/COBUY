package pt.iade.ei.cobuy.storage.model


data class Membership(
    val id: Int,
    val user: User? = null,
    val group: Group? = null,
    val role: String = "member",
    val joinedAt: String? = null
)
