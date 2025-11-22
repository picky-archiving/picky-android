package hackathon.picky.core.network.model.response

import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable

/**
 * 정책 상세 정보 API 응답
 */
@Serializable
data class PolicyDetailResponse(
    @SerialName("data")
    val data: PolicyDetailData,
    @SerialName("message")
    val message: String,
    @SerialName("success")
    val success: Boolean,
    @SerialName("timestamp")
    val timestamp: String
)

/**
 * 정책 상세 정보
 */
@Serializable
data class PolicyDetailData(
    @SerialName("id")
    val id: Long, // 정책 고유 ID

    @SerialName("category")
    val category: String, // 정책 카테고리명 (예: "교육지원2")

    @SerialName("title")
    val title: String, // 정책 제목

    @SerialName("content")
    val content: String, // 정책 상세 설명

    @SerialName("url")
    val url: String, // 정책 세부 내용을 확인할 수 있는 정적 페이지 또는 외부 링크

    @SerialName("limitIncomeBracket")
    val limitIncomeBracket: Long?, // 신청 가능한 최대 소득분위 (예: 4 = 4분위 이하 지원 가능)

    @SerialName("imageUrl")
    val imageUrl: String, // 정책 이미지 경로 (정적 파일)

    @SerialName("host")
    val host: String, // 정책을 운영하는 기관명 (예: "교육부")

    @SerialName("qualifications")
    val qualifications: List<String>, // 정책 참여 자격 목록 (예: ["대학교 재학생", "졸업 예정자"])

    @SerialName("always")
    val always: Boolean, // 상시 정책 여부 (true: 기간 제한 없음, false: 기간 제한 있음)

    @SerialName("startDate")
    val startDate: String, // 정책 시작일 (형식: "2025-01-15")

    @SerialName("endDate")
    val endDate: String? = null, // 정책 종료일 (형식: "2025-05-31", 상시모집인 경우 null)

    @SerialName("viewCount")
    val viewCount: Long, // 해당 정책의 총 조회수

    @SerialName("bookmarked")
    val bookmarked: Boolean // 현재 사용자의 북마크 여부 (true: 북마크됨, false: 북마크 안 됨)
)