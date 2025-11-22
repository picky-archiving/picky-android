package hackathon.picky.feature.mypage.model

import hackathon.picky.core.model.common.CommonListItem
import hackathon.picky.feature.home.model.PolicyDetail

sealed class MyPageUiState {
    data class Main(
        val rank: String = "1분위",
        val showRankBottomSheet: Boolean = false,
        val bookmarkedPolicies: List<CommonListItem> = emptyList(),
        val errorMessage: String? = null,
        val isLoading: Boolean = false
    ) : MyPageUiState() {
        val incomeRange: String
            get() = IncomeRangeMapper.getIncomeRange(rank)
    }

    data class Detail(
        val previousUiState: MyPageUiState,
        val policyDetail: PolicyDetail,
        val daysRemaining: Int,
        val isBookmarked: Boolean = false
    ) : MyPageUiState()
}
