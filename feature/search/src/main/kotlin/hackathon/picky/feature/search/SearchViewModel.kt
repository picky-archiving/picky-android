package hackathon.picky.feature.search

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import dagger.hilt.android.lifecycle.HiltViewModel
import hackathon.picky.core.model.common.CommonListItemTest
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.update
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class SearchViewModel @Inject constructor() : ViewModel() {
    private val _uiState: MutableStateFlow<SearchUiState> =
        MutableStateFlow(SearchUiState.Success())
    val uiState: StateFlow<SearchUiState> = _uiState.asStateFlow()

    fun onClickSearch() = viewModelScope.launch {
        _uiState.update { prev ->
            (prev as? SearchUiState.Success)?.copy(
                searchResult = listOf(
                    CommonListItemTest, CommonListItemTest
                )
            ) ?: prev
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