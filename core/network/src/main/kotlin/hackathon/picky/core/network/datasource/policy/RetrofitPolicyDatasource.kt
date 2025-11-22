package hackathon.picky.core.network.datasource.policy

import hackathon.picky.core.network.api.PolicyApi
import hackathon.picky.core.network.model.ApiResponse
import hackathon.picky.core.network.model.response.BookmarkedPolicyResponse
import hackathon.picky.core.network.model.response.BookmarkToggleResponse
import hackathon.picky.core.network.model.response.HomeResponse
import hackathon.picky.core.network.model.response.PolicyDetailResponse
import hackathon.picky.core.network.model.response.PolicyIncomeResponse
import hackathon.picky.core.network.model.response.PolicyListResponse
import hackathon.picky.core.network.util.runRemote
import retrofit2.Response
import retrofit2.http.Header
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

    override suspend fun getBookmarkedPolicies(
        page: Int,
        size: Int
    ): ApiResponse<BookmarkedPolicyResponse> {
        return runRemote { policyApi.getBookmarkedPolicies(page, size) }
    }

    override suspend fun addBookmark(policyId: Long): ApiResponse<BookmarkToggleResponse> {
        return runRemote { policyApi.addBookmark(policyId) }
    }

    override suspend fun removeBookmark(policyId: Long): ApiResponse<BookmarkToggleResponse> {
        return runRemote { policyApi.removeBookmark(policyId) }
        
    override suspend fun getPolicyIncomeList(): ApiResponse<PolicyIncomeResponse> = runRemote {
        policyApi.getPolicyIncomeList()
    }
}