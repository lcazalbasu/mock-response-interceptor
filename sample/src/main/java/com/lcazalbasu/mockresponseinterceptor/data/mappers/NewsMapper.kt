package com.lcazalbasu.mockresponseinterceptor.data.mappers

import com.lcazalbasu.mockresponseinterceptor.data.models.remote.NewsDetailResponse
import com.lcazalbasu.mockresponseinterceptor.data.models.remote.NewsResponse
import com.lcazalbasu.mockresponseinterceptor.features.detail.data.NewsDetail
import com.lcazalbasu.mockresponseinterceptor.features.news.data.News

interface NewsMapper {

    fun mapResponseToModel(response: NewsDetailResponse): NewsDetail

    fun mapResponseToModel(response: NewsResponse): News

    fun mapResponseListToModelList(list: List<NewsResponse>): List<News> =
        list.map { mapResponseToModel(it) }
}
