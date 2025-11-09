package pt.iade.ei.cobuy.model

import kotlinx.serialization.Serializable

@Serializable
data class AuthRequest(
    val email: String,
    val pass: String
)
