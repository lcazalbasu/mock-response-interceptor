package com.lcazalbasu.mockresponseinterceptor.data.repositories

import com.lcazalbasu.mockresponseinterceptor.features.detail.data.NewsDetail
import com.lcazalbasu.mockresponseinterceptor.features.news.data.News
import com.lcazalbasu.mockresponseinterceptor.utils.Resource

interface NewsRepository {

    suspend fun getNews(): Resource<List<News>>

    suspend fun getNewsDetails(id: Long): Resource<NewsDetail>
}
