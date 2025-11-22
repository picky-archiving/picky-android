package hackathon.picky.core.network.model.request

import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable

/**
 * 소득분위 수정 요청
 */
@Serializable
data class UpdateIncomeBracketRequest(
    @SerialName("incomeBracket")
    val incomeBracket: Int
)