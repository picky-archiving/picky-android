package hackathon.picky.core.network.model.response

import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable

@Serializable
data class PolicyIncomeResponse(
    @SerialName("data")
    val data: List<PolicyItem> = emptyList(),

    @SerialName("message")
    val message: String? = null,

    @SerialName("success")
    val success: Boolean = false,

    @SerialName("timestamp")
    val timestamp: String? = null
)

@Serializable
data class PolicyItem(
    @SerialName("id")
    val id: Long,

    @SerialName("title")
    val title: String? = null,

    @SerialName("category")
    val category: String? = null,

    @SerialName("host")
    val host: String? = null,

    @SerialName("always")
    val always: Boolean = false,

    @SerialName("startDate")
    val startDate: String? = null,  // 상시 모집이면 null

    @SerialName("endDate")
    val endDate: String? = null,    // 상시 모집이면 null

    @SerialName("qualifications")
    val qualifications: String? = null, // 쉼표 기반 텍스트

    @SerialName("imageUrl")
    val imageUrl: String? = null,

    @SerialName("url")
    val url: String? = null,

    @SerialName("viewCount")
    val viewCount: Long? = null // null이면 0
)
