package hackathon.picky.feature.home

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.verticalScroll
import androidx.compose.material3.MaterialTheme
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.lifecycle.compose.collectAsStateWithLifecycle
import hackathon.picky.feature.HomeViewModel
import hackathon.picky.feature.home.component.HomeInfoSection
import hackathon.picky.feature.home.component.HomeTopBanner
import hackathon.picky.feature.home.model.HomeUiState
import hackathon.picky.feature.home.model.HomeUiTest

@Composable
internal fun HomeRoute(
    padding: PaddingValues,
    navigateMy: () -> Unit,
    viewModel: HomeViewModel = hiltViewModel()
) {
    val uiState = viewModel.uiState.collectAsStateWithLifecycle()

    HomeScreen(
        padding = padding,
        uiState = uiState.value
    )
}

@Composable
private fun HomeScreen(
    padding: PaddingValues,
    uiState: HomeUiState
) {
    val scrollState = rememberScrollState()

    Column(
        modifier = Modifier
            .fillMaxSize()
            .background(MaterialTheme.colorScheme.background)
            .padding(padding)
            .padding(20.dp)

    ) {
        when (uiState) {
            is HomeUiState.Success -> {
                Column(
                    modifier = Modifier
                        .fillMaxSize()
                        .verticalScroll(scrollState)
                ) {
                    HomeTopBanner(imageList = uiState.topBannerList)

                    Spacer(modifier = Modifier.height(20.dp))
                    (uiState as HomeUiState.Success).infoSectionList.forEach{
                        HomeInfoSection(
                            titleImageRes = it.titleImageRes,
                            title = it.title,
                            description = it.description,
                            list = it.infoList,
                        )
                    }
                }
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
        uiState = HomeUiTest
    )
}
