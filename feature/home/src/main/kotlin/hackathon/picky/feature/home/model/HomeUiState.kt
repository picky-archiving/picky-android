package hackathon.picky.feature.home.model

import hackathon.picky.feature.home.model.HomeSectionListItem

sealed class HomeUiState {
    data class Success(
        val infoSectionList: List<HomeSectionListItem>,
        val topBannerList: List<Int> // url 반환 시 String으로, HomeTopBanner도 그에 맞게 변경
    ) : HomeUiState()

    data object Init : HomeUiState()
}

val HomeUiTest = HomeUiState.Success(
    infoSectionList = listOf(
        HomeSectionListItem(
            title = "오늘의 픽!",
            description = "당신을 위한 맞춤 추천",
            titleImageRes = android.R.drawable.ic_menu_camera,
            infoList = listOf(
                HomeListItem(imageRes = android.R.drawable.ic_menu_camera, title = "Item 1"),
                HomeListItem(imageRes = android.R.drawable.ic_menu_compass, title = "Item 2"),
                HomeListItem(imageRes = android.R.drawable.ic_menu_agenda, title = "Item 3"),
            ),
        ),
        HomeSectionListItem(
            title = "오늘의 픽!",
            description = "당신을 위한 맞춤 추천",
            titleImageRes = android.R.drawable.ic_menu_camera,
            infoList = listOf(
                HomeListItem(imageRes = android.R.drawable.ic_menu_camera, title = "Item 1"),
                HomeListItem(imageRes = android.R.drawable.ic_menu_compass, title = "Item 2"),
                HomeListItem(imageRes = android.R.drawable.ic_menu_agenda, title = "Item 3"),
            ),
        ),
    ),
    topBannerList = listOf(
        android.R.drawable.ic_menu_camera,
        android.R.drawable.ic_menu_camera,
        android.R.drawable.ic_menu_camera
    )
)