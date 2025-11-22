package hackathon.picky.core.designsystem.common

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.remember
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import hackathon.picky.core.designsystem.theme.Gray100
import hackathon.picky.core.designsystem.theme.Gray400
import hackathon.picky.core.designsystem.theme.PretendardFontFamily
import hackathon.picky.core.designsystem.theme.Primary
import hackathon.picky.core.designsystem.theme.ValidBox
import java.time.LocalDate
import java.time.LocalDateTime
import java.time.temporal.ChronoUnit

@Composable
fun DateBox(closingDate: LocalDate, isAll: Boolean = false, modifier: Modifier = Modifier) {

    val daysRemaining = remember(closingDate) {
        ChronoUnit.DAYS.between(LocalDate.now(), closingDate)
    }

    // 마감 여부
    val isClosed = (daysRemaining <= 0) && !isAll

    // 색상 조건부 변경
    val containerColor = if (isClosed) Gray100 else ValidBox
    val textColor = if (isClosed) Gray400 else Primary

    Box(
        modifier = modifier
            .clip(RoundedCornerShape(20.dp))
            .background(containerColor)
            .padding(horizontal = 8.dp, vertical = 4.dp)
    ) {
        Text(
            text = if (daysRemaining > 0) "D-$daysRemaining" else if (isClosed) "상시" else "마감",
            fontFamily = PretendardFontFamily,
            fontSize = 12.sp,
            fontWeight = FontWeight.Medium,
            color = textColor
        )
    }
}


