package com.lcazalbasu.mockresponseinterceptor.features.detail.usecases

import com.lcazalbasu.mockresponseinterceptor.features.detail.data.NewsDetail
import com.lcazalbasu.mockresponseinterceptor.utils.Resource

interface LoadNewsDetailUseCase {
    suspend fun run(id: Long): Resource<NewsDetail>
}
