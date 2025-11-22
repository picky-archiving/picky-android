package hackathon.picky.core.network.model

import kotlinx.serialization.Serializable

@Serializable
data class ErrorResponse(
    val message: String,
    val code: Int
)