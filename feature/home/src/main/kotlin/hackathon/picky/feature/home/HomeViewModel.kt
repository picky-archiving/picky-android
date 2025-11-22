package hackathon.picky.feature.home

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import dagger.hilt.android.lifecycle.HiltViewModel
import hackathon.picky.core.data.repo.PolicyRepository
import hackathon.picky.core.model.common.Category
import hackathon.picky.core.model.common.SearchFilter
import hackathon.picky.feature.home.model.HomeUiState
import hackathon.picky.feature.home.model.HomeUiTest
import hackathon.picky.feature.home.model.PolicyDetail
import hackathon.picky.feature.home.model.policyDetailData
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.update
import kotlinx.coroutines.launch
import java.net.SocketTimeoutException
import java.text.SimpleDateFormat
import java.time.LocalDate
import java.util.Calendar
import java.util.Locale
import java.util.concurrent.TimeUnit
import javax.inject.Inject

@HiltViewModel
class HomeViewModel @Inject constructor(
    private val policyRepository: PolicyRepository
) : ViewModel() {
    private val _uiState: MutableStateFlow<HomeUiState> = MutableStateFlow(HomeUiState.Init)
    val uiState: StateFlow<HomeUiState> = _uiState.asStateFlow()

    // 스낵바 상태 관리
    private val _snackbarMessage = MutableStateFlow<SnackbarState?>(null)
    val snackbarMessage: StateFlow<SnackbarState?> = _snackbarMessage.asStateFlow()

    data class SnackbarState(
        val message: String,
        val isError: Boolean = false
    )

    fun init(policyId: Int?) = viewModelScope.launch { // 초기 화면 설정
        if(policyId != null) {
            policyRepository.getPolicyDetail(policyId.toLong())
                .onSuccess { data ->
                    _uiState.update {
                        HomeUiState.Detail(
                            previousUiState = null,
                            policyDetail = PolicyDetail(
                                id = data.id.toInt(),
                                title = data.title,
                                imgUrl = data.imgUrl,
                                department = data.department,
                                startDate = data.startDate,
                                closingDate = data.closingDate,
                                eligibility = data.eligibility,
                                description = data.description
                            ),
                            isBookmarked = data.bookmarked
                        )
                    }
                }.onFailure { throwable ->
                    throwable.printStackTrace()
                    _uiState.update {
                        HomeUiTest
                    }
                }
        } else _uiState.update { HomeUiTest }
    }


    fun clickDetail(policyId: Int) = viewModelScope.launch {
        policyRepository.getPolicyDetail(policyId.toLong())
            .onSuccess { data ->
                _uiState.update { prev->
                    HomeUiState.Detail(
                        previousUiState = prev,
                        policyDetail = PolicyDetail(
                            id = data.id.toInt(),
                            title = data.title,
                            imgUrl = data.imgUrl,
                            department = data.department,
                            startDate = data.startDate,
                            closingDate = data.closingDate,
                            eligibility = data.eligibility,
                            description = data.description
                        ),
                        isBookmarked = data.bookmarked
                    )
                }
            }.onFailure {

            }
    }


    fun clickList(category: Category) = viewModelScope.launch {
        val searchFilter =
            (uiState.value as? HomeUiState.ListScreen)?.searchFilter ?: SearchFilter.RECENT
        _uiState.update { prev ->
            HomeUiState.ListScreen(
                previousUiState = prev,
                list = HomeUiTest.infoSectionList[0].infoList,
                category = category,
                searchFilter = searchFilter
            )
        }
    }

    fun onFilterChange(searchFilter: SearchFilter) {
        _uiState.update { prev ->
            (prev as? HomeUiState.ListScreen?)?.let {
                it.copy(
                    searchFilter = searchFilter
                )
            } ?: prev
        }
    }

    fun toggleBookmark() = viewModelScope.launch {
        val currentState = _uiState.value
        if (currentState !is HomeUiState.Detail) return@launch

        val policyId = currentState.policyDetail.id.toLong()
        val isCurrentlyBookmarked = currentState.isBookmarked

        // 서버 응답을 기다림
        policyRepository.toggleBookmark(policyId, isCurrentlyBookmarked)
            .onSuccess { _ ->
                // 서버 응답 성공 시 UI 업데이트
                _uiState.update { state ->
                    if (state is HomeUiState.Detail) {
                        state.copy(isBookmarked = !isCurrentlyBookmarked)
                    } else state
                }

                // 성공 스낵바 표시
                val successMessage = if (isCurrentlyBookmarked) {
                    "북마크가 해제되었습니다."
                } else {
                    "북마크가 등록되었습니다."
                }
                _snackbarMessage.value = SnackbarState(
                    message = successMessage,
                    isError = false
                )
            }
            .onFailure { error ->
                // 에러 메시지 처리
                val errorMessage = when {
                    error is SocketTimeoutException || error.cause is SocketTimeoutException ->
                        "요청 시간이 초과되었습니다. 네트워크 연결을 확인해주세요."

                    error.message?.contains("Http: 500") == true ->
                        "서버에 일시적인 문제가 발생했습니다. 잠시 후 다시 시도해주세요."

                    else ->
                        "북마크 처리에 실패했습니다."
                }

                // 에러 스낵바 표시
                _snackbarMessage.value = SnackbarState(
                    message = errorMessage,
                    isError = true
                )

                error.printStackTrace()
            }
    }

    // 스낵바 메시지 초기화
    fun clearSnackbar() {
        _snackbarMessage.value = null
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

    fun goBack(onBackPressed: () -> Unit) {
        when (val state = _uiState.value) {
            is HomeUiState.Detail -> {
                (_uiState.value as HomeUiState.Detail).let {
                    if (it.previousUiState != null) _uiState.value = state.previousUiState!!
                    else onBackPressed()

                }
            }

            is HomeUiState.ListScreen -> {
                _uiState.value = state.previousUiState
            }

            else -> {
                onBackPressed()
            }
        }
    }
}