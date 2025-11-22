package hackathon.picky.feature.main

import androidx.compose.runtime.Composable
import androidx.compose.runtime.remember
import androidx.navigation.NavDestination
import androidx.navigation.NavDestination.Companion.hasRoute
import androidx.navigation.NavGraph.Companion.findStartDestination
import androidx.navigation.NavHostController
import androidx.navigation.compose.currentBackStackEntryAsState
import androidx.navigation.compose.rememberNavController
import androidx.navigation.navOptions
import hackathon.picky.core.navigation.Route
import hackathon.picky.feature.home.navigateHome
import hackathon.picky.feature.home.navigatePolicyDetail
import hackathon.picky.feature.mypage.navigateMyPage
import hackathon.picky.feature.search.navigateSearch

class MainNavigator(
    val navController: NavHostController
) {
    private val currentDestination: NavDestination?
        @Composable get() = navController.currentBackStackEntryAsState().value?.destination

    val startDestination: Route = Route.Home()

    private val singleTopOptions = navOptions {
        launchSingleTop = false
        restoreState = true
    }

    val currentTab: MainTab?
        @Composable get() = MainTab.find { tab ->
            currentDestination?.hasRoute(tab::class) == true
        }

    fun navigate(tab: MainTab) {
        val navOptions = navOptions {
            popUpTo(navController.graph.findStartDestination().id) {
                inclusive = true  //  백스택에서 제거됨
            }
            launchSingleTop = true
        }

        when (tab) {
            MainTab.HOME -> navController.navigateHome(navOptions)
            MainTab.MY -> {}
        }
    }

    @Composable
    fun shouldShowBottomBar() = MainTab.contains {
        currentDestination?.hasRoute(it::class) == true
    }

    fun navigateHome() {
        navController.navigateHome(singleTopOptions)
    }

    fun navigatePolicyDetail(policyId: Int) {
        navController.navigatePolicyDetail(policyId = policyId, navOptions =  singleTopOptions)
    }

    fun navigateMyPage() {
        navController.navigateMyPage()
    }

    fun popBackStackIfNotHome() {
        if (!isSameCurrentDestination<Route.AuthRoute>()) {
            navController.popBackStack()
        }
    }

    fun navigateSearch() {
        navController.navigateSearch()
    }

    fun onBackPressed() {
        navController.popBackStack()
    }

    private inline fun <reified T : Route> isSameCurrentDestination(): Boolean {
        return navController.currentDestination?.hasRoute<T>() == true
    }
}

@Composable
internal fun rememberMainNavigator(
    navController: NavHostController = rememberNavController(),
): MainNavigator = remember(navController) {
    MainNavigator(navController)
}