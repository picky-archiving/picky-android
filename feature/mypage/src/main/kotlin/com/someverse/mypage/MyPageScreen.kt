package com.someverse.mypage

import android.R
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
import com.someverse.mypage.component.BookmarkSection
import com.someverse.mypage.component.MyPageHeader
import com.someverse.mypage.component.MyPageProfileCard
import com.someverse.mypage.model.BookmarkItem
import com.someverse.mypage.model.MyPageUiState
import hackathon.picky.core.designsystem.theme.AppColors
import java.time.LocalDateTime

@Composable
fun MyPageRoute(
    padding: PaddingValues,
    viewModel: MyPageViewModel = hiltViewModel()
) {
    val uiState by viewModel.uiState.collectAsStateWithLifecycle()

    MyPageScreen(
        padding = padding,
        uiState = uiState,
        onSearchClick = { /* TODO */ },
        onEditClick = viewModel::onEditClick,
        onClickDetail = { /* TODO */ },
        onDismissBottomSheet = viewModel::dismissBottomSheet,
        onRankSelected = viewModel::updateRank
    )
}

@Composable
private fun MyPageScreen(
    padding: PaddingValues,
    uiState: MyPageUiState,
    onSearchClick: () -> Unit,
    onEditClick: () -> Unit,
    onClickDetail: () -> Unit,
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
        // 헤더
        MyPageHeader(
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
    }

    // 바텀시트
    if (uiState.showRankBottomSheet) {
        com.someverse.mypage.component.RankSelectionBottomSheet(
            currentRank = uiState.rank,
            onDismiss = onDismissBottomSheet,
            onRankSelected = onRankSelected
        )
    }
}

@Preview(showBackground = true)
@Composable
private fun MyPageScreenPreview() {
    MyPageScreen(
        padding = PaddingValues(0.dp),
        uiState = previewMyPageUiState,
        onSearchClick = {},
        onEditClick = {},
        onClickDetail = {},
        onDismissBottomSheet = {},
        onRankSelected = {}
    )
}

private val previewMyPageUiState = MyPageUiState(
    rank = "3분위",
    showRankBottomSheet = false,
    bookmarkedPolicies = listOf(
        BookmarkItem(
            title = "정책 관련 타이틀이 들어갑니다. 2줄 이상 예시입니...",
            imageRes = R.drawable.ic_menu_camera,
            closingDate = LocalDateTime.now().plusDays(13)
        ),
        BookmarkItem(
            title = "정책 관련 타이틀이 들어갑니다. 2줄 이상 예시입니...",
            imageRes = android.R.drawable.ic_menu_compass,
            closingDate = java.time.LocalDateTime.now().plusDays(13)
        ),
        BookmarkItem(
            title = "정책 관련 타이틀이 들어갑니다. 2줄 이상 예시입니...",
            imageRes = android.R.drawable.ic_menu_agenda,
            closingDate = java.time.LocalDateTime.now().plusDays(13)
        ),
        BookmarkItem(
            title = "정책 관련 타이틀이 들어갑니다. 2줄 이상 예시입니...",
            imageRes = android.R.drawable.ic_menu_camera,
            closingDate = java.time.LocalDateTime.now().plusDays(13)
        )
    )
)