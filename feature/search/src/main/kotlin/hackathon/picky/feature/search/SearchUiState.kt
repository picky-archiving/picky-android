package hackathon.picky.feature.search

import hackathon.picky.core.model.common.CommonListItem

sealed class SearchUiState {
    data object Init : SearchUiState()
    data class Success(
        val query: String = "",
        val searchResult: List<CommonListItem> = emptyList()
    ) : SearchUiState()
}