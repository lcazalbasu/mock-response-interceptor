package com.lcazalbasu.mockresponseinterceptor.features.news.usecases

import com.lcazalbasu.mockresponseinterceptor.data.repositories.NewsRepository
import com.lcazalbasu.mockresponseinterceptor.features.news.data.News
import com.lcazalbasu.mockresponseinterceptor.utils.Resource
import javax.inject.Inject

class LoadNewsListUseCaseImpl @Inject constructor(
    private val repository: NewsRepository,
) : LoadNewsListUseCase {
    override suspend fun run(): Resource<List<News>> {
        return repository.getNews()
    }
}
