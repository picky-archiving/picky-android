package hackathon.picky.feature.home.model

import androidx.annotation.DrawableRes
import hackathon.picky.core.model.Category
import java.time.LocalDateTime

data class HomeSectionListItem(
    val title: String,
    @DrawableRes val titleImageRes: Int,
    val description: String,
    val category: Category,
    val infoList: List<HomeListItem>
)

data class HomeListItem(
    val id: Int,
    val title: String,
    @DrawableRes val imageRes: Int,
    val closingDate: LocalDateTime // null일 경우 상시
)