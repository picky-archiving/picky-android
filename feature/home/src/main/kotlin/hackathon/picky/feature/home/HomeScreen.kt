package hackathon.picky.feature.home

import androidx.activity.compose.BackHandler
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.verticalScroll
import androidx.compose.material3.MaterialTheme
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.lifecycle.compose.collectAsStateWithLifecycle
import hackathon.picky.feature.home.component.HomeInfoSection
import hackathon.picky.feature.home.component.HomeTopBanner
import hackathon.picky.feature.home.component.PolicyDetailContent
import hackathon.picky.feature.home.model.HomeUiState
import hackathon.picky.feature.home.model.HomeUiTest

@Composable
internal fun HomeRoute(
    padding: PaddingValues,
    navigateMy: () -> Unit,
    viewModel: HomeViewModel = hiltViewModel()
) {
    val uiState = viewModel.uiState.collectAsStateWithLifecycle()

    val context = LocalContext.current
    BackHandler(enabled = uiState.value is HomeUiState) {
        viewModel.goBack(context)
    }

    HomeScreen(
        padding = padding,
        uiState = uiState.value,
        onBackClick = {},
        onBookMarClick = viewModel::toggleBookmark,
        onClickDetail = viewModel::clickDetail,
    )
}

@Composable
private fun HomeScreen(
    padding: PaddingValues,
    uiState: HomeUiState,
    onClickDetail: () -> Unit,
    onBackClick: () -> Unit,
    onBookMarClick: () -> Unit
) {
    val scrollState = rememberScrollState()

    Column(
        modifier = Modifier
            .fillMaxSize()
            .background(MaterialTheme.colorScheme.background)
            .padding(padding)

    ) {
        when (uiState) {
            is HomeUiState.Main -> {
                Column(
                    modifier = Modifier
                        .fillMaxSize()
                        .padding(20.dp)
                        .verticalScroll(scrollState)
                ) {
                    HomeTopBanner(
                        imageList = uiState.topBannerList,
                        onClickDetail = onClickDetail
                    )

                    Spacer(modifier = Modifier.height(20.dp))
                    (uiState as HomeUiState.Main).infoSectionList.forEach {
                        HomeInfoSection(
                            category = it.category,
                            description = it.description,
                            list = it.infoList,
                            onClickDetail = onClickDetail
                        )
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

            else -> {}
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
    )
}
