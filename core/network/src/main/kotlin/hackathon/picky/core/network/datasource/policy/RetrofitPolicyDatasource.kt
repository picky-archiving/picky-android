package hackathon.picky.core.network.datasource.policy

import hackathon.picky.core.network.api.PolicyApi
import hackathon.picky.core.network.model.ApiResponse
import hackathon.picky.core.network.model.response.PolicyDetailResponse
import hackathon.picky.core.network.util.runRemote
import javax.inject.Inject
import javax.inject.Singleton

@Singleton
class RetrofitPolicyDatasource @Inject constructor(
    private val policyApi: PolicyApi
) : PolicyDatasource {
    override suspend fun getPolicyDetail(policyId: Long): ApiResponse<PolicyDetailResponse> {
        return runRemote { policyApi.getPolicyDetail(policyId) }
    }
}