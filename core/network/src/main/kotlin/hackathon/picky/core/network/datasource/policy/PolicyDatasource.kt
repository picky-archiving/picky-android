package hackathon.picky.core.network.datasource.policy

import hackathon.picky.core.network.model.ApiResponse
import hackathon.picky.core.network.model.response.BookmarkedPolicyResponse
import hackathon.picky.core.network.model.response.BookmarkToggleResponse
import hackathon.picky.core.network.model.response.HomeResponse
import hackathon.picky.core.network.model.response.PolicyDetailResponse
import hackathon.picky.core.network.model.response.PolicyListResponse

interface PolicyDatasource {
    /**
     * 메인 화면 데이터 조회
     */
    suspend fun getHomeData(): ApiResponse<HomeResponse>

    /**
     * 전체 게시물 목록 조회
     * @param page 페이지 번호
     * @param size 페이지 크기
     */
    suspend fun getPolicyList(page: Int, size: Int): ApiResponse<PolicyListResponse>

    /**
     * 정책 상세 정보 조회
     * @param policyId 정책 ID
     */
    suspend fun getPolicyDetail(policyId: Long): ApiResponse<PolicyDetailResponse>

    /**
     * 북마크된 정책 목록 조회
     * @param page 페이지 번호
     * @param size 페이지 크기
     */
    suspend fun getBookmarkedPolicies(page: Int, size: Int): ApiResponse<BookmarkedPolicyResponse>

    /**
     * 북마크 등록
     * @param policyId 정책 ID
     */
    suspend fun addBookmark(policyId: Long): ApiResponse<BookmarkToggleResponse>

    /**
     * 북마크 해제
     * @param policyId 정책 ID
     */
    suspend fun removeBookmark(policyId: Long): ApiResponse<BookmarkToggleResponse>
}