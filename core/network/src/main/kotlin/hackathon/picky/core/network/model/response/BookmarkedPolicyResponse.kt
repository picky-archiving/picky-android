package hackathon.picky.core.network.model.response

import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable

/**
 * 북마크된 정책 목록 조회 API 응답
 */
@Serializable
data class BookmarkedPolicyResponse(
    @SerialName("data")
    val data: BookmarkedPolicyPageData,
    @SerialName("message")
    val message: String,
    @SerialName("success")
    val success: Boolean,
    @SerialName("timestamp")
    val timestamp: String
)

/**
 * 북마크된 정책 페이징 데이터
 */
@Serializable
data class BookmarkedPolicyPageData(
    @SerialName("page")
    val page: Int,
    @SerialName("content")
    val content: List<BookmarkedPolicy>,
    @SerialName("size")
    val size: Int,
    @SerialName("totalElements")
    val totalElements: Int,
    @SerialName("totalPages")
    val totalPages: Int
)

/**
 * 북마크된 정책 정보
 */
@Serializable
data class BookmarkedPolicy(
    @SerialName("id")
    val id: Long,
    @SerialName("title")
    val title: String,
    @SerialName("category")
    val category: String?,
    @SerialName("host")
    val host: String?,
    @SerialName("always")
    val always: Boolean,
    @SerialName("bookmarked")
    val bookmarked: Boolean,
    @SerialName("StartDate")
    val startDate: String?,
    @SerialName("EndDate")
    val endDate: String?,
    @SerialName("dDay")
    val dDay: Int?,
    @SerialName("imageUrl")
    val imageUrl: String?,
    @SerialName("viewCount")
    val viewCount: Long?
)