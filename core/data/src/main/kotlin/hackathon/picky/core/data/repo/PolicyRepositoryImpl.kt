package hackathon.picky.core.data.repo

import hackathon.picky.core.data.mapper.toResult
import hackathon.picky.core.network.datasource.policy.PolicyDatasource
import hackathon.picky.core.network.di.FakeDataSource
import hackathon.picky.core.network.di.RealDataSource
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

    override suspend fun getPolicyDetail(policyId: Long): Result<PolicyDetailData> {
        return policyDatasource.getPolicyDetail(policyId).toResult(
            transform = { response ->
                response.data
            }
        )
    }
}