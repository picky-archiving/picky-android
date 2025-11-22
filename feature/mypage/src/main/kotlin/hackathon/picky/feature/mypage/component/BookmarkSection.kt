package hackathon.picky.feature.mypage.component

import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.material3.Icon
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.core.designsystem.R
import hackathon.picky.core.designsystem.common.CommonListItemBox
import hackathon.picky.core.designsystem.common.CommonListType
import hackathon.picky.core.designsystem.theme.Gray500
import hackathon.picky.core.designsystem.theme.Gray900
import hackathon.picky.core.designsystem.theme.PretendardFontFamily
import hackathon.picky.core.designsystem.theme.Primary
import hackathon.picky.core.model.common.CommonListItem

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

        // 북마크가 없을 때 빈 상태 표시
        if (bookmarkedPolicies.isEmpty()) {
            EmptyBookmarkState()
        } else {
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
}

@Composable
private fun EmptyBookmarkState(
    modifier: Modifier = Modifier
) {
    Box(
        modifier = modifier
            .fillMaxWidth()
            .padding(vertical = 80.dp),
        contentAlignment = Alignment.Center
    ) {
        Column(
            horizontalAlignment = Alignment.CenterHorizontally,
            verticalArrangement = Arrangement.Center
        ) {
            // 아이콘
            Image(
                painter = painterResource(R.drawable.no_data),
                contentDescription = null,
                modifier = Modifier.size(64.dp)
            )

            Spacer(modifier = Modifier.height(16.dp))

            // 안내 메시지
            Text(
                text = "정책을 저장하고\n나중에 다시 확인해보세요!",
                fontFamily = PretendardFontFamily,
                fontSize = 15.sp,
                fontWeight = FontWeight.Normal,
                color = Gray500,
                textAlign = TextAlign.Center,
                lineHeight = 22.sp
            )
        }
    }
}
