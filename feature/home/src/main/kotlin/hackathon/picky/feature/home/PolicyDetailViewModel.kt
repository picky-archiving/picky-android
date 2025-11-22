package hackathon.picky.feature.home

import androidx.lifecycle.ViewModel
import hackathon.picky.feature.home.model.PolicyDetail
import hackathon.picky.feature.home.model.PolicyDetailUiState
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow
import java.text.SimpleDateFormat
import java.util.Calendar
import java.util.Locale
import java.util.concurrent.TimeUnit

class PolicyDetailViewModel : ViewModel() {
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

    private val policyDetailData = PolicyDetail(
        id = "D-13",
        title = "정책명 예시입니다. 길어질 시 예시입니다. 길어질 시 예시입니다.",
        department = "주관 부처",
        applicationPeriod = "2024.09.07 ~ 2024.09.28",
        eligibility = listOf(
            "백엔드 개발자",
            "프론트엔드 개발자",
            "프로덕트디자이너플랫폼하는대학에학중인대학..."
        ),
        description = """정책 예시가 들어갑니다. 정책 예시가 들어갑니다.
정책 예시가 들어갑니다.정책 예시가 들어갑니다.
정책 예시가 들어갑니다.정책 예시가 들어갑니다.
정책 예시가 들어갑니다.정책 예시가 들어갑니다.
정책 예시가 들어갑니다.정책 예시가 들어갑니다.
정책 예시가 들어갑니다.정책 예시가 들어갑니다.
정책 예시가 들어갑니다.정책 예시가 들어갑니다.
정책 예시가 들어갑니다.정책 예시가 들어갑니다.
정책 예시가 들어갑니다.정책 예시가 들어갑니다.""".trimIndent()
    )

    private val _uiState = MutableStateFlow<PolicyDetailUiState>(
        PolicyDetailUiState.Success(
            policyDetail = policyDetailData,
            daysRemaining = calculateDaysRemaining("2024.09.28"),
            isBookmarked = false
        )
    )
    val uiState: StateFlow<PolicyDetailUiState> = _uiState.asStateFlow()

    fun toggleBookmark() {
        val currentState = _uiState.value
        if (currentState is PolicyDetailUiState.Success) {
            _uiState.value = currentState.copy(
                isBookmarked = !currentState.isBookmarked
            )
        }
    }
}
