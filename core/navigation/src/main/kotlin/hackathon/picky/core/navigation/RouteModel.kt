package hackathon.picky.core.navigation

import kotlinx.serialization.Serializable

sealed interface Route {
    @Serializable
    data object AuthRoute: Route

    @Serializable
    data class Home(val policyId: Int? = null) : Route


    @Serializable
    data object SearchRoute : Route
}

sealed interface MainTabRoute : Route {
    @Serializable
    data object Home : MainTabRoute

    @Serializable
    data object My : MainTabRoute
}