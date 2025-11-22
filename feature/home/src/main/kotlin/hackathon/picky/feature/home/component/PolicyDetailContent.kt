package hackathon.picky.feature.home.component

import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.clickable
import androidx.compose.foundation.interaction.MutableInteractionSource
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.ExperimentalLayoutApi
import androidx.compose.foundation.layout.FlowRow
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.aspectRatio
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.verticalScroll
import androidx.compose.material.ripple.rememberRipple
import androidx.compose.material3.Button
import androidx.compose.material3.CircularProgressIndicator
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.remember

import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextOverflow
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.core.designsystem.R
import hackathon.picky.core.designsystem.common.BackTopBar
import hackathon.picky.core.designsystem.common.PickySnackbar
import kotlinx.coroutines.launch
import hackathon.picky.core.designsystem.theme.AppColors
import hackathon.picky.core.designsystem.theme.Dimens
import hackathon.picky.core.designsystem.theme.Gray100
import hackathon.picky.core.designsystem.theme.Gray400
import hackathon.picky.core.designsystem.theme.Gray500
import hackathon.picky.core.designsystem.theme.Gray800
import hackathon.picky.core.designsystem.theme.PretendardFontFamily
import hackathon.picky.core.designsystem.theme.Primary
import hackathon.picky.core.designsystem.theme.Secondary
import hackathon.picky.feature.home.model.HomeUiState
import hackathon.picky.feature.home.model.PolicyDetail


@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun PolicyDetailContent(
    uiState: HomeUiState.Detail,
    onBackClick: () -> Unit,
    onBookmarkClick: () -> Unit
) {
    val policyDetail = uiState.policyDetail
    Column(
    ) {
        BackTopBar(
            title = policyDetail.title,
            onClickBack = onBackClick
        )
        Column(
            modifier = Modifier
                .weight(1f)
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
                    .weight(1f)
                    .padding(horizontal = Dimens.Space20)
            ) {
                // D-13 태그 (박스 형태)
                Box(
                    modifier = Modifier
                        .clip(RoundedCornerShape(20.dp))
                        .background(Secondary)
                        .padding(horizontal = 8.dp, vertical = 4.dp)
                ) {
                    Text(
                        text = "D${uiState.daysRemaining}",
                        fontFamily = PretendardFontFamily,
                        fontSize = 12.sp,
                        fontWeight = FontWeight.Medium,
                        color = Primary
                    )
                }
                Spacer(modifier = Modifier.height(Dimens.Space8))

                // 제목
                Text(
                    text = policyDetail.title,
                    fontFamily = PretendardFontFamily,
                    fontSize = 18.sp,
                    fontWeight = FontWeight.Medium,
                    color = Gray800,
                    modifier = Modifier.padding(bottom = Dimens.Space6)
                )

                // 주관 부처
                Text(
                    text = policyDetail.department,
                    fontFamily = PretendardFontFamily,
                    fontSize = 15.sp,
                    fontWeight = FontWeight.Normal,
                    color = Gray500,
                    modifier = Modifier.padding(bottom = Dimens.Space20)
                )

                // 디바이더
                Box(
                    modifier = Modifier
                        .fillMaxWidth()
                        .padding(horizontal = 20.dp)
                        .height(1.dp)
                        .background(Gray100)
                        .padding(bottom = Dimens.Space20)
                )

                // 신청 기간
                SectionTitle(title = "신청 기간")
                Spacer(modifier = Modifier.height(Dimens.Space10))
                Text(
                    text = policyDetail.applicationPeriod,
                    fontFamily = PretendardFontFamily,
                    fontSize = 15.sp,
                    fontWeight = FontWeight.Normal,
                    color = Gray800,
                    modifier = Modifier.padding(bottom = Dimens.Space40)
                )

                // 신청 자격
                SectionTitle(title = "신청 자격")
                Spacer(modifier = Modifier.height(Dimens.Space10))

                EligibilityChipGroup(eligibility = policyDetail.eligibility)

                Spacer(modifier = Modifier.height(Dimens.Space40))

                // 정책 설명
                SectionTitle(title = "정책 설명")
                Spacer(modifier = Modifier.height(Dimens.Space10))
                Box(
                    modifier = Modifier
                        .fillMaxWidth()
                        .clip(RoundedCornerShape(8.dp))
                        .background(Gray100)
                        .padding(16.dp)
                ) {
                    Text(
                        text = policyDetail.description,
                        fontSize = 15.sp,
                        fontWeight = FontWeight.Normal,
                        color = AppColors.Black,
                        lineHeight = 22.sp
                    )
                }
                Spacer(modifier = Modifier.height(Dimens.Space48))
            }
        }

        BottomActionBar(
            isBookmarked = uiState.isBookmarked,
            onBookmarkClick = onBookmarkClick
        )
    }
}

