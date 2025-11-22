package hackathon.picky.core.network.api

import hackathon.picky.core.network.model.response.HomeResponse
import hackathon.picky.core.network.model.response.PolicyDetailResponse
import hackathon.picky.core.network.model.response.PolicyListResponse
import retrofit2.Response
import retrofit2.http.GET
import retrofit2.http.Path
import retrofit2.http.Query

interface PolicyApi {
    /**
     * 메인 화면 조회
     * - 카테고리별 정책 목록
     * - 소득분위 기반 추천 정책 목록
     */
    @GET("api/home")
    suspend fun getHomeData(): Response<HomeResponse>

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