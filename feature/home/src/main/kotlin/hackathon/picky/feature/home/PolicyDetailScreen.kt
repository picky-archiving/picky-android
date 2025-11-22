package hackathon.picky.feature.home

import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.aspectRatio
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.verticalScroll
import androidx.compose.material3.Button
import androidx.compose.material3.CenterAlignedTopAppBar
import androidx.compose.material3.CircularProgressIndicator
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.material3.TopAppBarDefaults
import androidx.compose.foundation.layout.WindowInsets
import androidx.compose.foundation.layout.navigationBars
import androidx.compose.foundation.layout.windowInsetsPadding
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextOverflow
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.lifecycle.viewmodel.compose.viewModel
import com.example.core.designsystem.R
import hackathon.picky.core.designsystem.theme.AppColors
import hackathon.picky.core.designsystem.theme.Dimens
import hackathon.picky.feature.home.model.PolicyDetailUiState

@Composable
fun PolicyDetailRoute(
    onBackClick: () -> Unit,
    viewModel: PolicyDetailViewModel = viewModel()
) {
    val uiState by viewModel.uiState.collectAsState()

    when (uiState) {
        is PolicyDetailUiState.Loading -> {
            LoadingScreen()
        }

        is PolicyDetailUiState.Success -> {
            PolicyDetailScreen(
                uiState = uiState as PolicyDetailUiState.Success,
                onBackClick = onBackClick,
                onBookmarkClick = { viewModel.toggleBookmark() }
            )
        }

        is PolicyDetailUiState.Error -> {
            ErrorScreen(
                message = (uiState as PolicyDetailUiState.Error).message,
                onBackClick = onBackClick
            )
        }
    }
}

@OptIn(ExperimentalMaterial3Api::class)
@Composable
private fun PolicyDetailScreen(
    uiState: PolicyDetailUiState.Success,
    onBackClick: () -> Unit,
    onBookmarkClick: () -> Unit
) {
    val policyDetail = uiState.policyDetail
    Scaffold(
        topBar = {
            CenterAlignedTopAppBar(
                title = {
                    Text(
                        text = policyDetail.title,
                        fontSize = 17.sp,
                        fontWeight = FontWeight.SemiBold,
                        maxLines = 1,
                        overflow = TextOverflow.Ellipsis,
                        color = AppColors.Black
                    )
                },
                navigationIcon = {
                    IconButton(onClick = onBackClick) {
                        Icon(
                            painter = painterResource(R.drawable.back),
                            contentDescription = "뒤로가기",
                            tint = AppColors.Black
                        )
                    }
                },
                colors = TopAppBarDefaults.centerAlignedTopAppBarColors(
                    containerColor = AppColors.White
                )
            )
        },
        bottomBar = {
            BottomActionBar(
                isBookmarked = uiState.isBookmarked,
                onBookmarkClick = onBookmarkClick
            )
        },
        containerColor = AppColors.White
    ) { paddingValues ->
        Column(
            modifier = Modifier
                .fillMaxSize()
                .padding(paddingValues)
                .verticalScroll(rememberScrollState())
        ) {
            // 이미지 영역 (체크보드 패턴 - 실제로는 이미지가 들어갈 자리)
            Box(
                modifier = Modifier
                    .fillMaxWidth()
                    .aspectRatio(16f / 9f)
                    .background(AppColors.Gray02)
            )

            Spacer(modifier = Modifier.height(Dimens.Space24))

            // 컨텐츠 영역
            Column(
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(horizontal = Dimens.Space20)
            ) {
                // D-13 태그 (박스 형태)
                Box(
                    modifier = Modifier
                        .clip(RoundedCornerShape(20.dp))
                        .background(Color(0x1A3C6CE5))
                        .padding(horizontal = 8.dp, vertical = 4.dp)
                ) {
                    Text(
                        text = "D-${uiState.daysRemaining}",
                        fontSize = 13.sp,
                        fontWeight = FontWeight.Medium,
                        color = Color(0xFF3C6CE5)
                    )
                }
                Spacer(modifier = Modifier.height(Dimens.Space8))

                // 제목
                Text(
                    text = policyDetail.title,
                    fontSize = 22.sp,
                    fontWeight = FontWeight.Bold,
                    color = AppColors.Black,
                    modifier = Modifier.padding(bottom = Dimens.Space4)
                )

                // 주관 부처
                Text(
                    text = policyDetail.department,
                    fontSize = 14.sp,
                    fontWeight = FontWeight.Normal,
                    color = AppColors.Gray07,
                    modifier = Modifier.padding(bottom = Dimens.Space32)
                )

                // 신청 기간
                SectionTitle(title = "신청 기간")
                Spacer(modifier = Modifier.height(Dimens.Space12))
                Text(
                    text = policyDetail.applicationPeriod,
                    fontSize = 16.sp,
                    fontWeight = FontWeight.Medium,
                    color = AppColors.Black,
                    modifier = Modifier.padding(bottom = Dimens.Space32)
                )

                // 신청 자격
                SectionTitle(title = "신청 자격")
                Spacer(modifier = Modifier.height(Dimens.Space12))

                policyDetail.eligibility.forEach { eligibility ->
                    EligibilityChip(text = eligibility)
                    Spacer(modifier = Modifier.height(Dimens.Space8))
                }

                Spacer(modifier = Modifier.height(Dimens.Space24))

                // 정책 설명
                SectionTitle(title = "정책 설명")
                Spacer(modifier = Modifier.height(Dimens.Space12))
                Text(
                    text = policyDetail.description,
                    fontSize = 15.sp,
                    fontWeight = FontWeight.Normal,
                    color = AppColors.Black,
                    lineHeight = 22.sp,
                    modifier = Modifier.padding(bottom = Dimens.Space32)
                )
            }
        }
    }
}

