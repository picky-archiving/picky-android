package hackathon.picky.core.network.api

import hackathon.picky.core.network.model.response.PolicyDetailResponse
import hackathon.picky.core.network.model.response.PolicyListResponse
import retrofit2.Response
import retrofit2.http.GET
import retrofit2.http.Path
import retrofit2.http.Query

interface PolicyApi {
    /**
     * 전체 게시물 목록 조회
     * @param page 페이지 번호 (0-base, default = 0)
     * @param size 페이지 크기 (default = 10)
     */
    @GET("api/post")
    suspend fun getPolicyList(
        @Query("page") page: Int = 0,
        @Query("size") size: Int = 10
    ): Response<PolicyListResponse>

    /**
     * 정책 상세 정보 조회
     * @param policyId 정책 ID
     */
    @GET("api/post/{policyId}")
    suspend fun getPolicyDetail(
        @Path("policyId") policyId: Long
    ): Response<PolicyDetailResponse>
}