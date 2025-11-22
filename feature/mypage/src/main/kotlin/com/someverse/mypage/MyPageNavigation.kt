package com.someverse.mypage

import androidx.compose.foundation.layout.PaddingValues
import androidx.navigation.NavController
import androidx.navigation.NavGraphBuilder
import androidx.navigation.NavOptions
import androidx.navigation.compose.composable

const val MY_PAGE_ROUTE = "my_page_route"

fun NavController.navigateToMyPage(navOptions: NavOptions? = null) {
    navigate(MY_PAGE_ROUTE, navOptions)
}

fun NavGraphBuilder.myPageScreen(padding: PaddingValues) {
    composable(route = MY_PAGE_ROUTE) {
        MyPageRoute(padding = padding)
    }
}
