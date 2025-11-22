package hackathon.picky.feature.mypage

import androidx.compose.foundation.layout.PaddingValues
import androidx.navigation.NavController
import androidx.navigation.NavGraphBuilder
import androidx.navigation.NavOptions
import androidx.navigation.compose.composable
import hackathon.picky.core.navigation.Route

fun NavController.navigateMyPage(navOptions: NavOptions? = null) {
    navigate(Route.MyPage, navOptions)
}

fun NavGraphBuilder.myPageNavGraph(
    padding: PaddingValues,
    onBackClick: () -> Unit,
    onSearchClick: () -> Unit,
) {
    composable<Route.MyPage> {
        MyPageRoute(
            padding = padding,
            onBackClick = onBackClick,
            onSearchClick = onSearchClick
        )
    }
}
