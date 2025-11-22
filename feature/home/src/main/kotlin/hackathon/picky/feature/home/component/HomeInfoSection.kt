package hackathon.picky.feature.home.component

import android.R
import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.lazy.LazyRow
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.ArrowForward
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import hackathon.picky.core.designsystem.common.CommonListItem
import hackathon.picky.core.designsystem.theme.Gray400
import hackathon.picky.core.model.Category
import hackathon.picky.feature.home.model.HomeListItem
import hackathon.picky.feature.home.model.HomeUiTest
import java.time.LocalDateTime

@Composable
fun HomeInfoSection(
    category: Category,
    description: String,
    list: List<HomeListItem>,
    onClickDetail: () -> Unit,
    modifier: Modifier = Modifier
) {
    Column(
        modifier = modifier
            .fillMaxWidth()
            .padding(12.dp)
    ) {
        // 타이틀 영역
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
                style = MaterialTheme.typography.bodyLarge,
                modifier = Modifier.weight(1f) // 왼쪽 정렬, 오른쪽 더보기는 끝으로
            )

            // 오른쪽 더보기 + 화살표
            Row(verticalAlignment = Alignment.CenterVertically) {
                Text(
                    text = "더보기",
                    style = MaterialTheme.typography.bodyMedium,
                    color = Gray400
                )
                Spacer(modifier = Modifier.width(4.dp))
                Icon(
                    imageVector = Icons.Default.ArrowForward,
                    contentDescription = "rightArrow",
                    tint = Gray400,
                    modifier = Modifier.size(16.dp)
                )
            }
        }

        Spacer(modifier = Modifier.height(4.dp))

        Text(
            text = description,
            style = MaterialTheme.typography.bodyMedium,
            color = Gray400 // 진한 회색
        )

        Spacer(modifier = Modifier.height(20.dp))

        LazyRow(
            modifier = Modifier.fillMaxWidth(),
            horizontalArrangement = Arrangement.spacedBy(12.dp)
        ) {
            items(list) { item ->
                CommonListItem(
                    imageRes = item.imageRes,
                    title = item.title,
                    onClickDetail = onClickDetail,
                    closingDate = item.closingDate
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
    )
}

@Composable
@Preview
fun CommonListItemPrev() {
    CommonListItem(
        imageRes = R.drawable.ic_menu_camera,
        title = "exampleasasasasasasas",
        onClickDetail = { },
        closingDate = LocalDateTime.now()
    )
}
