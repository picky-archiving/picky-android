package hackathon.picky.core.data.repo

import hackathon.picky.core.network.model.response.PolicyDetailData
import hackathon.picky.core.network.model.response.PolicyPageData

interface PolicyRepository {
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
    suspend fun getPolicyDetail(policyId: Long): Result<PolicyDetailData>
}