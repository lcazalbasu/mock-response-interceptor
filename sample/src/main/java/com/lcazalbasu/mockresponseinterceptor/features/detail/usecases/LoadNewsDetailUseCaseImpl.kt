package com.lcazalbasu.mockresponseinterceptor.features.detail.usecases

import com.lcazalbasu.mockresponseinterceptor.data.repositories.NewsRepository
import com.lcazalbasu.mockresponseinterceptor.features.detail.data.NewsDetail
import com.lcazalbasu.mockresponseinterceptor.utils.Resource
import javax.inject.Inject

class LoadNewsDetailUseCaseImpl @Inject constructor(
    private val repository: NewsRepository,
) : LoadNewsDetailUseCase {
    override suspend fun run(id: Long): Resource<NewsDetail> {
        return repository.getNewsDetails(id)
    }
}
