package hackathon.picky.core.network.datasource.policy

import hackathon.picky.core.network.api.PolicyApi
import hackathon.picky.core.network.model.ApiResponse
import hackathon.picky.core.network.model.response.HomeResponse
import hackathon.picky.core.network.model.response.PolicyDetailResponse
import hackathon.picky.core.network.model.response.PolicyListResponse
import hackathon.picky.core.network.util.runRemote
import javax.inject.Inject
import javax.inject.Singleton

@Singleton
class RetrofitPolicyDatasource @Inject constructor(
    private val policyApi: PolicyApi
) : PolicyDatasource {
    override suspend fun getHomeData(): ApiResponse<HomeResponse> {
        return runRemote { policyApi.getHomeData() }
    }

    override suspend fun getPolicyList(page: Int, size: Int): ApiResponse<PolicyListResponse> {
        return runRemote { policyApi.getPolicyList(page, size) }
    }

    override suspend fun getPolicyDetail(policyId: Long): ApiResponse<PolicyDetailResponse> {
        return runRemote { policyApi.getPolicyDetail(policyId) }
    }
}