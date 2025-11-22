package hackathon.picky.feature.home.model

import android.R
import hackathon.picky.core.model.Category
import java.time.LocalDateTime

sealed class HomeUiState {
    data class Main(
        val infoSectionList: List<HomeSectionListItem>,
        val topBannerList: List<HomeListItem>, // url 반환 시 String으로, HomeTopBanner도 그에 맞게 변경
        val topList: List<HomeListItem>
    ) : HomeUiState()

    data class Detail(
        val previousUiState: HomeUiState,
        val policyDetail: PolicyDetail,
        val daysRemaining: Int,
        val isBookmarked: Boolean = false
    ) : HomeUiState()

    data class ListScreen(
        val previousUiState: HomeUiState,
        val list: List<HomeListItem>,
        val category: Category
    ) : HomeUiState()

    data object Init : HomeUiState()
}

val HomeUiTest = HomeUiState.Main(
    infoSectionList = listOf(
        HomeSectionListItem(
            title = "오늘의 픽!",
            description = "당신을 위한 맞춤 추천",
            titleImageRes = R.drawable.ic_menu_camera,
            infoList = listOf(
                HomeListItem(
                    id = 1,
                    imageRes = R.drawable.ic_menu_camera, title = "Item 1",
                    closingDate = LocalDateTime.now().plusDays(3)
                ),
                HomeListItem(
                    id = 2,
                    imageRes = R.drawable.ic_menu_compass, title = "Item 2",
                    closingDate = LocalDateTime.now().plusDays(3)
                ),
                HomeListItem(
                    id = 3,
                    imageRes = R.drawable.ic_menu_agenda,
                    title = "Item 3",
                    closingDate = LocalDateTime.now().plusDays(3)
                ),
            ),
            category = Category.EDUCATION,
        ),
        HomeSectionListItem(
            title = "오늘의 픽!",
            description = "당신을 위한 맞춤 추천",
            titleImageRes = R.drawable.ic_menu_camera,
            infoList = listOf(
                HomeListItem(
                    id = 1,
                    imageRes = R.drawable.ic_menu_camera, title = "Item 1",
                    closingDate = LocalDateTime.now().plusDays(3)
                ),
                HomeListItem(
                    id = 2,
                    imageRes = R.drawable.ic_menu_compass, title = "Item 2",
                    closingDate = LocalDateTime.now().plusDays(3)
                ),
                HomeListItem(
                    id = 3,
                    imageRes = R.drawable.ic_menu_agenda,
                    title = "Item 3",
                    closingDate = LocalDateTime.now().plusDays(3)
                ),
            ),
            category = Category.EMPLOYMENT,
        ),
    ),
    topBannerList = listOf(
        HomeListItem(
            id = 1,
            imageRes = R.drawable.ic_menu_camera,
            title = "Banner 1",
            closingDate = LocalDateTime.now().plusDays(5)
        ),
        HomeListItem(
            id = 2,
            imageRes = R.drawable.ic_menu_compass,
            title = "Banner 2",
            closingDate = LocalDateTime.now().plusDays(5)
        ),
    ),
    topList =  listOf(
        HomeListItem(
            id = 1,
            imageRes = R.drawable.ic_menu_camera,
            title = "Banner 1",
            closingDate = LocalDateTime.now().plusDays(5)
        ),
        HomeListItem(
            id = 2,
            imageRes = R.drawable.ic_menu_compass,
            title = "Banner 2",
            closingDate = LocalDateTime.now().plusDays(5)
        ),
    ),
)

val policyDetailData = PolicyDetail(
    id = "D-13",
    title = "정책명 예시입니다. 길어질 시 예시입니다. 길어질 시 예시입니다.",
    department = "주관 부처",
    applicationPeriod = "2024.09.07 ~ 2024.09.28",
    eligibility = listOf(
        "백엔드 개발자",
        "프론트엔드 개발자",
        "프로덕트디자이너플랫폼하는대학에학중인대학..."
    ),
    description = """정책 예시가 들어갑니다. 정책 예시가 들어갑니다.
정책 예시가 들어갑니다.정책 예시가 들어갑니다.
정책 예시가 들어갑니다.정책 예시가 들어갑니다.
정책 예시가 들어갑니다.정책 예시가 들어갑니다.
정책 예시가 들어갑니다.정책 예시가 들어갑니다.
정책 예시가 들어갑니다.정책 예시가 들어갑니다.
정책 예시가 들어갑니다.정책 예시가 들어갑니다.
정책 예시가 들어갑니다.정책 예시가 들어갑니다.
정책 예시가 들어갑니다.정책 예시가 들어갑니다.""".trimIndent()
)