@Composable
private fun SectionTitle(title: String) {
    Text(
        text = title,
        fontFamily = PretendardFontFamily,
        fontSize = 14.sp,
        fontWeight = FontWeight.Medium,
        color = Gray400
    )
}

@OptIn(ExperimentalLayoutApi::class)
@Composable
private fun EligibilityChipGroup(eligibility: List<String>) {
    FlowRow(
        modifier = Modifier.fillMaxWidth(),
        horizontalArrangement = Arrangement.spacedBy(8.dp),
        verticalArrangement = Arrangement.spacedBy(8.dp)
    ) {
        eligibility.forEach { text ->
            EligibilityChip(text = text)
        }
    }
}

@Composable
private fun EligibilityChip(text: String) {
    Box(
        modifier = Modifier
            .clip(RoundedCornerShape(24.dp))
            .background(Gray100)
            .padding(horizontal = 12.dp, vertical = 4.dp)
    ) {
        Text(
            text = text,
            fontFamily = PretendardFontFamily,
            fontSize = 14.sp,
            fontWeight = FontWeight.Normal,
            color = Gray800,
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
            color = Primary
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
                .clickable(
                    interactionSource = remember { MutableInteractionSource() },
                    indication = rememberRipple(),
                    onClick = { onBookmarkClick() }
                )
                .background(Gray100),
            contentAlignment = Alignment.Center
        ) {
            Icon(
                painter = painterResource(
                    if (isBookmarked) R.drawable.bookmark_selected
                    else R.drawable.bookmark_unselected
                ),
                contentDescription = if (isBookmarked) "북마크 해제" else "북마크",
                tint = Primary,
                modifier = Modifier.size(24.dp)
            )
        }

        // 신청하기 버튼
        Box(
            modifier = Modifier
                .weight(1f)
                .height(48.dp)
                .clip(RoundedCornerShape(8.dp))
                .clickable(
                    interactionSource = remember { MutableInteractionSource() },
                    indication = rememberRipple(),
                    onClick = { }
                )
                .background(Primary),
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

// Preview 데이터
private val previewPolicyDetail = PolicyDetail(
    id = "1",
    title = "청년 내일채움공제",
    department = "고용노동부",
    applicationPeriod = "2024.01.01 ~ 2024.12.31",
    eligibility = listOf(
        "만 15세 ~ 34세 청년",
        "중소·중견기업 정규직 재직자",
        "근속기간 6개월 미만"
    ),
    description = "청년이 중소·중견기업에서 장기간 근속할 수 있도록 지원하는 제도입니다. " +
            "청년과 기업이 공동으로 적립한 금액에 정부가 지원금을 더해 2년 또는 3년 후 목돈을 마련할 수 있도록 돕습니다. " +
            "청년의 자산형성과 중소기업의 인력 안정화를 동시에 지원하는 정책입니다."
)

private val previewUiState = HomeUiState.Detail(
    policyDetail = previewPolicyDetail,
    daysRemaining = 13,
    isBookmarked = false,
    previousUiState = HomeUiState.Init,
)

@Preview(showBackground = true)
@Composable
private fun PolicyDetailContentPreview() {
    PolicyDetailContent(
        uiState = previewUiState,
        onBackClick = {},
        onBookmarkClick = {}
    )
}

@Preview(showBackground = true)
@Composable
private fun PolicyDetailContentBookmarkedPreview() {
    PolicyDetailContent(
        uiState = previewUiState.copy(isBookmarked = true),
        onBackClick = {},
        onBookmarkClick = {}
    )
}

@Preview(showBackground = true)
@Composable
private fun LoadingScreenPreview() {
    LoadingScreen()
}

@Preview(showBackground = true)
@Composable
private fun ErrorScreenPreview() {
    ErrorScreen(
        message = "네트워크 오류가 발생했습니다.",
        onBackClick = {}
    )
}