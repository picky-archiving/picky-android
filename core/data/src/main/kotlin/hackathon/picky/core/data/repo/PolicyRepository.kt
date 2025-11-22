package hackathon.picky.core.data.repo

import hackathon.picky.core.data.model.policy.PolicyDetailEntity

interface PolicyRepository {
    /**
     * 정책 상세 정보 조회
     * @param policyId 정책 ID
     * @return Result<PolicyDetailData> 성공 시 정책 상세 정보, 실패 시 에러
     */
    suspend fun getPolicyDetail(policyId: Long): Result<PolicyDetailEntity>
}