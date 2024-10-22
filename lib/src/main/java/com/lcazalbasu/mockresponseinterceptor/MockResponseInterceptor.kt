package com.lcazalbasu.mockresponseinterceptor

import android.content.Context
import java.io.IOException
import okhttp3.Interceptor
import okhttp3.MediaType.Companion.toMediaType
import okhttp3.Protocol
import okhttp3.Request
import okhttp3.Response
import okhttp3.ResponseBody.Companion.toResponseBody
import retrofit2.Invocation

/**
 * Interceptor that provides mock responses for Retrofit requests.
 *
 * @param context The context used to read the mock response from the assets folder.
 */
class MockResponseInterceptor(
    private val context: Context,
) : Interceptor {

    override fun intercept(chain: Interceptor.Chain): Response {
        val request = chain.request()
        if (request.needsMockResponse().not()) {
            return chain.proceed(request)
        }

        val invocation = request.tag(Invocation::class.java)
        val annotation = invocation?.method()?.getAnnotation(MockResponse::class.java)
            ?: return chain.proceed(request)

        val builder = Response.Builder()
            .code(annotation.code)
            .message(annotation.message)
            .request(request)
            .protocol(chain.connection()?.protocol() ?: Protocol.HTTP_1_1)

        for (header in annotation.headers) {
            builder.addHeader(header.name, header.value)
        }

        val responseString = when {
            annotation.stringBody.isNotBlank() ->
                annotation.stringBody

            annotation.fileBodyPath.isNotBlank() ->
                getJsonDataFromAsset(annotation.fileBodyPath)

            else -> ""
        }
        builder.body(
            responseString.toByteArray()
                .toResponseBody("application/json".toMediaType()),
        )

        return builder.build()
    }

    private fun Request.needsMockResponse(): Boolean {
        val invocation = tag(Invocation::class.java)
        val annotation = invocation?.method()?.getAnnotation(Mock::class.java)
            ?: return false
        return annotation.enabled
    }

    @Throws(IOException::class)
    private fun getJsonDataFromAsset(fileName: String): String {
        return context.assets.open(fileName).bufferedReader().use { it.readText() }
    }
}
