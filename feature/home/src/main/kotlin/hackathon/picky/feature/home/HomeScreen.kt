package hackathon.picky.feature.home

import androidx.activity.compose.BackHandler
import androidx.activity.compose.LocalOnBackPressedDispatcherOwner
import androidx.compose.animation.Crossfade
import androidx.compose.animation.core.tween
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.verticalScroll
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.HorizontalDivider
import androidx.compose.material3.MaterialTheme
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.lifecycle.compose.collectAsStateWithLifecycle
import hackathon.picky.core.designsystem.common.BackTopBar
import hackathon.picky.core.designsystem.common.LogoTopBar
import hackathon.picky.core.designsystem.theme.Gray50
import hackathon.picky.core.model.common.Category
import hackathon.picky.core.model.common.SearchFilter
import hackathon.picky.feature.home.component.HomeInfoSection
import hackathon.picky.feature.home.component.HomeListVerticalGridScroll
import hackathon.picky.feature.home.component.HomeTopBanner
import hackathon.picky.feature.home.component.ListInfoFilterSection
import hackathon.picky.feature.home.component.PolicyDetailContent
import hackathon.picky.feature.home.model.HomeUiState
import hackathon.picky.feature.home.model.HomeUiTest

@Composable
internal fun HomeRoute(
    padding: PaddingValues,
    navigateMy: () -> Unit,
    navigateSearch: () -> Unit,
    viewModel: HomeViewModel = hiltViewModel(),
    policyId: Int? = null,
    onBackPressed: () -> Unit
) {
    val uiState = viewModel.uiState.collectAsStateWithLifecycle()

    val backDispatcher = LocalOnBackPressedDispatcherOwner.current?.onBackPressedDispatcher

    LaunchedEffect(policyId) {
        viewModel.init(policyId)
    }

    BackHandler(enabled = uiState.value is HomeUiState) {
        backDispatcher?.let { viewModel.goBack(onBackPressed) }
    }

    HomeScreen(
        padding = padding,
        uiState = uiState.value,
        onBackClick = { backDispatcher?.let { viewModel.goBack(onBackPressed) } },
        onBookMarClick = viewModel::toggleBookmark,
        onClickDetail = viewModel::clickDetail,
        navigateMy = navigateMy,
        navigateSearch = navigateSearch,
        onClickList = viewModel::clickList,
        onFilterChange = viewModel::onFilterChange
    )
}

@OptIn(ExperimentalMaterial3Api::class)
@Composable
private fun HomeScreen(
    padding: PaddingValues,
    uiState: HomeUiState,
    onClickDetail: (Int) -> Unit,
    onBackClick: () -> Unit,
    onBookMarClick: () -> Unit,
    onClickList: (category: Category) -> Unit,
    navigateMy: () -> Unit,
    navigateSearch: () -> Unit,
    onFilterChange: (SearchFilter) -> Unit
) {
    val scrollState = rememberScrollState()
    Crossfade(
        targetState = uiState,
        animationSpec = tween(durationMillis = 500)
    ) { uiState ->
        Column(
            modifier = Modifier
                .fillMaxSize()
                .background(MaterialTheme.colorScheme.background)
                .padding(padding)
        ) {
            when (uiState) {
                is HomeUiState.Main -> {
                    Column(
                        modifier = Modifier.fillMaxSize()
                    ) {
                        LogoTopBar(
                            onClickSearch = { navigateSearch() },
                            onClickMy = { navigateMy() }
                        )

                        Spacer(modifier = Modifier.height(20.dp))

                        Column(
                            modifier = Modifier
                                .fillMaxSize()
                                .verticalScroll(scrollState)
                        ) {
                            HomeTopBanner(
                                listItem = uiState.topBannerList,
                                onClickDetail = onClickDetail,
                                modifier = Modifier.padding(horizontal = 20.dp),
                                onClickList = onClickList,
                            )

                            Spacer(modifier = Modifier.height(20.dp))

                            HomeInfoSection(
                                list = uiState.topList,
                                onClickDetail = onClickDetail,
                                onClickList = onClickList,
                                modifier = Modifier.padding(horizontal = 20.dp)
                            )

                            Spacer(modifier = Modifier.height(20.dp))

                            (uiState as HomeUiState.Main).infoSectionList.forEach { item ->
                                HorizontalDivider(Modifier.height(8.dp), color = Gray50)

                                HomeInfoSection(
                                    category = item.category,
                                    description = item.description,
                                    list = item.infoList,
                                    onClickDetail = onClickDetail,
                                    onClickList = onClickList,
                                    modifier = Modifier.padding(horizontal = 20.dp)
                                )
                            }
                        }
                    }
                }

                is HomeUiState.Detail -> {
                    PolicyDetailContent(
                        uiState = uiState as HomeUiState.Detail,
                        onBackClick = onBackClick,
                        onBookmarkClick = onBookMarClick
                    )
                }

                is HomeUiState.ListScreen -> {
                    val listUiState = uiState as HomeUiState.ListScreen

                    BackTopBar(
                        title = listUiState.category.label,
                        onClickBack = onBackClick
                    )

                    ListInfoFilterSection(
                        modifier = Modifier.padding(horizontal = 20.dp),
                        listCount = listUiState.list.size,
                        filter = listUiState.searchFilter,
                        onFilterChange = onFilterChange
                    )

                    HomeListVerticalGridScroll(
                        list = listUiState.list,
                        onClickDetail = onClickDetail,
                        modifier = Modifier.padding(20.dp)
                    )

                }

                else -> {}
            }
        }
    }
}

@Composable
@Preview
fun HomeScreenPrev() {
    HomeScreen(
        padding = PaddingValues(0.dp),
        uiState = HomeUiTest,
        onBackClick = {},
        onBookMarClick = { },
        onClickDetail = { },
        navigateMy = { },
        navigateSearch = {},
        onClickList = {},
        onFilterChange = {}
    )
}
