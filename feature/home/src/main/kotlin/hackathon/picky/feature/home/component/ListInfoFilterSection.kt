package hackathon.picky.feature.home.component

import androidx.compose.foundation.clickable
import androidx.compose.foundation.interaction.MutableInteractionSource
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.material.ripple.rememberRipple
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.ModalBottomSheet
import androidx.compose.material3.Text
import androidx.compose.material3.rememberModalBottomSheetState
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.core.designsystem.R
import hackathon.picky.core.designsystem.theme.Gray600
import hackathon.picky.core.designsystem.theme.Gray900
import hackathon.picky.core.designsystem.theme.PretendardFontFamily
import hackathon.picky.core.designsystem.theme.Primary
import hackathon.picky.core.model.common.SearchFilter
import kotlinx.coroutines.launch

@OptIn(ExperimentalMaterial3Api::class, ExperimentalMaterial3Api::class)
@Composable
fun ListInfoFilterSection(
    modifier: Modifier = Modifier,
    listCount: Int,
    filter: SearchFilter,
    onFilterChange: (SearchFilter) -> Unit
) {
    val sheetState = rememberModalBottomSheetState()
    val scope = rememberCoroutineScope()
    var showBottomSheet by remember { mutableStateOf(false) }

    Row(
        modifier = Modifier
            .fillMaxWidth()
            .padding(horizontal = 20.dp, vertical = 8.dp),
        verticalAlignment = Alignment.CenterVertically,
        horizontalArrangement = Arrangement.SpaceBetween
    ) {
        Text(
            text = "공고 ${listCount}개",
            fontFamily = PretendardFontFamily,
            fontWeight = FontWeight.Medium,
            fontSize = 14.sp,
            color = Gray600,
        )

        Row(
            modifier = Modifier
                .clickable(
                    interactionSource = remember { MutableInteractionSource() },
                    indication = rememberRipple(),
                    onClick = {  showBottomSheet = true }
                ),
            verticalAlignment = Alignment.CenterVertically
        ) {
            Text(
                text = filter.label,
                fontFamily = PretendardFontFamily,
                fontWeight = FontWeight.Medium,
                fontSize = 14.sp,
                color = Gray600
            )
            Icon(
                painter = painterResource(id = R.drawable.arrowdown),
                contentDescription = "필터 열기"
            )
        }
    }
    if (showBottomSheet) {
        ModalBottomSheet(
            onDismissRequest = {
                showBottomSheet = false
            },
            sheetState = sheetState
        ) {
            Column(modifier = Modifier.fillMaxWidth()) {
                SearchFilter.values().forEach { item ->
                    Text(
                        text = item.label,
                        modifier = Modifier
                            .fillMaxWidth()
                            .clickable(
                                interactionSource = remember { MutableInteractionSource() },
                                indication = rememberRipple(),
                                onClick = {
                                    scope.launch {
                                        onFilterChange(item)
                                        sheetState.hide()
                                    }.invokeOnCompletion {
                                        if (!sheetState.isVisible) {
                                            showBottomSheet = false
                                        }
                                    }
                                }
                            )
                            .padding(vertical = 16.dp, horizontal = 20.dp),
                        fontFamily = PretendardFontFamily,
                        fontSize = 16.sp,
                        fontWeight = if (item == filter) FontWeight.Bold else FontWeight.Normal,
                        color = if (item == filter) Primary else Gray900
                    )
                }
            }
        }
    }
}

