package hackathon.picky.core.network.datasource.policy

import hackathon.picky.core.network.model.ApiResponse
import hackathon.picky.core.network.model.response.PolicyDetailData
import hackathon.picky.core.network.model.response.PolicyDetailResponse
import hackathon.picky.core.network.model.response.PolicyListResponse
import hackathon.picky.core.network.model.response.PolicyPageData
import hackathon.picky.core.network.model.response.PolicySummary
import kotlinx.coroutines.delay
import java.time.LocalDateTime
import java.time.format.DateTimeFormatter
import javax.inject.Inject
import javax.inject.Singleton

@Singleton
class FakePolicyDatasource @Inject constructor() : PolicyDatasource {
    override suspend fun getPolicyList(page: Int, size: Int): ApiResponse<PolicyListResponse> {
        delay(500) // 네트워크 지연 시뮬레이션

        val fakePolicies = List(size) { index ->
            PolicySummary(
                id = (page * size + index + 1).toLong(),
                category = listOf("취업", "교육", "주거", "복지")[index % 4],
                title = "청년 정책 ${page * size + index + 1}",
                host = listOf("고용노동부", "교육부", "국토교통부", "보건복지부")[index % 4],
                imageUrl = "https://fastly.picsum.photos/id/765/536/354.jpg?hmac=KdMEeWclG6G7uEBwImE8VC-vX6j6B7b2NbtqQNF80R0",
                qualifications = "청년, 구직자",
                always = index % 3 == 0,
                startDate = "2025-01-01",
                endDate = if (index % 3 == 0) null else "2025-06-30",
                url = "https://www.naver.com",
                viewCount = (100..1000).random().toLong()
            )
        }

        val pageData = PolicyPageData(
            content = fakePolicies,
            empty = false,
            first = page == 0,
            last = page >= 1,
            number = page,
            numberOfElements = size,
            size = size,
            totalElements = 25,
            totalPages = 3
        )

        val response = PolicyListResponse(
            data = pageData,
            message = "전체 게시물 목록 조회 성공",
            success = true,
            timestamp = LocalDateTime.now().format(DateTimeFormatter.ISO_LOCAL_DATE_TIME)
        )

        return ApiResponse.Success(response)
    }

    override suspend fun getPolicyDetail(policyId: Long): ApiResponse<PolicyDetailResponse> {
        // 가짜 정책 데이터
        val fakeData = PolicyDetailData(
            id = policyId,
            category = "교육지원",
            title = "국가 장학금 II유형",
            content = "소득분위에 따라 등록금을 지원하는 장학금입니다. " +
                    "대학의 자체 노력과 연계하여 등록금 부담을 완화하고 교육의 질 제고를 도모합니다.",
            url = "https://www.naver.com",
            limitIncomeBracket = 4,
            imageUrl = "https://fastly.picsum.photos/id/765/536/354.jpg?hmac=KdMEeWclG6G7uEBwImE8VC-vX6j6B7b2NbtqQNF80R0",
            host = "교육부",
            qualifications = listOf(
                "대학교 재학생",
                "졸업 예정자",
                "직장인"
            ),
            always = false,
            startDate = "2025-01-15",
            endDate = "2025-05-31",
            viewCount = 2,
            bookmarked = false
        )

        val response = PolicyDetailResponse(
            data = fakeData,
            message = "success",
            success = true,
            timestamp = "2025-11-23T00:25:16.0298456"
        )

        return ApiResponse.Success(response)
    }
}