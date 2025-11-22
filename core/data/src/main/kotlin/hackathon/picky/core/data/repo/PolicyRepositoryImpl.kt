package hackathon.picky.core.data.repo

import hackathon.picky.core.data.mapper.policiy.toEntity
import hackathon.picky.core.data.mapper.toResult
import hackathon.picky.core.data.model.policy.PolicyDetailEntity
import hackathon.picky.core.network.datasource.policy.PolicyDatasource
import hackathon.picky.core.network.di.FakeDataSource
import hackathon.picky.core.network.di.RealDataSource
import hackathon.picky.core.network.model.response.PolicyDetailData
import javax.inject.Inject

class PolicyRepositoryImpl @Inject constructor(
    @param:RealDataSource private val policyDatasource: PolicyDatasource
) : PolicyRepository {
    override suspend fun getPolicyDetail(policyId: Long): Result<PolicyDetailEntity> {
        return policyDatasource.getPolicyDetail(policyId).toResult(
            transform = { response ->
                response.data.toEntity()
            }
        )
    }
}