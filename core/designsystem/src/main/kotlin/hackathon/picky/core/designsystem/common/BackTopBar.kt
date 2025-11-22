package hackathon.picky.core.designsystem.common

import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.interaction.MutableInteractionSource
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.widthIn
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.ripple.rememberRipple
import androidx.compose.material3.Icon
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalConfiguration
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextOverflow
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.core.designsystem.R
import hackathon.picky.core.designsystem.theme.Gray50
import hackathon.picky.core.designsystem.theme.Gray800
import hackathon.picky.core.designsystem.theme.PretendardFontFamily

@Composable
fun BackTopBar(
    title: String,
    onClickBack: () -> Unit,
    onClickSearch: (() -> Unit)? = null,
    isGray: Boolean = false,
    padding: PaddingValues? = null
) {
    val configuration = LocalConfiguration.current
    val screenWidth = configuration.screenWidthDp.dp
    val maxTextWidth = screenWidth - 40.dp - 60.dp  // 좌우 아이콘 고려
    val height = padding?.let{52.dp + padding.calculateTopPadding()} ?: 52.dp
    Box(
        modifier = Modifier
            .fillMaxWidth()
            .background(if(isGray)Gray50 else Color.White),
    ) {
        Box(
            modifier = Modifier
                .fillMaxWidth()
                .height(height)
                .padding(horizontal = 20.dp)
        ) {
            // 텍스트 가운데 정렬
            Text(
                text = title,
                fontFamily = PretendardFontFamily,
                fontWeight = FontWeight.Medium,
                fontSize = 16.sp,
                color = Gray800,
                maxLines = 1,
                overflow = TextOverflow.Ellipsis,
                modifier = Modifier
                    .align(Alignment.Center)
                    .widthIn(max = maxTextWidth)
            )

            // 뒤로가기 아이콘 왼쪽 정렬
            Icon(
                painter = painterResource(id = R.drawable.back),
                contentDescription = "back",
                modifier = Modifier
                    .align(Alignment.CenterStart)
                    .clip(RoundedCornerShape(8.dp))
                    .clickable(
                        interactionSource = remember { MutableInteractionSource() },
                        indication = rememberRipple(),
                        onClick = onClickBack
                    )
                    .size(24.dp)
            )

            // 검색 아이콘 오른쪽 정렬
            if (onClickSearch != null) {
                Icon(
                    painter = painterResource(id = R.drawable.search),
                    contentDescription = "search",
                    modifier = Modifier
                        .align(Alignment.CenterEnd)
                        .clip(RoundedCornerShape(8.dp))
                        .clickable(
                            interactionSource = remember { MutableInteractionSource() },
                            indication = rememberRipple(),
                            onClick = { onClickSearch.invoke() }
                        )
                        .size(24.dp)
                )
            }
        }
    }
}



@Composable
@Preview
fun BackTopBarPreview() {
    BackTopBar(
        title = "뒤로가기 테스트",
        onClickBack = {}
    )
}