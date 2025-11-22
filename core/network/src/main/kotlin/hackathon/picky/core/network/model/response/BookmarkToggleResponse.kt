package hackathon.picky.core.network.model.response

import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable

/**
 * 북마크 토글 API 응답
 */
@Serializable
data class BookmarkToggleResponse(
    @SerialName("data")
    val data: BookmarkToggleData,
    @SerialName("message")
    val message: String,
    @SerialName("success")
    val success: Boolean,
    @SerialName("timestamp")
    val timestamp: String
)

/**
 * 북마크 토글 데이터
 */
@Serializable
data class BookmarkToggleData(
    @SerialName("bookmarked")
    val bookmarked: Boolean,
    @SerialName("policyId")
    val policyId: Long
)