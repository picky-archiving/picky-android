package hackathon.picky.core.network.datasource.policy

import hackathon.picky.core.network.model.ApiResponse
import hackathon.picky.core.network.model.response.PolicyDetailResponse

interface PolicyDatasource {
    suspend fun getPolicyDetail(policyId: Long): ApiResponse<PolicyDetailResponse>
}