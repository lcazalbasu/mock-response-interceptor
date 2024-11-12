package com.lcazalbasu.mockresponseinterceptor.features.news.ui

import com.lcazalbasu.mockresponseinterceptor.data.models.exceptions.AppException
import com.lcazalbasu.mockresponseinterceptor.features.news.data.News

data class NewsListState(
    val exception: AppException?,
    val isLoading: Boolean,
    val list: List<News>,
) {

    companion object {
        fun default(): NewsListState {
            return NewsListState(
                exception = null,
                isLoading = false,
                list = emptyList(),
            )
        }
    }
}
