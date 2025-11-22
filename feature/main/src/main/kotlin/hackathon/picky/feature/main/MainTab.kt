package hackathon.picky.feature.main

import androidx.annotation.DrawableRes
import androidx.compose.runtime.Composable
import com.example.feature.main.R
import hackathon.picky.core.navigation.MainTabRoute
import hackathon.picky.core.navigation.Route

enum class MainTab(
    @DrawableRes
    val iconResId: Int,
    internal val contentDescription: String,
    val route: MainTabRoute
) {
    HOME(
        iconResId = R.drawable.ic_launcher_background,
        contentDescription = "홈",
        route = MainTabRoute.Home,
    ),
    MY(
        iconResId = R.drawable.ic_launcher_background,
        contentDescription = "마이",
        route = MainTabRoute.My,
    );

    companion object {
        @Composable
        fun find(predicate: @Composable (MainTabRoute) -> Boolean): MainTab? {
            return entries.find { predicate(it.route) }
        }

        @Composable
        fun contains(predicate: @Composable (Route) -> Boolean): Boolean {
            return entries.map { it.route }.any { predicate(it) }
        }
    }
}