package hackathon.picky.feature.home.model

import androidx.annotation.DrawableRes
import hackathon.picky.core.model.Category
import hackathon.picky.core.model.CommonListItem

data class HomeSectionListItem(
    val title: String,
    @DrawableRes val titleImageRes: Int,
    val description: String,
    val category: Category,
    val infoList: List<CommonListItem>
)

