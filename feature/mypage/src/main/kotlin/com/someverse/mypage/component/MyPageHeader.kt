package com.someverse.mypage.component

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.core.designsystem.R
import hackathon.picky.core.designsystem.theme.Gray50
import hackathon.picky.core.designsystem.theme.Gray800
import hackathon.picky.core.designsystem.theme.Gray900
import hackathon.picky.core.designsystem.theme.PretendardFontFamily

@Composable
internal fun MyPageHeader(
    onSearchClick: () -> Unit,
    modifier: Modifier = Modifier
) {
    Row(
        modifier = modifier
            .fillMaxWidth()
            .background(Gray50)
            .padding(horizontal = 20.dp, vertical = 16.dp),
        verticalAlignment = Alignment.CenterVertically
    ) {
        Text(
            text = "마이페이지",
            fontFamily = PretendardFontFamily,
            fontSize = 20.sp,
            fontWeight = FontWeight.SemiBold,
            color = Gray900
        )

        Spacer(modifier = Modifier.weight(1f))

        IconButton(
            onClick = onSearchClick,
            modifier = Modifier.size(24.dp)
        ) {
            Icon(
                painter = painterResource(R.drawable.search),
                contentDescription = "검색",
                tint = Gray800
            )
        }
    }
}