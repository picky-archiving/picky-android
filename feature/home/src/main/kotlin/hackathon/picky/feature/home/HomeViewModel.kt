package hackathon.picky.feature.home

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import dagger.hilt.android.lifecycle.HiltViewModel
import hackathon.picky.core.data.repo.PolicyRepository
import hackathon.picky.core.model.common.Category
import hackathon.picky.core.model.common.CommonListItem
import hackathon.picky.core.model.common.SearchFilter
import hackathon.picky.feature.home.model.HomeSectionListItem
import hackathon.picky.feature.home.model.HomeUiState
import hackathon.picky.feature.home.model.HomeUiTest
import hackathon.picky.feature.home.model.PolicyDetail
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
        if (policyId != null) {
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
        } else {
            policyRepository.getHomeData().onSuccess { data ->
                val data =
                    _uiState.update {
                        HomeUiState.Main(
                            infoSectionList = data.categories.map {
                                HomeSectionListItem(
                                    category = it.category,
                                    infoList = it.policies.map {
                                        CommonListItem(
                                            id = it.id.toInt(),
                                            title = it.title,
                                            imageUrl = it.imageUrl,
                                            closingDate = it.endDate,
                                            viewCount = it.viewCount
                                        )
                                    }
                                )
                            },
                            topBannerList = data.incomePolicies.take(3).map {
                                CommonListItem(
                                    id = it.id.toInt(),
                                    title = it.title,
                                    imageUrl = it.imageUrl,
                                    closingDate = it.endDate,
                                )
                            },
                            topList = data.incomePolicies.take(3).map {
                                CommonListItem(
                                    id = it.id.toInt(),
                                    title = it.title,
                                    imageUrl = it.imageUrl,
                                    closingDate = it.endDate,
                                )
                            }
                        )
                    }
            }.onFailure { }
        }
    }


    fun clickDetail(policyId: Int) = viewModelScope.launch {
        policyRepository.getPolicyDetail(policyId.toLong())
            .onSuccess { data ->
                _uiState.update { prev ->
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
        _uiState.update { prev ->
            (prev as? HomeUiState.Main)?.let { mainState ->
                HomeUiState.ListScreen(
                    previousUiState = prev,
                    list = mainState.infoSectionList
                        .filter { it.category == category }
                        .flatMap { it.infoList }
                        .sortedWith(compareByDescending<CommonListItem> { it.closingDate ?: LocalDate.MAX }),
                    category = category,
                    searchFilter = SearchFilter.RECENT
                )
            } ?: prev
        }
    }


    fun onFilterChange(searchFilter: SearchFilter) {
        _uiState.update { prev ->
            (prev as? HomeUiState.ListScreen?)?.let {
                val sortedList = if (searchFilter == SearchFilter.RECENT) {
                    prev.list.sortedWith(compareByDescending<CommonListItem> { it.closingDate ?: LocalDate.MAX })
                } else {
                    // 인기순: viewCount 기준 내림차순, null은 0으로 처리
                    prev.list.sortedByDescending { it.viewCount ?: 0L }
                }

                it.copy(
                    searchFilter = searchFilter,
                    list = sortedList
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