package hackathon.picky.feature.home.model

import hackathon.picky.core.model.common.Category
import hackathon.picky.core.model.common.CommonListItem

data class HomeSectionListItem(
    val category: Category,
    val infoList: List<CommonListItem>
)

