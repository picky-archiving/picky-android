package hackathon.picky.feature.mypage

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import dagger.hilt.android.lifecycle.HiltViewModel
import hackathon.picky.core.data.repo.PolicyRepository
import hackathon.picky.core.model.CommonListItemTest
import hackathon.picky.feature.home.model.PolicyDetail
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
    private val policyRepository: PolicyRepository
) : ViewModel() {
    private val _uiState = MutableStateFlow<MyPageUiState>(
        MyPageUiState.Main(
            rank = "3분위",
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
        val previousState = _uiState.value

        policyRepository.getPolicyDetail(policyId.toLong())
            .onSuccess { policyData ->
                // API 응답을 PolicyDetail 모델로 변환
                val policyDetail = PolicyDetail(
                    id = policyData.id.toString(),
                    title = policyData.title,
                    department = policyData.host,
                    applicationPeriod = "${policyData.startDate} ~ ${policyData.endDate ?: "상시"}",
                    eligibility = policyData.qualifications,
                    description = policyData.content
                )

                val daysRemaining = policyData.endDate?.let {
                    calculateDaysRemaining(it)
                } ?: -1  // 상시모집인 경우 -1

                _uiState.update {
                    MyPageUiState.Detail(
                        previousUiState = previousState,
                        policyDetail = policyDetail,
                        daysRemaining = daysRemaining,
                        isBookmarked = policyData.bookmarked
                    )
                }
            }
            .onFailure { error ->
                // 에러 처리 - 필요시 에러 상태 추가
                // TODO: 에러 상태 표시
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
