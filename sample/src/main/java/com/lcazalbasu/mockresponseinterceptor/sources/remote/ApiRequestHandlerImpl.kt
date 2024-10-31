package com.lcazalbasu.mockresponseinterceptor.sources.remote

import com.lcazalbasu.mockresponseinterceptor.data.models.exceptions.AppException
import com.lcazalbasu.mockresponseinterceptor.utils.Resource
import javax.inject.Inject

class ApiRequestHandlerImpl @Inject constructor() : ApiRequestHandler {

    override suspend fun <T : Any, R> handleRequest(
        apiCall: suspend () -> T,
        successMapper: (T) -> R,
    ): Resource<R> {
        return try {
            val response = apiCall.invoke()
            Resource.Success(data = successMapper(response))
        } catch (exception: Exception) {
            Resource.Error(
                failure = AppException.NetworkException(
                    causedByException = exception,
                ),
            )
        }
    }
}
