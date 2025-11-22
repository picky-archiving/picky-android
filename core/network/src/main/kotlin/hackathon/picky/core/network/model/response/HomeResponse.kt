package hackathon.picky.core.network.model.response

import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable

/**
 * 메인 화면 조회 API 응답
 */
@Serializable
data class HomeResponse(
    @SerialName("data")
    val data: HomeData,
    @SerialName("message")
    val message: String,
    @SerialName("success")
    val success: Boolean,
    @SerialName("timestamp")
    val timestamp: String
)

/**
 * 메인 화면 데이터
 */
@Serializable
data class HomeData(
    @SerialName("categories")
    val categories: List<CategoryGroup>,
    @SerialName("incomePolicies")
    val incomePolicies: List<PolicyInfo>
)

/**
 * 카테고리별 정책 그룹
 */
@Serializable
data class CategoryGroup(
    @SerialName("category")
    val category: String, // 취업, 주거, 금융, 교육
    @SerialName("policies")
    val policies: List<PolicyInfo>
)

/**
 * 정책 정보
 */
@Serializable
data class PolicyInfo(
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
    val startDate: String?,
    @SerialName("endDate")
    val endDate: String?,
    @SerialName("url")
    val url: String,
    @SerialName("viewCount")
    val viewCount: Long?
)