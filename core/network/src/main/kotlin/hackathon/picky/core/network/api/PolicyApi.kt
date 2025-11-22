package hackathon.picky.core.network.api

import hackathon.picky.core.network.model.response.BookmarkedPolicyResponse
import hackathon.picky.core.network.model.response.BookmarkToggleResponse
import hackathon.picky.core.network.model.response.HomeResponse
import hackathon.picky.core.network.model.response.PolicyDetailResponse
import hackathon.picky.core.network.model.response.PolicyIncomeResponse
import hackathon.picky.core.network.model.response.PolicyListResponse
import retrofit2.Response
import retrofit2.http.DELETE
import retrofit2.http.GET
import retrofit2.http.POST
import retrofit2.http.Header
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

    /**
     * 북마크된 정책 목록 조회
     * @param page 페이지 번호 (0-base, default = 0)
     * @param size 페이지 크기 (default = 20)
     */
    @GET("api/post/bookmarked")
    suspend fun getBookmarkedPolicies(
        @Query("page") page: Int = 0,
        @Query("size") size: Int = 20
    ): Response<BookmarkedPolicyResponse>

    /**
     * 북마크 등록
     * @param policyId 정책 ID
     * @return 북마크 상태 (bookmarked = true)
     */
    @POST("api/post/{policyId}/bookmark")
    suspend fun addBookmark(
        @Path("policyId") policyId: Long
    ): Response<BookmarkToggleResponse>

    /**
     * 북마크 해제
     * @param policyId 정책 ID
     * @return 북마크 상태 (bookmarked = false)
     */
    @DELETE("api/post/{policyId}/bookmark")
    suspend fun removeBookmark(
        @Path("policyId") policyId: Long
    ): Response<BookmarkToggleResponse>

    /**
     * 소득분위별 게시물 조회(조회수 상위 3개)
     * @Header userId
     */
    @GET("api/post/income")
    suspend fun getPolicyIncomeList(
        @Header("X-USER-ID") userId: Long = 1
    ): Response<PolicyIncomeResponse>
}