@Composable
private fun SectionTitle(title: String) {
    Text(
        text = title,
        fontSize = 13.sp,
        fontWeight = FontWeight.Medium,
        color = AppColors.Gray07
    )
}

@Composable
private fun EligibilityChip(text: String) {
    Box(
        modifier = Modifier
            .clip(RoundedCornerShape(8.dp))
            .background(AppColors.Gray02)
            .padding(horizontal = 16.dp, vertical = 12.dp)
    ) {
        Text(
            text = text,
            fontSize = 15.sp,
            fontWeight = FontWeight.Normal,
            color = AppColors.Black,
            maxLines = 1,
            overflow = TextOverflow.Ellipsis
        )
    }
}

@Composable
private fun LoadingScreen() {
    Box(
        modifier = Modifier
            .fillMaxSize()
            .background(AppColors.White),
        contentAlignment = Alignment.Center
    ) {
        CircularProgressIndicator(
            color = Color(0xFF3C6CE5)
        )
    }
}

@Composable
private fun ErrorScreen(
    message: String,
    onBackClick: () -> Unit
) {
    Box(
        modifier = Modifier
            .fillMaxSize()
            .background(AppColors.White),
        contentAlignment = Alignment.Center
    ) {
        Column(
            horizontalAlignment = Alignment.CenterHorizontally
        ) {
            Text(
                text = "오류가 발생했습니다",
                fontSize = 18.sp,
                fontWeight = FontWeight.Bold,
                color = AppColors.Black
            )
            Spacer(modifier = Modifier.height(8.dp))
            Text(
                text = message,
                fontSize = 14.sp,
                color = AppColors.Gray07
            )
            Spacer(modifier = Modifier.height(24.dp))
            Button(onClick = onBackClick) {
                Text("돌아가기")
            }
        }
    }
}

@Composable
private fun BottomActionBar(
    isBookmarked: Boolean,
    onBookmarkClick: () -> Unit
) {
    Row(
        modifier = Modifier
            .fillMaxWidth()
            .background(AppColors.White)
            .windowInsetsPadding(WindowInsets.navigationBars)
            .padding(start = 20.dp, end = 20.dp, top = 8.dp, bottom = 14.dp),
        horizontalArrangement = Arrangement.spacedBy(12.dp),
        verticalAlignment = Alignment.CenterVertically
    ) {
        // 북마크 버튼
        Box(
            modifier = Modifier
                .size(48.dp)
                .border(
                    width = 1.dp,
                    color = AppColors.Gray04,
                    shape = RoundedCornerShape(8.dp)
                )
                .clip(RoundedCornerShape(8.dp))
                .clickable { onBookmarkClick() },
            contentAlignment = Alignment.Center
        ) {
            Icon(
                painter = painterResource(
                    if (isBookmarked) R.drawable.bookmark_selected
                    else R.drawable.bookmark_unselected
                ),
                contentDescription = if (isBookmarked) "북마크 해제" else "북마크",
                tint = Color.Unspecified,
                modifier = Modifier.size(24.dp)
            )
        }

        // 신청하기 버튼
        Box(
            modifier = Modifier
                .weight(1f)
                .height(48.dp)
                .clip(RoundedCornerShape(8.dp))
                .background(Color(0xFF3C6CE5))
                .clickable { /* TODO: 신청하기 동작 */ },
            contentAlignment = Alignment.Center
        ) {
            Text(
                text = "신청하기",
                fontSize = 16.sp,
                fontWeight = FontWeight.SemiBold,
                color = Color.White
            )
        }
    }
}
