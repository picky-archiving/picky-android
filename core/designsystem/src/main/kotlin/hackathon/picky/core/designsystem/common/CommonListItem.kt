package hackathon.picky.core.designsystem.common

import androidx.compose.foundation.clickable
import androidx.compose.foundation.interaction.MutableInteractionSource
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.ripple.rememberRipple
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.platform.LocalConfiguration
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextOverflow
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import coil.compose.AsyncImage
import hackathon.picky.core.designsystem.theme.Gray900
import hackathon.picky.core.designsystem.theme.PretendardFontFamily
import java.time.LocalDate
import java.time.LocalDateTime

@Composable
fun CommonListItemBox(
    id: Int,
    imageUrl: String,
    title: String,
    closingDate: LocalDate?,
    onClickDetail: (Int) -> Unit,
    type: CommonListType = CommonListType.FIX
) {

    val size = when (type) {
        CommonListType.FIX -> 128.dp
        CommonListType.DYNAMIC -> (LocalConfiguration.current.screenWidthDp.dp - 52.dp) / 2
    }
    Column(
        modifier = Modifier
            .width(size)
            .clip(RoundedCornerShape(8.dp))
            .clickable(
                interactionSource = remember { MutableInteractionSource() },
                indication = rememberRipple(),
                onClick = { onClickDetail(id) }
            )
    ) {
        Box(
            modifier = Modifier
                .size(size)
                .clip(RoundedCornerShape(8.dp))
        ) {
            AsyncImage(
                model = imageUrl,
                contentDescription = null,
                modifier = Modifier.matchParentSize()
            )

            DateBox(
                modifier = Modifier
                    .align(Alignment.TopStart)
                    .padding(4.dp),
                closingDate = closingDate ?: LocalDate.now(),
                isAll = closingDate == null,
            )
        }

        Spacer(modifier = Modifier.width(12.dp))

        Text(
            text = title,
            fontFamily = PretendardFontFamily,
            fontSize = 15.sp,
            fontWeight = FontWeight.Medium,
            maxLines = 2,
            overflow = TextOverflow.Ellipsis,
            color = Gray900
        )
    }
}

@Composable
@Preview
fun CommonListItemBoxPreview() {
    CommonListItemBox(
        id = 1,
        imageUrl = "https://sosal.kr/1144?pidx=0",
        title = "친환경 제품 사용하고 계신가요?",
        closingDate = LocalDate.now().plusDays(5),
        onClickDetail = {}
    )
}

enum class CommonListType {
    FIX, DYNAMIC
}