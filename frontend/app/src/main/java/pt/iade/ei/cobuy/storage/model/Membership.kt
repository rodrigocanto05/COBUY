package pt.iade.ei.cobuy.storage.model


data class Membership(
    val id: Int,
    val name: String?,   // vem diretamente do backend
    val role: String,
    val email: String? = null   // se houver mais tarde
)

