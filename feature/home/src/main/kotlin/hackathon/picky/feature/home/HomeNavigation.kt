package hackathon.picky.feature.home

import androidx.compose.foundation.layout.PaddingValues
import androidx.navigation.NavController
import androidx.navigation.NavGraphBuilder
import androidx.navigation.NavOptions
import androidx.navigation.compose.composable
import androidx.navigation.toRoute
import hackathon.picky.core.navigation.Route

fun NavController.navigateHome(
    navOptions: NavOptions
) {
    navigate(Route.Home, navOptions)
}

fun NavController.navigatePolicyDetail(
    policyId: Int
) {
    navigate(Route.Home(policyId))
}

fun NavGraphBuilder.homeNavGraph(
    padding: PaddingValues,
    navigateMy: () -> Unit,
    navigateSearch: () -> Unit,
    onBackPressed: () -> Unit
) {
    composable<Route.Home> {
        HomeRoute(
            padding = padding,
            navigateMy = navigateMy,
            navigateSearch = navigateSearch,
            policyId = it.toRoute<Route.Home>().policyId,
            onBackPressed = onBackPressed
        )
    }
}
