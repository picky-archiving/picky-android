package hackathon.picky.feature.home.component

import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.interaction.MutableInteractionSource
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.ripple.rememberRipple
import androidx.compose.material3.Icon
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.graphicsLayer
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import coil.compose.AsyncImage
import com.example.core.designsystem.R
import com.google.accompanist.pager.HorizontalPager
import com.google.accompanist.pager.rememberPagerState
import hackathon.picky.core.designsystem.theme.AppColors.White
import hackathon.picky.core.designsystem.theme.Gray100
import hackathon.picky.core.designsystem.theme.Gray400
import hackathon.picky.core.designsystem.theme.Gray900
import hackathon.picky.core.designsystem.theme.PretendardFontFamily
import hackathon.picky.core.model.common.Category
import hackathon.picky.core.model.common.CommonListItem
import hackathon.picky.feature.home.model.HomeUiTest

@Composable
fun HomeTopBanner(
    listItem: List<CommonListItem>,
    onClickDetail: (Int) -> Unit,
    modifier: Modifier = Modifier,
    onClickList: (Category) -> Unit
) {
    Column(
        modifier = modifier
            .fillMaxWidth()
    ) {
        Row(
            verticalAlignment = Alignment.CenterVertically,
            modifier = Modifier.fillMaxWidth()
        ) {
            Image(
                painter = painterResource(R.drawable.ic_fire),
                contentDescription = null,
                modifier = Modifier
                    .size(20.dp)
                    .clip(RoundedCornerShape(8.dp))
            )

            Spacer(modifier = Modifier.width(8.dp))

            Text(
                text = Category.TOP.label,
                fontFamily = PretendardFontFamily,
                fontSize = 18.sp,
                fontWeight = FontWeight.SemiBold,
                color = Gray900,
                modifier = Modifier.weight(1f) // 왼쪽 정렬, 오른쪽 더보기는 끝으로
            )

            // 오른쪽 더보기 + 화살표
            Row(
                modifier = Modifier.clickable(
                    interactionSource = remember { MutableInteractionSource() },
                    indication = rememberRipple(),
                    onClick = { onClickList(Category.TOP) }
                ),
                verticalAlignment = Alignment.CenterVertically) {
                Text(
                    text = "더보기",
                    fontFamily = PretendardFontFamily,
                    fontSize = 14.sp,
                    fontWeight = FontWeight.Medium,
                    color = Gray400
                )
                Spacer(modifier = Modifier.width(4.dp))
                Icon(
                    painter = painterResource(id = R.drawable.back),
                    contentDescription = "rightArrow",
                    tint = Gray400,
                    modifier = Modifier
                        .size(16.dp)
                        .graphicsLayer {
                            scaleX = -1f // 좌우 반전
                        },
                )
            }
        }

        Spacer(modifier = Modifier.height(12.dp))

        BannerCarousel(
            listItem,
            onClickDetail = onClickDetail
        )
    }
}

@Composable
fun BannerCarousel(
    listItem: List<CommonListItem>, // 로컬 이미지 리스트
    onClickDetail: (Int) -> Unit,
    modifier: Modifier = Modifier
) {
    val pagerState = rememberPagerState()

    Box(
        modifier = modifier
            .fillMaxWidth()
            .height(180.dp) // 배너 높이
    ) {
        // HorizontalPager
        HorizontalPager(
            count = listItem.size,
            state = pagerState,
            modifier = Modifier.fillMaxSize()
        ) { page ->
            AsyncImage(
                model = listItem[page].imageUrl,
                contentDescription = null,
                contentScale = ContentScale.Crop,
                modifier = Modifier
                    .fillMaxSize()
                    .clip(RoundedCornerShape(12.dp))
                    .clickable(
                        interactionSource = remember { MutableInteractionSource() },
                        indication = rememberRipple(), // Material3 ripple
                        onClick = { onClickDetail(listItem[page].id) }
                    )
            )
        }

        // 인디케이터: 배너 내부 아래쪽 중앙
        Row(
            horizontalArrangement = Arrangement.Center,
            verticalAlignment = Alignment.CenterVertically,
            modifier = Modifier
                .align(Alignment.BottomCenter)
                .padding(bottom = 8.dp)
        ) {
            repeat(listItem.size) { index ->
                Box(
                    modifier = Modifier
                        .padding(horizontal = 2.dp)
                        .size(if (pagerState.currentPage == index) 8.dp else 6.dp)
                        .background(
                            color = if (pagerState.currentPage == index) White else Gray100,
                            shape = RoundedCornerShape(50)
                        )
                )
            }
        }
    }
}


@Composable
@Preview
fun HomeTopBannerPrev() {
    HomeTopBanner(
        listItem = HomeUiTest.infoSectionList[0].infoList,
        onClickDetail = { },
        onClickList = {}
    )
}