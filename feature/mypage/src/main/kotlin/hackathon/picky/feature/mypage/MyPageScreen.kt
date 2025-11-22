package hackathon.picky.feature.mypage

import androidx.activity.compose.BackHandler
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.verticalScroll
import androidx.compose.material3.CircularProgressIndicator
import androidx.compose.material3.SnackbarHost
import androidx.compose.material3.SnackbarHostState
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.getValue
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.lifecycle.Lifecycle
import androidx.lifecycle.LifecycleEventObserver
import androidx.lifecycle.compose.LocalLifecycleOwner
import androidx.lifecycle.compose.collectAsStateWithLifecycle
import hackathon.picky.core.designsystem.common.BackTopBar
import hackathon.picky.core.designsystem.common.PickySnackbar
import hackathon.picky.core.designsystem.theme.AppColors
import hackathon.picky.core.designsystem.theme.Gray50
import hackathon.picky.core.designsystem.theme.Primary
import hackathon.picky.core.model.common.CommonListItemTest
import hackathon.picky.feature.mypage.component.BookmarkSection
import hackathon.picky.feature.mypage.component.MyPageProfileCard
import hackathon.picky.feature.mypage.component.RankSelectionBottomSheet
import hackathon.picky.feature.mypage.model.MyPageUiState

@Composable
fun MyPageRoute(
    padding: PaddingValues,
    onBackClick: () -> Unit,
    viewModel: MyPageViewModel = hiltViewModel(),
    onSearchClick: () -> Unit,
    navigateDetail: (Int) -> Unit
) {
    val uiState by viewModel.uiState.collectAsStateWithLifecycle()

    val handleBackClick: () -> Unit = {
        when (uiState) {
            else -> {
                // Main 상태에서는 Home으로 이동
                onBackClick()
            }
        }
    }

    val lifecycle = LocalLifecycleOwner.current.lifecycle

    LaunchedEffect(Unit) {
        lifecycle.addObserver(
            LifecycleEventObserver { _, event ->
                if (event == Lifecycle.Event.ON_RESUME) {
                    viewModel.refresh()
                }
            }
        )
    }

    BackHandler(enabled = true) {
        handleBackClick()
    }

    MyPageScreen(
        padding = padding,
        uiState = uiState,
        onBackClick = handleBackClick,
        onSearchClick = onSearchClick,
        onEditClick = viewModel::onEditClick,
        onClickDetail = navigateDetail,
        onDismissBottomSheet = viewModel::dismissBottomSheet,
        onRankSelected = viewModel::updateRank,
        onClearError = viewModel::clearErrorMessage
    )
}

@Composable
private fun MyPageScreen(
    padding: PaddingValues,
    uiState: MyPageUiState,
    onBackClick: () -> Unit,
    onSearchClick: () -> Unit,
    onEditClick: () -> Unit,
    onClickDetail: (Int) -> Unit,
    onDismissBottomSheet: () -> Unit,
    onRankSelected: (String) -> Unit,
    onClearError: () -> Unit
) {
    val scrollState = rememberScrollState()
    val snackbarHostState = remember { SnackbarHostState() }

    // 에러 메시지 표시
    if (uiState is MyPageUiState.Main && uiState.errorMessage != null) {
        LaunchedEffect(uiState.errorMessage) {
            snackbarHostState.showSnackbar(uiState.errorMessage)
            onClearError()
        }
    }
    Box(modifier = Modifier.fillMaxSize().background(AppColors.White)) {
        Box(
            modifier = Modifier
                .fillMaxWidth()
                .height(padding.calculateTopPadding())
                .background(Gray50)
        )

        Box(
            modifier = Modifier
                .fillMaxSize()
//                .background(AppColors.White)
                .padding(padding)
        ) {
            Column(
                modifier = Modifier.fillMaxSize()
            ) {
                when (uiState) {
                    is MyPageUiState.Main -> {
                        // 헤더
                        BackTopBar(
                            title = "마이페이지",
                            onClickBack = onBackClick,
                            onClickSearch = onSearchClick,
                            isGray = true
                        )

                        // 로딩 상태 표시
                        if (uiState.isLoading) {
                            Box(
                                modifier = Modifier.fillMaxSize(),
                                contentAlignment = Alignment.Center
                            ) {
                                CircularProgressIndicator(
                                    color = Primary
                                )
                            }
                        } else {
                            // 스크롤 가능한 컨텐츠
                            Column(
                                modifier = Modifier
                                    .fillMaxSize()
                                    .verticalScroll(scrollState)
                            ) {
                                // 프로필 카드
                                MyPageProfileCard(
                                    rank = uiState.rank,
                                    incomeRange = uiState.incomeRange,
                                    onEditClick = onEditClick
                                )

                                Spacer(modifier = Modifier.height(32.dp))

                                // 북마크 리스트
                                BookmarkSection(
                                    bookmarkedPolicies = uiState.bookmarkedPolicies,
                                    onClickDetail = onClickDetail
                                )
                            }
                        }

                        // 바텀시트
                        if (uiState.showRankBottomSheet) {
                            RankSelectionBottomSheet(
                                currentRank = uiState.rank,
                                onDismiss = onDismissBottomSheet,
                                onRankSelected = onRankSelected
                            )
                        }
                    }

                    else -> {}
                }
            }

            // Snackbar
            SnackbarHost(
                hostState = snackbarHostState,
                modifier = Modifier
                    .align(Alignment.BottomCenter)
                    .padding(bottom = 16.dp)
            ) { snackbarData ->
                PickySnackbar(
                    message = snackbarData.visuals.message,
                    isError = true
                )
            }
        }
    }
}

@Preview(showBackground = true)
@Composable
private fun MyPageScreenPreview() {
    MyPageScreen(
        padding = PaddingValues(0.dp),
        uiState = previewMyPageUiState,
        onBackClick = {},
        onSearchClick = {},
        onEditClick = {},
        onClickDetail = { _ -> },
        onDismissBottomSheet = {},
        onRankSelected = {},
        onClearError = {}
    )
}

private val previewMyPageUiState = MyPageUiState.Main(
    rank = "3분위",
    showRankBottomSheet = false,
    bookmarkedPolicies = listOf(
        CommonListItemTest,
        CommonListItemTest,
        CommonListItemTest,
        CommonListItemTest,
        CommonListItemTest,

    )
)
