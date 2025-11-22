package hackathon.picky.feature.home.model

sealed class HomeUiState {
    data class Main(
        val infoSectionList: List<HomeSectionListItem>,
        val topBannerList: List<Int> // url 반환 시 String으로, HomeTopBanner도 그에 맞게 변경
    ) : HomeUiState()

    data class Detail(
        val previousUiState: HomeUiState,
        val policyDetail: PolicyDetail,
        val daysRemaining: Int,
        val isBookmarked: Boolean = false
    ) : HomeUiState()

    data object Init : HomeUiState()
}

val HomeUiTest = HomeUiState.Main(
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