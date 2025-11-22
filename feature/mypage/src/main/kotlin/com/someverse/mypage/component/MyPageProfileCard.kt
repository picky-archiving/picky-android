package com.someverse.mypage.component

import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.interaction.MutableInteractionSource
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.ripple.rememberRipple
import androidx.compose.material3.Icon
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.core.designsystem.R
import hackathon.picky.core.designsystem.theme.Gray100
import hackathon.picky.core.designsystem.theme.Gray400
import hackathon.picky.core.designsystem.theme.Gray500
import hackathon.picky.core.designsystem.theme.Gray900
import hackathon.picky.core.designsystem.theme.Primary
import hackathon.picky.core.designsystem.theme.PretendardFontFamily
import hackathon.picky.core.designsystem.theme.White

@Composable
internal fun MyPageProfileCard(
    rank: String,
    incomeRange: String,
    onEditClick: () -> Unit,
    modifier: Modifier = Modifier
) {
    Box(
        modifier = modifier
            .fillMaxWidth()
            .height(240.dp)
    ) {
        // 배경 이미지
        Image(
            painter = painterResource(R.drawable.mypage_bg),
            contentDescription = null,
            modifier = Modifier.matchParentSize(),
            contentScale = ContentScale.FillBounds
        )

        // 컨텐츠
        Box(
            modifier = Modifier
                .fillMaxWidth()
                .padding(horizontal = 20.dp)
                .padding(top = 32.dp)
        ) {
            Column(
                modifier = Modifier
                    .fillMaxWidth()
                    .clip(RoundedCornerShape(16.dp))
                    .background(White)
                    .padding(20.dp)
            ) {
                // 현재 소득분위 섹션
                Row(
                    modifier = Modifier.fillMaxWidth(),
                    horizontalArrangement = Arrangement.SpaceBetween,
                    verticalAlignment = Alignment.CenterVertically
                ) {
                    Text(
                        text = "현재 소득분위",
                        fontFamily = PretendardFontFamily,
                        fontSize = 15.sp,
                        fontWeight = FontWeight.SemiBold,
                        color = Gray900
                    )

                    Row(
                        verticalAlignment = Alignment.CenterVertically,
                        modifier = Modifier
                            .clip(RoundedCornerShape(4.dp))
                            .clickable(
                                interactionSource = remember { MutableInteractionSource() },
                                indication = rememberRipple(),
                                onClick = onEditClick
                            )
                            .padding(4.dp)
                    ) {
                        Text(
                            text = "변경하기",
                            fontFamily = PretendardFontFamily,
                            fontSize = 14.sp,
                            fontWeight = FontWeight.Medium,
                            color = Gray400
                        )
                        Icon(
                            painter = painterResource(R.drawable.chevron_right),
                            contentDescription = "변경하기",
                            tint = Gray400,
                            modifier = Modifier.size(16.dp)
                        )
                    }
                }

                Spacer(modifier = Modifier.height(16.dp))

                // 디바이더
                Box(
                    modifier = Modifier
                        .fillMaxWidth()
                        .height(1.dp)
                        .background(Gray100)
                )

                Spacer(modifier = Modifier.height(16.dp))

                // 소득분위 표시
                Row(
                    modifier = Modifier.fillMaxWidth(),
                    horizontalArrangement = Arrangement.SpaceBetween,
                    verticalAlignment = Alignment.Bottom
                ) {
                    Text(
                        text = rank,
                        fontFamily = PretendardFontFamily,
                        fontSize = 18.sp,
                        fontWeight = FontWeight.SemiBold,
                        color = Primary
                    )

                    Text(
                        text = incomeRange,
                        fontFamily = PretendardFontFamily,
                        fontSize = 14.sp,
                        fontWeight = FontWeight.Medium,
                        color = Gray500
                    )
                }
            }
        }
    }
}