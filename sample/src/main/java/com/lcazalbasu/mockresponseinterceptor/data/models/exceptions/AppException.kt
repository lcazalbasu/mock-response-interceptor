package com.lcazalbasu.mockresponseinterceptor.data.models.exceptions

sealed class AppException(
    open val causedByException: Exception,
) : Exception(causedByException) {

    class NetworkException(override val causedByException: Exception) :
        AppException(causedByException = Exception(causedByException))

    class UnknownException(override val causedByException: Exception) :
        AppException(causedByException = Exception(causedByException))
}
