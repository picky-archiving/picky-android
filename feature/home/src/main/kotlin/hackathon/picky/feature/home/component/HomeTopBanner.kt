package hackathon.picky.feature.home.component

import android.R
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
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.google.accompanist.pager.HorizontalPager
import com.google.accompanist.pager.rememberPagerState
import hackathon.picky.core.designsystem.theme.AppColors.White
import hackathon.picky.core.designsystem.theme.LightGray

@Composable
fun HomeTopBanner(
    imageList: List<Int>,
    onClickDetail: () -> Unit
) {
    Column(
        modifier = Modifier
            .fillMaxWidth()
    ) {
        Row(

            verticalAlignment = Alignment.CenterVertically,
            modifier = Modifier.fillMaxWidth()
        ) {
            Image(
                painter = painterResource(R.drawable.ic_menu_camera),
                contentDescription = null,
                modifier = Modifier
                    .size(20.dp)
                    .clip(RoundedCornerShape(8.dp))
            )

            Spacer(modifier = Modifier.width(8.dp))

            Text(
                text = "MD PICK 금주의 공고",
                style = MaterialTheme.typography.bodyLarge,
                modifier = Modifier.weight(1f) // 왼쪽 정렬, 오른쪽 더보기는 끝으로
            )
        }

        Spacer(modifier = Modifier.height(12.dp))

        BannerCarousel(
            imageList,
            onClickDetail = onClickDetail
        )
    }
}

@Composable
fun BannerCarousel(
    imageUrls: List<Int>, // 로컬 이미지 리스트
    onClickDetail: () -> Unit,
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
            count = imageUrls.size,
            state = pagerState,
            modifier = Modifier.fillMaxSize()
        ) { page ->

            Image(
                painter = painterResource(imageUrls[page]),
                contentDescription = null,
                contentScale = ContentScale.Crop,
                modifier = Modifier
                    .fillMaxSize()
                    .clip(RoundedCornerShape(12.dp))
                    .clickable(
                        interactionSource = remember { MutableInteractionSource() },
                        indication = rememberRipple(), // Material3 ripple
                        onClick = { onClickDetail() }
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
            repeat(imageUrls.size) { index ->
                Box(
                    modifier = Modifier
                        .padding(horizontal = 2.dp)
                        .size(if (pagerState.currentPage == index) 8.dp else 6.dp)
                        .background(
                            color = if (pagerState.currentPage == index) White else LightGray,
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
        imageList = listOf(
            R.drawable.ic_menu_camera,
            R.drawable.ic_menu_compass,
            R.drawable.ic_menu_gallery
        ),
        onClickDetail = { }
    )
}