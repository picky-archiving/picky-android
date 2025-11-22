package hackathon.picky.feature.mypage

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import dagger.hilt.android.lifecycle.HiltViewModel
import hackathon.picky.core.data.repo.PolicyRepository
import hackathon.picky.core.data.repo.UserRepository
import hackathon.picky.core.model.common.CommonListItemTest
import hackathon.picky.feature.mypage.model.MyPageUiState
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.update
import kotlinx.coroutines.launch
import java.text.SimpleDateFormat
import java.util.Calendar
import java.util.Locale
import java.util.concurrent.TimeUnit
import javax.inject.Inject

@HiltViewModel
class MyPageViewModel @Inject constructor(
    private val policyRepository: PolicyRepository,
    private val userRepository: UserRepository
) : ViewModel() {
    private val _uiState = MutableStateFlow<MyPageUiState>(
        MyPageUiState.Main(
            rank = "1분위",
            showRankBottomSheet = false,
            bookmarkedPolicies = listOf(
                CommonListItemTest,
                CommonListItemTest,
                CommonListItemTest,
                CommonListItemTest,
                CommonListItemTest,
                CommonListItemTest,
                CommonListItemTest,
                CommonListItemTest,
            ),
            isLoading = true
        )
    )
    val uiState: StateFlow<MyPageUiState> = _uiState.asStateFlow()

    init {
        loadUserIncomeBracket()
    }

    private fun loadUserIncomeBracket() = viewModelScope.launch {
        val currentState = _uiState.value
        if (currentState !is MyPageUiState.Main) return@launch

        userRepository.getUserIncomeBracket(userId = 1)
            .onSuccess { incomeBracket ->
                _uiState.update {
                    currentState.copy(
                        rank = "${incomeBracket}분위",
                        isLoading = false,
                        errorMessage = null
                    )
                }
            }
            .onFailure { error ->
                _uiState.update {
                    currentState.copy(
                        isLoading = false,
                        errorMessage = "소득분위 조회에 실패했습니다"
                    )
                }
            }
    }

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

    fun updateRank(rank: String) = viewModelScope.launch {
        val currentState = _uiState.value
        if (currentState !is MyPageUiState.Main) return@launch

        // 분위 문자열에서 숫자만 추출 (예: "3분위" -> 3)
        val incomeBracket = rank.replace("분위", "").toIntOrNull() ?: return@launch

        // 바텀시트 닫고 로딩 상태 표시
        _uiState.update {
            currentState.copy(
                showRankBottomSheet = false,
                isLoading = true
            )
        }

        userRepository.updateUserIncomeBracket(userId = 1, incomeBracket = incomeBracket)
            .onSuccess { updatedIncomeBracket ->
                _uiState.update {
                    (it as MyPageUiState.Main).copy(
                        rank = "${updatedIncomeBracket}분위",
                        isLoading = false,
                        errorMessage = null
                    )
                }
            }
            .onFailure { error ->
                _uiState.update {
                    (it as MyPageUiState.Main).copy(
                        isLoading = false,
                        errorMessage = "소득분위 수정에 실패했습니다"
                    )
                }
            }
    }

    fun clearErrorMessage() {
        val currentState = _uiState.value
        if (currentState is MyPageUiState.Main) {
            _uiState.update { currentState.copy(errorMessage = null) }
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
            // API 응답 형식: "2025-05-31"
            val formatter = SimpleDateFormat("yyyy-MM-dd", Locale.getDefault())

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
