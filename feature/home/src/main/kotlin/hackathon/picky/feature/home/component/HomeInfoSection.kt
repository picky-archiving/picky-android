package hackathon.picky.feature.home.component

import androidx.compose.foundation.Image
import androidx.compose.foundation.clickable
import androidx.compose.foundation.interaction.MutableInteractionSource
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.lazy.LazyRow
import androidx.compose.foundation.lazy.items
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
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.core.designsystem.R
import hackathon.picky.core.designsystem.common.CommonListItemBox
import hackathon.picky.core.designsystem.common.CommonListType
import hackathon.picky.core.designsystem.theme.Gray400
import hackathon.picky.core.designsystem.theme.Gray900
import hackathon.picky.core.designsystem.theme.PretendardFontFamily
import hackathon.picky.core.model.Category
import hackathon.picky.core.model.CommonListItem
import hackathon.picky.feature.home.model.HomeUiTest
import java.time.LocalDateTime

@Composable
fun HomeInfoSection(
    category: Category? = null,
    description: String? = null,
    list: List<CommonListItem>,
    onClickDetail: (Int) -> Unit,
    onClickList: (Category) -> Unit,
    modifier: Modifier = Modifier
) {
    Column(
        modifier = modifier
            .fillMaxWidth()
    ) {
        // 타이틀 영역
        if (category != null && description != null) {
            Row(
                verticalAlignment = Alignment.CenterVertically,
                modifier = Modifier.fillMaxWidth()
            ) {
                Image(
                    painter = painterResource(category.iconResId),
                    contentDescription = null,
                    modifier = Modifier
                        .size(20.dp)
                        .clip(RoundedCornerShape(8.dp))
                )

                Spacer(modifier = Modifier.width(8.dp))


                Text(
                    text = category.label,
                    fontFamily = PretendardFontFamily,
                    fontWeight = FontWeight.SemiBold,
                    fontSize = 18.sp,
                    color = Gray900,
                    modifier = Modifier.weight(1f)
                )

                // 오른쪽 더보기 + 화살표
                Row(
                    modifier = Modifier.clickable(
                        interactionSource = remember { MutableInteractionSource() },
                        indication = rememberRipple(),
                        onClick = { onClickList(category) }
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

            Spacer(modifier = Modifier.height(4.dp))

            Text(
                text = description,
                fontFamily = PretendardFontFamily,
                fontSize = 14.sp,
                fontWeight = FontWeight.Normal,
                color = Gray400 // 진한 회색
            )

            Spacer(modifier = Modifier.height(20.dp))
        }


        LazyRow(
            modifier = Modifier.fillMaxWidth(),
            horizontalArrangement = Arrangement.spacedBy(12.dp)
        ) {
            items(list) { item ->
                CommonListItemBox(
                    type = CommonListType.FIX,
                    imageRes = item.imageRes,
                    title = item.title,
                    onClickDetail = onClickDetail,
                    closingDate = item.closingDate,
                    id = item.id
                )
            }
        }
    }
}


@Composable
@Preview
fun HomeInfoSectionPrev() {
    HomeInfoSection(
        category = Category.EMPLOYMENT,
        description = "This is an example description for the info section.",
        list = HomeUiTest.infoSectionList[0].infoList,
        onClickDetail = { },
        onClickList = { },
    )
}

@Composable
@Preview
fun CommonListItemBoxPrev() {
    CommonListItemBox(
        imageRes = R.drawable.bookmark_selected,
        title = "exampleasasasasasasas",
        onClickDetail = { },
        closingDate = LocalDateTime.now(),
        id = 1,
    )
}
