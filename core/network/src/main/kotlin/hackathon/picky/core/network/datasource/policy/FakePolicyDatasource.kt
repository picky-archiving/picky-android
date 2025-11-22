package hackathon.picky.core.network.datasource.policy

import hackathon.picky.core.network.model.ApiResponse
import hackathon.picky.core.network.model.response.PolicyDetailData
import hackathon.picky.core.network.model.response.PolicyDetailResponse
import javax.inject.Inject
import javax.inject.Singleton

@Singleton
class FakePolicyDatasource @Inject constructor() : PolicyDatasource {
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