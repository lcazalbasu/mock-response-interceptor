package com.lcazalbasu.mockresponseinterceptor.features.detail.ui

import com.lcazalbasu.mockresponseinterceptor.data.models.exceptions.AppException
import com.lcazalbasu.mockresponseinterceptor.features.detail.data.NewsDetail

data class NewsDetailState(
    val exception: AppException?,
    val isLoading: Boolean,
    val detail: NewsDetail?,
) {
    companion object {
        fun default() = NewsDetailState(
            exception = null,
            isLoading = false,
            detail = null,
        )
    }
}
