package pt.iade.ei.cobuy.storage.model


data class Recipe(
    val id: Int,
    val user: User? = null,
    val name: String,
    val serves: Int
)
