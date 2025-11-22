package hackathon.picky.feature.home.model

import com.example.core.designsystem.R
import hackathon.picky.core.model.common.Category
import hackathon.picky.core.model.common.CommonListItem
import hackathon.picky.core.model.common.CommonListItemTest
import hackathon.picky.core.model.common.SearchFilter
import java.time.LocalDate

sealed class HomeUiState {
    data class Main(
        val infoSectionList: List<HomeSectionListItem>,
        val topBannerList: List<Int> = listOf(
            R.drawable.banner_1,
            R.drawable.banner_2,
            R.drawable.banner_3,
            R.drawable.banner_4,
            R.drawable.banner_5
        ), // 배너 이미지 리소스 ID 리스트
        val topList: List<CommonListItem>,
        val isRefreshing: Boolean = false
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

    data class Web(
        val previousUiState: HomeUiState,
        val webUrl:String
    ): HomeUiState()
    data object Init : HomeUiState()
}


val HomeUiTest = HomeUiState.Main(
    infoSectionList = listOf(
        HomeSectionListItem(
            infoList = listOf(
                CommonListItemTest, CommonListItemTest, CommonListItemTest
            ),
            category = Category.EDUCATION,
        ),
        HomeSectionListItem(
            infoList = listOf(
                CommonListItemTest, CommonListItemTest, CommonListItemTest
            ),
            category = Category.EMPLOYMENT,
        ),
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
    webUrl = "",
)