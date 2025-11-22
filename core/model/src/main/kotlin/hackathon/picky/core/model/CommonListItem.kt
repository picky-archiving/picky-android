package hackathon.picky.core.model

import androidx.annotation.DrawableRes
import java.time.LocalDateTime

data class CommonListItem (
    val id: Int,
    val title: String,
    @DrawableRes val imageRes: Int,
    val closingDate: LocalDateTime // null일 경우 상시
)