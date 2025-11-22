package hackathon.picky.core.network.model.response

import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable

/**
 * 전체 게시물 목록 조회 API 응답
 */
@Serializable
data class PolicyListResponse(
    @SerialName("data")
    val data: PolicyPageData,
    @SerialName("message")
    val message: String,
    @SerialName("success")
    val success: Boolean,
    @SerialName("timestamp")
    val timestamp: String
)

/**
 * 페이징된 정책 목록 데이터
 */
@Serializable
data class PolicyPageData(
    @SerialName("content")
    val content: List<PolicySummary>,
    @SerialName("empty")
    val empty: Boolean,
    @SerialName("first")
    val first: Boolean,
    @SerialName("last")
    val last: Boolean,
    @SerialName("number")
    val number: Int,
    @SerialName("numberOfElements")
    val numberOfElements: Int,
    @SerialName("size")
    val size: Int,
    @SerialName("totalElements")
    val totalElements: Long,
    @SerialName("totalPages")
    val totalPages: Int
)

/**
 * 정책 요약 정보
 */
@Serializable
data class PolicySummary(
    @SerialName("id")
    val id: Long,
    @SerialName("category")
    val category: String,
    @SerialName("title")
    val title: String,
    @SerialName("host")
    val host: String,
    @SerialName("imageUrl")
    val imageUrl: String,
    @SerialName("qualifications")
    val qualifications: String, // 쉼표로 구분된 문자열
    @SerialName("always")
    val always: Boolean,
    @SerialName("startDate")
    val startDate: String,
    @SerialName("endDate")
    val endDate: String?,
    @SerialName("url")
    val url: String,
    @SerialName("viewCount")
    val viewCount: Long?
)