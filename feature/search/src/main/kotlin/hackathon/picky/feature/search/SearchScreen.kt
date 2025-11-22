package hackathon.picky.feature.search

import androidx.activity.compose.BackHandler
import androidx.activity.compose.LocalOnBackPressedDispatcherOwner
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.lifecycle.compose.collectAsStateWithLifecycle
import androidx.lifecycle.viewmodel.compose.viewModel
import hackathon.picky.core.designsystem.common.BackTopBar
import hackathon.picky.feature.search.component.SearchBar
import hackathon.picky.feature.search.component.SearchListVerticalGridScroll

@Composable
internal fun SearchRoute(
    padding: PaddingValues,
    navigateDetail: (Int) -> Unit,
    viewModel: SearchViewModel = hiltViewModel(),
    onBackPressed: () -> Unit
) {
    val uiState = viewModel.uiState.collectAsStateWithLifecycle()

    BackHandler(enabled = uiState.value is SearchUiState) {
        onBackPressed()
    }
    LaunchedEffect(Unit) {
        viewModel.getDataList()
    }

    SearchScreen(
        padding = padding,
        onBackClick = { onBackPressed() },
        uiState = uiState.value,
        onChangeQuery = viewModel::onChangeQuery,
        onClickSearch = viewModel::onClickSearch,
        onClickDetail = navigateDetail
    )
}

@Composable
internal fun SearchScreen(
    padding: PaddingValues,
    onBackClick: () -> Unit,
    uiState: SearchUiState,
    onChangeQuery: (String) -> Unit,
    onClickSearch: () -> Unit,
    onClickDetail: (Int) -> Unit
) {
    Column(
        modifier = Modifier.padding(padding)
    ) {
        when (uiState) {
            is SearchUiState.Success -> {
                BackTopBar(title = "검색", onClickBack = onBackClick)

                SearchBar(
                    query = uiState.query,
                    onChangeQuery = onChangeQuery,
                    onClickSearch = onClickSearch,
                    modifier = Modifier.padding(horizontal = 20.dp)
                )

                Spacer(modifier = Modifier.height(20.dp))

                SearchListVerticalGridScroll(
                    list = uiState.searchResult,
                    onClickDetail = onClickDetail,
                    modifier = Modifier.padding(horizontal = 20.dp)
                )
            }

            else -> {}
        }

    }
}