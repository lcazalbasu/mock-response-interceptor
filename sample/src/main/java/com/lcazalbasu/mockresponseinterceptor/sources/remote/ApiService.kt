package com.lcazalbasu.mockresponseinterceptor.sources.remote

import com.lcazalbasu.mockresponseinterceptor.Mock
import com.lcazalbasu.mockresponseinterceptor.MockResponseCode
import com.lcazalbasu.mockresponseinterceptor.MockResponseFileBody
import com.lcazalbasu.mockresponseinterceptor.MockResponseMessage
import com.lcazalbasu.mockresponseinterceptor.RandomMockResponseFileBody
import com.lcazalbasu.mockresponseinterceptor.data.models.remote.NewsDetailResponse
import com.lcazalbasu.mockresponseinterceptor.data.models.remote.NewsResponse
import retrofit2.http.GET
import retrofit2.http.Path

interface ApiService {

    @Mock
    @MockResponseFileBody(fileBodyPath = "responses/news_list_200.json")
    @GET("/api/news/list")
    suspend fun getNewsListResponseOk(): List<NewsResponse>

    @Mock
    @MockResponseCode(500)
    @MockResponseMessage("Internal Server Error")
    @MockResponseFileBody(fileBodyPath = "responses/news_list_500.json")
    @GET("/api/news/list")
    suspend fun getNewsListResponseError(): List<NewsResponse>

    @Mock
    @RandomMockResponseFileBody(
        randomFileBodyPath = [
            "responses/news_detail_1.json",
            "responses/news_detail_2.json",
            "responses/news_detail_3.json",
            "responses/news_detail_4.json",
        ],
    )
    @GET("/api/news/{id}")
    suspend fun getNewsDetail(@Path("id") id: Long): NewsDetailResponse
}
