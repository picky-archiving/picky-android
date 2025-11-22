package hackathon.picky.feature.main.component

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.navigation.compose.NavHost
import hackathon.picky.feature.auth.authNavGraph
import hackathon.picky.feature.home.homeNavGraph
import hackathon.picky.feature.main.MainNavigator
import hackathon.picky.feature.mypage.myPageNavGraph
import hackathon.picky.feature.search.searchNavGraph

@Composable
fun MainNavHost(
    modifier: Modifier = Modifier,
    navigator: MainNavigator,
    padding: PaddingValues,
    onShowErrorSnackBar: (throwable: Throwable?) -> Unit,
) {
    Box(
        modifier = Modifier
            .fillMaxSize()
            .background(Color.White)
    ) {
        NavHost(
            navController = navigator.navController,
            startDestination = navigator.startDestination,
        ) {
            homeNavGraph(
                padding = padding,
                navigateSearch = navigator::navigateSearch,
                onBackPressed = navigator::onBackPressed,
                navigateMy = navigator::navigateMyPage
            )

            myPageNavGraph(
                padding = padding,
                onBackClick = navigator::popBackStackIfNotHome,
                onSearchClick = navigator::navigateSearch,
                onClickDetail = navigator::navigatePolicyDetail
            )

            authNavGraph(
                padding = padding,
                navigateHome = navigator::navigateHome
            )

            searchNavGraph(
                padding = padding,
                navigateDetail = navigator::navigatePolicyDetail,
                onBackPressed = navigator::onBackPressed
            )
        }
    }
}