package com.someverse.mypage.model

import androidx.annotation.DrawableRes
import java.time.LocalDateTime

data class BookmarkItem(
    val title: String,
    @DrawableRes val imageRes: Int,
    val closingDate: LocalDateTime
)

data class MyPageUiState(
    val rank: String = "0분위",
    val showRankBottomSheet: Boolean = false,
    val bookmarkedPolicies: List<BookmarkItem> = emptyList()
) {
    val incomeRange: String
        get() = IncomeRangeMapper.getIncomeRange(rank)
}