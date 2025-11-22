package hackathon.picky.feature.search

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import dagger.hilt.android.lifecycle.HiltViewModel
import hackathon.picky.core.data.repo.PolicyRepository
import hackathon.picky.core.model.common.CommonListItem
import hackathon.picky.core.model.common.CommonListItemTest
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.update
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class SearchViewModel @Inject constructor(
    private val policyRepository: PolicyRepository
) : ViewModel() {
    private val _uiState: MutableStateFlow<SearchUiState> =
        MutableStateFlow(SearchUiState.Success())
    val uiState: StateFlow<SearchUiState> = _uiState.asStateFlow()

    fun getDataList() = viewModelScope.launch {
        policyRepository.getHomeData().onSuccess {
            _uiState.update { prev ->
                (prev as SearchUiState.Success).copy(
                    dataList = (it.incomePolicies.map { policy ->
                        CommonListItem(
                            id = policy.id.toInt(),
                            title = policy.title,
                            imageUrl = policy.imageUrl,
                            closingDate = policy.endDate,
                            viewCount = policy.viewCount
                        )
                    } + it.categories.flatMap { categoryGroup ->
                        categoryGroup.policies.map { policy ->
                            CommonListItem(
                                id = policy.id.toInt(),
                                title = policy.title,
                                imageUrl = policy.imageUrl,
                                closingDate = policy.endDate,
                                viewCount = policy.viewCount
                            )
                        }
                    }).distinctBy { it.id }
                )
            }
        }
    }

    fun onClickSearch() = viewModelScope.launch {
        _uiState.update { prev ->
            val state = prev as SearchUiState.Success
            val query = state.query.trim()

            state.copy(
                searchResult = state.dataList.filter { item ->
                    item.title.contains(query, ignoreCase = true)
                }
            )
        }
    }

    fun onChangeQuery(query: String) {
        (_uiState.value as? SearchUiState.Success)?.let {
            _uiState.value = it.copy(
                query = query
            )
        }
    }
}