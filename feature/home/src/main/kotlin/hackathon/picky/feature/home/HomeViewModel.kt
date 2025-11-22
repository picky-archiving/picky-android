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

    fun init(policyId: Int?) = viewModelScope.launch { // 초기 화면 설정
        if(policyId != null) {
            policyRepository.getPolicyDetail(1)
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

    fun toggleBookmark() {
        val currentState = _uiState.value
        if (currentState is HomeUiState.Detail) {
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