package hackathon.picky.core.network.api

import hackathon.picky.core.network.model.response.PolicyDetailResponse
import retrofit2.Response
import retrofit2.http.GET
import retrofit2.http.Path

interface PolicyApi {
    @GET("api/post/{policyId}")
    suspend fun getPolicyDetail(
        @Path("policyId") policyId: Long
    ): Response<PolicyDetailResponse>
}