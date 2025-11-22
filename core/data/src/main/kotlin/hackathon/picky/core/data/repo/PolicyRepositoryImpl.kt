package hackathon.picky.core.data.repo

import hackathon.picky.core.data.mapper.toResult
import hackathon.picky.core.network.datasource.policy.PolicyDatasource
import hackathon.picky.core.network.di.FakeDataSource
import hackathon.picky.core.network.model.response.PolicyDetailData
import javax.inject.Inject

class PolicyRepositoryImpl @Inject constructor(
    @param:FakeDataSource private val policyDatasource: PolicyDatasource
) : PolicyRepository {
    override suspend fun getPolicyDetail(policyId: Long): Result<PolicyDetailData> {
        return policyDatasource.getPolicyDetail(policyId).toResult(
            transform = { response ->
                response.data
            }
        )
    }
}