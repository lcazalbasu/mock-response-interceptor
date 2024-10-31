package com.lcazalbasu.mockresponseinterceptor.utils

import com.lcazalbasu.mockresponseinterceptor.data.models.exceptions.AppException

sealed class Resource<T> {
    data class Success<T>(val data: T) : Resource<T>()
    data class Error<T>(val failure: AppException) : Resource<T>()
}
