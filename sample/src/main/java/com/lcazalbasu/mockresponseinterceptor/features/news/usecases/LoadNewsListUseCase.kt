package com.lcazalbasu.mockresponseinterceptor.features.news.usecases

import com.lcazalbasu.mockresponseinterceptor.features.news.data.News
import com.lcazalbasu.mockresponseinterceptor.utils.Resource

interface LoadNewsListUseCase {
    suspend fun run(): Resource<List<News>>
}
