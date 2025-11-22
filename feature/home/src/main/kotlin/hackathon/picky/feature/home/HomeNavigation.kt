package hackathon.picky.feature.home

import androidx.compose.foundation.layout.PaddingValues
import androidx.navigation.NavController
import androidx.navigation.NavGraphBuilder
import androidx.navigation.NavOptions
import androidx.navigation.compose.composable
import hackathon.picky.core.navigation.MainTabRoute
import hackathon.picky.core.navigation.Route

fun NavController.navigateHome(
    navOptions: NavOptions
) {
    navigate(MainTabRoute.Home, navOptions)
}

fun NavController.navigatePolicyDetail(
    policyId: String
) {
    navigate(Route.PolicyDetail(policyId))
}

fun NavGraphBuilder.homeNavGraph(
    padding: PaddingValues,
    navigateMy: () -> Unit,
    navigatePolicyDetail: (String) -> Unit
) {
    composable<MainTabRoute.Home> {
        HomeRoute(
            padding = padding,
            navigateMy = navigateMy,
            navigatePolicyDetail = navigatePolicyDetail
        )
    }
}

fun NavGraphBuilder.policyDetailNavGraph(
    onBackClick: () -> Unit
) {
    composable<Route.PolicyDetail> {
        PolicyDetailRoute(
            onBackClick = onBackClick
        )
    }
}