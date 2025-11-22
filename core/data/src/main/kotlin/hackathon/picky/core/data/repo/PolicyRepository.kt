package hackathon.picky.core.data.repo

import hackathon.picky.core.data.model.policy.HomeDataEntity
import hackathon.picky.core.network.model.response.HomeData
import hackathon.picky.core.network.model.response.PolicyDetailData
import hackathon.picky.core.network.model.response.PolicyPageData
import hackathon.picky.core.data.model.policy.PolicyDetailEntity
import hackathon.picky.core.data.model.policy.PolicyEntity
import hackathon.picky.core.network.model.ApiResponse
import hackathon.picky.core.network.model.response.PolicyListResponse

interface PolicyRepository {
    /**
     * 메인 화면 데이터 조회
     * @return Result<HomeData> 성공 시 홈 데이터, 실패 시 에러
     */
    suspend fun getHomeData(): Result<HomeDataEntity>

    /**
     * 전체 게시물 목록 조회
     * @param page 페이지 번호 (0-base)
     * @param size 페이지 크기
     * @return Result<PolicyPageData> 성공 시 페이징된 정책 목록, 실패 시 에러
     */
    suspend fun getPolicyList(page: Int, size: Int): Result<PolicyPageData>

    /**
     * 정책 상세 정보 조회
     * @param policyId 정책 ID
     * @return Result<PolicyDetailData> 성공 시 정책 상세 정보, 실패 시 에러
     */
    suspend fun getPolicyDetail(policyId: Long): Result<PolicyDetailEntity>

    /**
     * 소득분위별 게시물 조회(조회수 상위 3개)
     * @Header userId
     */
    suspend fun getPolicyIncomeList(): Result<List<PolicyEntity>>
}