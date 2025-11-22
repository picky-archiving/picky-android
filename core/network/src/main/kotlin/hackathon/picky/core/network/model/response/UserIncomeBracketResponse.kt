package hackathon.picky.core.network.model.response

import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable

/**
 * 사용자 소득분위 조회 API 응답
 */
@Serializable
data class UserIncomeBracketResponse(
    @SerialName("data")
    val data: UserIncomeBracketData,
    @SerialName("message")
    val message: String,
    @SerialName("success")
    val success: Boolean,
    @SerialName("timestamp")
    val timestamp: String
)

/**
 * 사용자 소득분위 정보
 */
@Serializable
data class UserIncomeBracketData(
    @SerialName("incomeBracket")
    val incomeBracket: Int // 사용자의 소득분위 (1~10)
)