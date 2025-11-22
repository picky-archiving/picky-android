package hackathon.picky.feature.home.model

import android.R
import hackathon.picky.core.model.common.Category
import hackathon.picky.core.model.common.CommonListItem
import hackathon.picky.core.model.common.CommonListItemTest
import hackathon.picky.core.model.common.SearchFilter
import java.time.LocalDate
import java.time.LocalDateTime

sealed class HomeUiState {
    data class Main(
        val infoSectionList: List<HomeSectionListItem>,
        val topBannerList: List<CommonListItem>, // url 반환 시 String으로, HomeTopBanner도 그에 맞게 변경
        val topList: List<CommonListItem>
    ) : HomeUiState()

    data class Detail(
        val previousUiState: HomeUiState?,
        val policyDetail: PolicyDetail,
        val isBookmarked: Boolean = false
    ) : HomeUiState()

    data class ListScreen(
        val previousUiState: HomeUiState,
        val list: List<CommonListItem>,
        val category: Category,
        val searchFilter: SearchFilter
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
                CommonListItemTest, CommonListItemTest, CommonListItemTest
            ),
            category = Category.EDUCATION,
        ),
        HomeSectionListItem(
            title = "오늘의 픽!",
            description = "당신을 위한 맞춤 추천",
            titleImageRes = R.drawable.ic_menu_camera,
            infoList = listOf(
                CommonListItemTest, CommonListItemTest, CommonListItemTest
            ),
            category = Category.EMPLOYMENT,
        ),
    ),
    topBannerList = listOf(
        CommonListItemTest, CommonListItemTest
    ),
    topList = listOf(
        CommonListItemTest, CommonListItemTest
    ),
)

val policyDetailData = PolicyDetail(
    id = 1,
    title = "정책명 예시입니다. 길어질 시 예시입니다. 길어질 시 예시입니다.",
    department = "주관 부처",
    closingDate = LocalDate.now().plusDays(15),
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
정책 예시가 들어갑니다.정책 예시가 들어갑니다.""".trimIndent(),
    imgUrl = "https://sosal.kr/1144?pidx=0",
    startDate = LocalDate.now().minusDays(15),
)