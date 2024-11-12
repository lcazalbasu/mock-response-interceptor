@file:Suppress("ktlint:standard:filename")

package com.lcazalbasu.mockresponseinterceptor.navigation

sealed class Destinations(val resolvedRoute: String) {

    sealed class NewsListDestinations(
        resolvedRoute: String,
    ) : Destinations(resolvedRoute) {

        data class ToDetail(
            val id: Long,
        ) : NewsListDestinations(
            resolvedRoute = Screen.NewsDetailScreen.getRouteForArgs(id),
        )
    }
}
