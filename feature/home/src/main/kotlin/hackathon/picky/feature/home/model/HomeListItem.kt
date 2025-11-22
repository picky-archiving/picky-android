package hackathon.picky.feature.home.model

import androidx.annotation.DrawableRes

data class HomeSectionListItem(
    val title: String,
    @DrawableRes val titleImageRes: Int,
    val description: String,
    val infoList: List<HomeListItem>
)

data class HomeListItem(
    val title: String,
    @DrawableRes val imageRes: Int
)