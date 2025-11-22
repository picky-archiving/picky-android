package hackathon.picky.core.data.repo

import hackathon.picky.core.data.mapper.policiy.toEntity
import hackathon.picky.core.data.mapper.toResult
import hackathon.picky.core.data.model.policy.PolicyDetailEntity
import hackathon.picky.core.network.datasource.policy.PolicyDatasource
import hackathon.picky.core.network.di.FakeDataSource
import hackathon.picky.core.network.di.RealDataSource
import hackathon.picky.core.network.model.response.BookmarkedPolicyPageData
import hackathon.picky.core.network.model.response.BookmarkToggleData
import hackathon.picky.core.network.model.response.HomeData
import hackathon.picky.core.network.model.response.PolicyDetailData
import hackathon.picky.core.network.model.response.PolicyPageData
import javax.inject.Inject

class PolicyRepositoryImpl @Inject constructor(
    @param:RealDataSource private val policyDatasource: PolicyDatasource
) : PolicyRepository {
    override suspend fun getHomeData(): Result<HomeData> {
        return policyDatasource.getHomeData().toResult(
            transform = { response ->
                response.data
            }
        )
    }

    override suspend fun getPolicyList(page: Int, size: Int): Result<PolicyPageData> {
        return policyDatasource.getPolicyList(page, size).toResult(
            transform = { response ->
                response.data
            }
        )
    }

    override suspend fun getPolicyDetail(policyId: Long): Result<PolicyDetailEntity> {
        return policyDatasource.getPolicyDetail(policyId).toResult(
            transform = { response ->
                response.data.toEntity()
            }
        )
    }

    override suspend fun getBookmarkedPolicies(
        page: Int,
        size: Int
    ): Result<BookmarkedPolicyPageData> {
        return policyDatasource.getBookmarkedPolicies(page, size).toResult(
            transform = { response ->
                response.data
            }
        )
    }

    override suspend fun addBookmark(policyId: Long): Result<BookmarkToggleData> {
        return policyDatasource.addBookmark(policyId).toResult(
            transform = { response ->
                response.data
            }
        )
    }

    override suspend fun removeBookmark(policyId: Long): Result<BookmarkToggleData> {
        return policyDatasource.removeBookmark(policyId).toResult(
            transform = { response ->
                response.data
            }
        )
    }
}