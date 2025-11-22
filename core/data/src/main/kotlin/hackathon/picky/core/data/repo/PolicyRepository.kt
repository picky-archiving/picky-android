package hackathon.picky.core.data.repo

import hackathon.picky.core.network.model.response.BookmarkedPolicyPageData
import hackathon.picky.core.network.model.response.BookmarkToggleData
import hackathon.picky.core.network.model.response.HomeData
import hackathon.picky.core.network.model.response.PolicyDetailData
import hackathon.picky.core.network.model.response.PolicyPageData
import hackathon.picky.core.data.model.policy.PolicyDetailEntity

interface PolicyRepository {
    /**
     * 메인 화면 데이터 조회
     * @return Result<HomeData> 성공 시 홈 데이터, 실패 시 에러
     */
    suspend fun getHomeData(): Result<HomeData>

    /**
     * 전체 게시물 목록 조회
     * @param page 페이지 번호 (0-base)
     * @param size 페이지 크기
     * @return Result<PolicyPageData> 성공 시 페이징된 정책 목록, 실패 시 에러
     */
    suspend fun getPolicyList(page: Int, size: Int): Result<PolicyPageData>

    /**
     * 정책 상세 정보 조회
     * @param policyId 정책 ID
     * @return Result<PolicyDetailData> 성공 시 정책 상세 정보, 실패 시 에러
     */
    suspend fun getPolicyDetail(policyId: Long): Result<PolicyDetailEntity>

    /**
     * 북마크된 정책 목록 조회
     * @param page 페이지 번호 (0-base)
     * @param size 페이지 크기
     * @return Result<BookmarkedPolicyPageData> 성공 시 북마크된 정책 목록, 실패 시 에러
     */
    suspend fun getBookmarkedPolicies(page: Int, size: Int): Result<BookmarkedPolicyPageData>

    /**
     * 북마크 등록
     * @param policyId 정책 ID
     * @return Result<BookmarkToggleData> 성공 시 북마크 상태 (bookmarked = true), 실패 시 에러
     */
    suspend fun addBookmark(policyId: Long): Result<BookmarkToggleData>

    /**
     * 북마크 해제
     * @param policyId 정책 ID
     * @return Result<BookmarkToggleData> 성공 시 북마크 상태 (bookmarked = false), 실패 시 에러
     */
    suspend fun removeBookmark(policyId: Long): Result<BookmarkToggleData>

    /**
     * 북마크 토글 (현재 상태에 따라 등록/해제)
     * @param policyId 정책 ID
     * @param isCurrentlyBookmarked 현재 북마크 상태
     * @return Result<BookmarkToggleData> 성공 시 변경된 북마크 상태, 실패 시 에러
     */
    suspend fun toggleBookmark(
        policyId: Long,
        isCurrentlyBookmarked: Boolean
    ): Result<BookmarkToggleData> {
        return if (isCurrentlyBookmarked) {
            removeBookmark(policyId)
        } else {
            addBookmark(policyId)
        }
    }
}