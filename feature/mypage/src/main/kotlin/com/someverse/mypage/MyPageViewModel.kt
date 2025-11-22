package com.someverse.mypage

import androidx.lifecycle.ViewModel
import com.someverse.mypage.model.BookmarkItem
import com.someverse.mypage.model.MyPageUiState
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.update
import java.time.LocalDateTime
import javax.inject.Inject

@HiltViewModel
class MyPageViewModel @Inject constructor() : ViewModel() {
    private val _uiState = MutableStateFlow(
        MyPageUiState(
            rank = "0분위",
            showRankBottomSheet = false,
            bookmarkedPolicies = listOf(
                BookmarkItem(
                    title = "정책 관련 타이틀이 들어갑니다. 2줄 이상 예시입니...",
                    imageRes = android.R.drawable.ic_menu_camera,
                    closingDate = LocalDateTime.now().plusDays(13)
                ),
                BookmarkItem(
                    title = "정책 관련 타이틀이 들어갑니다. 2줄 이상 예시입니...",
                    imageRes = android.R.drawable.ic_menu_camera,
                    closingDate = LocalDateTime.now().plusDays(13)
                ),
                BookmarkItem(
                    title = "정책 관련 타이틀이 들어갑니다. 2줄 이상 예시입니...",
                    imageRes = android.R.drawable.ic_menu_camera,
                    closingDate = LocalDateTime.now().plusDays(13)
                ),
                BookmarkItem(
                    title = "정책 관련 타이틀이 들어갑니다. 2줄 이상 예시입니...",
                    imageRes = android.R.drawable.ic_menu_camera,
                    closingDate = LocalDateTime.now().plusDays(13)
                )
            )
        )
    )
    val uiState: StateFlow<MyPageUiState> = _uiState.asStateFlow()

    fun onEditClick() {
        _uiState.update { it.copy(showRankBottomSheet = true) }
    }

    fun dismissBottomSheet() {
        _uiState.update { it.copy(showRankBottomSheet = false) }
    }

    fun updateRank(rank: String) {
        _uiState.update {
            it.copy(
                rank = rank,
                showRankBottomSheet = false
            )
        }
    }
}