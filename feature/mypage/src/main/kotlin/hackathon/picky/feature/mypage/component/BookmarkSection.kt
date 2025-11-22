package hackathon.picky.feature.mypage.component

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Icon
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.core.designsystem.R
import hackathon.picky.core.designsystem.common.CommonListItemBox
import hackathon.picky.core.designsystem.common.CommonListType
import hackathon.picky.core.designsystem.theme.Gray900
import hackathon.picky.core.designsystem.theme.PretendardFontFamily
import hackathon.picky.core.designsystem.theme.Primary
import hackathon.picky.core.model.CommonListItem

@Composable
internal fun BookmarkSection(
    bookmarkedPolicies: List<CommonListItem>,
    onClickDetail: (Int) -> Unit,
    modifier: Modifier = Modifier
) {
    Column(
        modifier = modifier
            .fillMaxWidth()
            .padding(horizontal = 20.dp)
    ) {
        // 섹션 헤더
        Row(
            verticalAlignment = Alignment.CenterVertically,
            modifier = Modifier.fillMaxWidth()
        ) {
            Icon(
                painter = painterResource(R.drawable.bookmark),
                contentDescription = null,
                tint = Primary,
                modifier = Modifier.padding(end = 8.dp)
            )
            Text(
                text = "북마크 리스트",
                fontFamily = PretendardFontFamily,
                fontSize = 18.sp,
                fontWeight = FontWeight.SemiBold,
                color = Gray900
            )
        }

        Spacer(modifier = Modifier.height(16.dp))

        // 그리드 리스트 (2열)
        Column(
            verticalArrangement = Arrangement.spacedBy(24.dp)
        ) {
            bookmarkedPolicies.chunked(2).forEach { rowItems ->
                Row(
                    horizontalArrangement = Arrangement.spacedBy(12.dp),
                    modifier = Modifier.fillMaxWidth()
                ) {
                    rowItems.forEach { item ->
                        CommonListItemBox(
                            id = item.id,
                            imageUrl = item.imageUrl,
                            title = item.title,
                            closingDate = item.closingDate,
                            onClickDetail = onClickDetail,
                            type = CommonListType.DYNAMIC
                        )
                    }
                }
            }
        }
    }
}
