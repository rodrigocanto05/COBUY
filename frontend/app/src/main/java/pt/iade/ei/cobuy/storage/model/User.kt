package pt.iade.ei.cobuy.storage.model

data class User(
    val id: Int,
    val name: String,
    val email: String,
    val createdAt: String? = null
)
