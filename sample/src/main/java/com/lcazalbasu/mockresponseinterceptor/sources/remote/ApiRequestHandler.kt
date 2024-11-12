package com.lcazalbasu.mockresponseinterceptor.sources.remote

import com.lcazalbasu.mockresponseinterceptor.utils.Resource

interface ApiRequestHandler {

    suspend fun <T : Any, R> handleRequest(
        apiCall: suspend () -> T,
        successMapper: (T) -> R,
    ): Resource<R>
}
