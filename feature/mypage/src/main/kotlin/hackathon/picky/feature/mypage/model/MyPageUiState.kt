package hackathon.picky.feature.mypage.model

import androidx.annotation.DrawableRes
import hackathon.picky.feature.home.model.PolicyDetail
import java.time.LocalDateTime

data class BookmarkItem(
    val id: Int,
    val title: String,
    @DrawableRes val imageRes: Int,
    val closingDate: LocalDateTime
)

sealed class MyPageUiState {
    data class Main(
        val rank: String = "1분위",
        val showRankBottomSheet: Boolean = false,
        val bookmarkedPolicies: List<BookmarkItem> = emptyList()
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
