package hackathon.picky.feature.mypage

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import hackathon.picky.feature.home.model.policyDetailData
import hackathon.picky.feature.mypage.model.BookmarkItem
import hackathon.picky.feature.mypage.model.MyPageUiState
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.update
import kotlinx.coroutines.launch
import java.text.SimpleDateFormat
import java.time.LocalDateTime
import java.util.Calendar
import java.util.Locale
import java.util.concurrent.TimeUnit
import javax.inject.Inject

@HiltViewModel
class MyPageViewModel @Inject constructor() : ViewModel() {
    private val _uiState = MutableStateFlow<MyPageUiState>(
        MyPageUiState.Main(
            rank = "3분위",
            showRankBottomSheet = false,
            bookmarkedPolicies = listOf(
                BookmarkItem(
                    id = 1,
                    title = "정책 관련 타이틀이 들어갑니다. 2줄 이상 예시입니...",
                    imageRes = android.R.drawable.ic_menu_camera,
                    closingDate = LocalDateTime.now().plusDays(13)
                ),
                BookmarkItem(
                    id = 2,
                    title = "정책 관련 타이틀이 들어갑니다. 2줄 이상 예시입니...",
                    imageRes = android.R.drawable.ic_menu_camera,
                    closingDate = LocalDateTime.now().plusDays(13)
                ),
                BookmarkItem(
                    id = 3,
                    title = "정책 관련 타이틀이 들어갑니다. 2줄 이상 예시입니...",
                    imageRes = android.R.drawable.ic_menu_camera,
                    closingDate = LocalDateTime.now().plusDays(13)
                ),
                BookmarkItem(
                    id = 4,
                    title = "정책 관련 타이틀이 들어갑니다. 2줄 이상 예시입니...",
                    imageRes = android.R.drawable.ic_menu_camera,
                    closingDate = LocalDateTime.now().plusDays(13)
                ),
                BookmarkItem(
                    id = 5,
                    title = "정책 관련 타이틀이 들어갑니다. 2줄 이상 예시입니...",
                    imageRes = android.R.drawable.ic_menu_camera,
                    closingDate = LocalDateTime.now().plusDays(13)
                ),
                BookmarkItem(
                    id = 6,
                    title = "정책 관련 타이틀이 들어갑니다. 2줄 이상 예시입니...",
                    imageRes = android.R.drawable.ic_menu_camera,
                    closingDate = LocalDateTime.now().plusDays(13)
                )
            )
        )
    )
    val uiState: StateFlow<MyPageUiState> = _uiState.asStateFlow()

    fun onEditClick() {
        val currentState = _uiState.value
        if (currentState is MyPageUiState.Main) {
            _uiState.update { currentState.copy(showRankBottomSheet = true) }
        }
    }

    fun dismissBottomSheet() {
        val currentState = _uiState.value
        if (currentState is MyPageUiState.Main) {
            _uiState.update { currentState.copy(showRankBottomSheet = false) }
        }
    }

    fun updateRank(rank: String) {
        val currentState = _uiState.value
        if (currentState is MyPageUiState.Main) {
            _uiState.update {
                currentState.copy(
                    rank = rank,
                    showRankBottomSheet = false
                )
            }
        }
    }

    fun clickDetail(policyId: Int) = viewModelScope.launch {
        _uiState.update { prev ->
            MyPageUiState.Detail(
                previousUiState = prev,
                policyDetail = policyDetailData,
                daysRemaining = calculateDaysRemaining("2024.09.28"),
                isBookmarked = false
            )
        }
    }

    fun toggleBookmark() {
        val currentState = _uiState.value
        if (currentState is MyPageUiState.Detail) {
            _uiState.value = currentState.copy(
                isBookmarked = !currentState.isBookmarked
            )
        }
    }

    private fun calculateDaysRemaining(endDate: String): Int {
        return try {
            val formatter = SimpleDateFormat("yyyy.MM.dd", Locale.getDefault())
            val end = formatter.parse(endDate) ?: return 0
            val today = Calendar.getInstance().time
            val diffInMillis = end.time - today.time
            TimeUnit.MILLISECONDS.toDays(diffInMillis).toInt()
        } catch (e: Exception) {
            0
        }
    }

    fun goBackToMain() {
        val state = _uiState.value
        if (state is MyPageUiState.Detail) {
            _uiState.value = state.previousUiState
        }
    }
}
