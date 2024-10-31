package com.lcazalbasu.mockresponseinterceptor.data.mappers

import com.lcazalbasu.mockresponseinterceptor.data.models.remote.NewsDetailResponse
import com.lcazalbasu.mockresponseinterceptor.data.models.remote.NewsResponse
import com.lcazalbasu.mockresponseinterceptor.features.detail.data.NewsDetail
import com.lcazalbasu.mockresponseinterceptor.features.news.data.News
import javax.inject.Inject

class NewsMapperImpl @Inject constructor() : NewsMapper {

    override fun mapResponseToModel(response: NewsDetailResponse): NewsDetail {
        return NewsDetail(
            id = response.id,
            title = response.title,
            subtitle = response.subtitle,
            shortDescription = response.shortDescription,
            content = response.content,
            imageUrl = response.imageUrl,
        )
    }

    override fun mapResponseToModel(response: NewsResponse): News {
        return News(
            id = response.id,
            title = response.title,
            subtitle = response.subtitle,
            shortDescription = response.shortDescription,
            thumbUrl = response.thumbUrl,
        )
    }
}
