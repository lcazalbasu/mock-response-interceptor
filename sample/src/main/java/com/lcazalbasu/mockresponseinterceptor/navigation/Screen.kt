package com.lcazalbasu.mockresponseinterceptor.navigation

// Screens
const val NEWS_LIST_SCREEN = "newsList"
const val NEWS_DETAIL_SCREEN = "newsDetail"

// Args
const val NEWS_ID_ARG = "newsId"

const val NEWS_LIST_ROUTE = NEWS_LIST_SCREEN
const val NEWS_DETAIL_ROUTE = "$NEWS_DETAIL_SCREEN?$NEWS_ID_ARG={$NEWS_ID_ARG}"

sealed class Screen(val route: String) {

    data object NewsListScreen : Screen(route = NEWS_LIST_ROUTE)

    data object NewsDetailScreen : Screen(route = NEWS_DETAIL_ROUTE) {
        fun getRouteForArgs(id: Long): String = route.replace("{$NEWS_ID_ARG}", id.toString())
    }
}
