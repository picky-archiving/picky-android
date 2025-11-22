package hackathon.picky.feature.mypage

import android.R
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
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.lifecycle.compose.collectAsStateWithLifecycle
import hackathon.picky.feature.home.component.PolicyDetailContent
import hackathon.picky.feature.home.model.HomeUiState
import hackathon.picky.feature.mypage.component.BookmarkSection
import hackathon.picky.feature.mypage.component.MyPageHeader
import hackathon.picky.feature.mypage.component.MyPageProfileCard
import hackathon.picky.feature.mypage.model.BookmarkItem
import hackathon.picky.feature.mypage.model.MyPageUiState
import hackathon.picky.core.designsystem.theme.AppColors
import java.time.LocalDateTime

@Composable
fun MyPageRoute(
    padding: PaddingValues,
    onBackClick: () -> Unit,
    viewModel: MyPageViewModel = hiltViewModel()
) {
    val uiState by viewModel.uiState.collectAsStateWithLifecycle()

    val handleBackClick: () -> Unit = {
        when (uiState) {
            is MyPageUiState.Detail -> {
                // Detail 상태에서는 Main으로 돌아감
                viewModel.goBackToMain()
            }

            else -> {
                // Main 상태에서는 Home으로 이동
                onBackClick()
            }
        }
    }

    BackHandler(enabled = true) {
        handleBackClick()
    }

    MyPageScreen(
        padding = padding,
        uiState = uiState,
        onBackClick = handleBackClick,
        onSearchClick = { /* TODO */ },
        onEditClick = viewModel::onEditClick,
        onClickDetail = viewModel::clickDetail,
        onBookmarkClick = viewModel::toggleBookmark,
        onDismissBottomSheet = viewModel::dismissBottomSheet,
        onRankSelected = viewModel::updateRank
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
    onBookmarkClick: () -> Unit,
    onDismissBottomSheet: () -> Unit,
    onRankSelected: (String) -> Unit
) {
    val scrollState = rememberScrollState()

    Column(
        modifier = Modifier
            .fillMaxSize()
            .background(AppColors.White)
            .padding(padding)
    ) {
        when (uiState) {
            is MyPageUiState.Main -> {
                // 헤더
                MyPageHeader(
                    onBackClick = onBackClick,
                    onSearchClick = onSearchClick
                )

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

                // 바텀시트
                if (uiState.showRankBottomSheet) {
                    hackathon.picky.feature.mypage.component.RankSelectionBottomSheet(
                        currentRank = uiState.rank,
                        onDismiss = onDismissBottomSheet,
                        onRankSelected = onRankSelected
                    )
                }
            }

            is MyPageUiState.Detail -> {
                // PolicyDetailContent를 HomeUiState.Detail 형태로 변환
                val homeDetailState = HomeUiState.Detail(
                    previousUiState = HomeUiState.Init,
                    policyDetail = uiState.policyDetail,
                    daysRemaining = uiState.daysRemaining,
                    isBookmarked = uiState.isBookmarked
                )

                PolicyDetailContent(
                    uiState = homeDetailState,
                    onBackClick = onBackClick,
                    onBookmarkClick = onBookmarkClick
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
        onBookmarkClick = {},
        onDismissBottomSheet = {},
        onRankSelected = {}
    )
}

private val previewMyPageUiState = MyPageUiState.Main(
    rank = "3분위",
    showRankBottomSheet = false,
    bookmarkedPolicies = listOf(
        BookmarkItem(
            id = 1,
            title = "정책 관련 타이틀이 들어갑니다. 2줄 이상 예시입니...",
            imageRes = R.drawable.ic_menu_camera,
            closingDate = LocalDateTime.now().plusDays(13)
        ),
        BookmarkItem(
            id = 2,
            title = "정책 관련 타이틀이 들어갑니다. 2줄 이상 예시입니...",
            imageRes = android.R.drawable.ic_menu_compass,
            closingDate = java.time.LocalDateTime.now().plusDays(13)
        ),
        BookmarkItem(
            id = 3,
            title = "정책 관련 타이틀이 들어갑니다. 2줄 이상 예시입니...",
            imageRes = android.R.drawable.ic_menu_agenda,
            closingDate = java.time.LocalDateTime.now().plusDays(13)
        ),
        BookmarkItem(
            id = 4,
            title = "정책 관련 타이틀이 들어갑니다. 2줄 이상 예시입니...",
            imageRes = android.R.drawable.ic_menu_camera,
            closingDate = java.time.LocalDateTime.now().plusDays(13)
        )
    )
)
