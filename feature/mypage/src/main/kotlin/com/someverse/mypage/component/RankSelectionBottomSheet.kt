package com.someverse.mypage.component

import androidx.compose.foundation.clickable
import androidx.compose.foundation.interaction.MutableInteractionSource
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.heightIn
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.verticalScroll
import androidx.compose.material.ripple.rememberRipple
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.ModalBottomSheet
import androidx.compose.material3.Text
import androidx.compose.material3.rememberModalBottomSheetState
import androidx.compose.runtime.Composable
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import hackathon.picky.core.designsystem.theme.Gray800
import hackathon.picky.core.designsystem.theme.Gray900
import hackathon.picky.core.designsystem.theme.Primary
import hackathon.picky.core.designsystem.theme.PretendardFontFamily

@OptIn(ExperimentalMaterial3Api::class)
@Composable
internal fun RankSelectionBottomSheet(
    currentRank: String,
    onDismiss: () -> Unit,
    onRankSelected: (String) -> Unit
) {
    val sheetState = rememberModalBottomSheetState(skipPartiallyExpanded = true)
    val scrollState = rememberScrollState()

    val ranks = listOf(
        "1분위",
        "2분위",
        "3분위",
        "4분위",
        "5분위",
        "6분위",
        "7분위",
        "8분위",
        "9분위",
        "10분위"
    )

    ModalBottomSheet(
        onDismissRequest = onDismiss,
        sheetState = sheetState
    ) {
        Column(
            modifier = Modifier
                .fillMaxWidth()
                .heightIn(max = 540.dp)
                .verticalScroll(scrollState)
                .padding(bottom = 36.dp),
            horizontalAlignment = Alignment.CenterHorizontally
        ) {
            ranks.forEach { rank ->
                RankItem(
                    rank = rank,
                    isSelected = rank == currentRank,
                    onClick = {
                        onRankSelected(rank)
                        onDismiss()
                    }
                )
            }
        }
    }
}

@Composable
private fun RankItem(
    rank: String,
    isSelected: Boolean,
    onClick: () -> Unit
) {
    Text(
        text = rank,
        fontFamily = PretendardFontFamily,
        fontSize = 16.sp,
        fontWeight = FontWeight.SemiBold,
        color = if (isSelected) Primary else Gray900,
        textAlign = TextAlign.Center,
        modifier = Modifier
            .fillMaxWidth()
            .clickable(
                interactionSource = remember { MutableInteractionSource() },
                indication = rememberRipple(),
                onClick = onClick
            )
            .padding(vertical = 20.dp)
    )
}