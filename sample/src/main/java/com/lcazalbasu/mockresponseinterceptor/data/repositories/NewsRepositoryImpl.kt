package com.lcazalbasu.mockresponseinterceptor.data.repositories

import com.lcazalbasu.mockresponseinterceptor.data.mappers.NewsMapper
import com.lcazalbasu.mockresponseinterceptor.features.detail.data.NewsDetail
import com.lcazalbasu.mockresponseinterceptor.features.news.data.News
import com.lcazalbasu.mockresponseinterceptor.sources.remote.ApiRequestHandler
import com.lcazalbasu.mockresponseinterceptor.sources.remote.ApiService
import com.lcazalbasu.mockresponseinterceptor.utils.Resource
import javax.inject.Inject

class NewsRepositoryImpl @Inject constructor(
    private val apiService: ApiService,
    private val newsMapper: NewsMapper,
    private val apiRequestHandler: ApiRequestHandler,
) : NewsRepository {

    override suspend fun getNews(): Resource<List<News>> {
        val response = apiRequestHandler.handleRequest(
            apiCall = {
                val shouldReturnError = (0..1).random() == 1
                if (shouldReturnError) {
                    apiService.getNewsListResponseError()
                } else {
                    apiService.getNewsListResponseOk()
                }
            },
            successMapper = {
                newsMapper.mapResponseListToModelList(it)
            },
        )

        return response
    }

    override suspend fun getNewsDetails(id: Long): Resource<NewsDetail> {
        return apiRequestHandler.handleRequest(
            apiCall = {
                apiService.getNewsDetail(id = id)
            },
            successMapper = {
                newsMapper.mapResponseToModel(it)
            },
        )
    }
}
