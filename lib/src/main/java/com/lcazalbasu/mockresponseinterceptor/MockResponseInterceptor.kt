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
 *
 * @see Mock
 * @see MockResponseHeaders
 * @see MockResponseCode
 * @see MockResponseMessage
 * @see MockResponseBody
 * @see MockResponseFileBody
 * @see RandomMockResponseBody
 * @see RandomMockResponseFileBody
 *
 * If you are using the [MockResponseInterceptor] in a test environment,
 * you can use the [Mock] annotation to enable or disable the mock response.
 *
 * If you will set the [Mock] annotation to true,
 * you can use the [MockResponseHeaders] to set the response headers,
 * [MockResponseCode] to set the response code,
 * [MockResponseMessage] to set the response message.
 *
 * If you want to the response body you can use one of the following annotations:
 * [MockResponseBody] to set the response body as a json string,
 * [MockResponseFileBody] to set the response body from a file in the assets folder,
 * [RandomMockResponseBody] to set a random response body from a list of strings,
 * [RandomMockResponseFileBody] to set a random response body from a list of files in the assets folder.
 * There annotations will be checked in the order they are listed.
 *
 * So, if you set both [MockResponseBody] and [MockResponseFileBody],
 * the [MockResponseBody] will be used.
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
            ?: return chain.proceed(request)

        val builder = Response.Builder()

        // set the response code
        val codeAnnotation = invocation.method().getAnnotation(MockResponseCode::class.java)
        val code = codeAnnotation?.code ?: 200
        builder.code(code)

        // set the response message
        val messageAnnotation = invocation.method().getAnnotation(MockResponseMessage::class.java)
        val message = messageAnnotation?.message ?: "OK"
        builder.message(message)

        // set the request and protocol
        builder.request(request)
            .protocol(chain.connection()?.protocol() ?: Protocol.HTTP_1_1)

        val headersAnnotation = invocation.method().getAnnotation(MockResponseHeaders::class.java)
        val headers = headersAnnotation?.headers ?: emptyArray()

        // set the response headers
        for (header in headers) {
            builder.addHeader(header.name, header.value)
        }

        // set the response body
        builder.body(
            getResponseBody(invocation)
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

    private fun getResponseBody(invocation: Invocation): String {
        val stringBodyAnnotation = invocation.method().getAnnotation(MockResponseBody::class.java)
        if (stringBodyAnnotation != null) {
            return stringBodyAnnotation.jsonStringBody
        }

        val fileBodyAnnotation = invocation.method().getAnnotation(MockResponseFileBody::class.java)
        if (fileBodyAnnotation != null) {
            return getJsonDataFromAsset(fileBodyAnnotation.fileBodyPath)
        }

        val randomMockResponseAnnotation =
            invocation.method().getAnnotation(RandomMockResponseBody::class.java)
        if (randomMockResponseAnnotation != null) {
            return randomMockResponseAnnotation.randomStringBody.randomOrNull() ?: ""
        }

        val randomMockResponseFileAnnotation =
            invocation.method().getAnnotation(RandomMockResponseFileBody::class.java)
        if (randomMockResponseFileAnnotation != null) {
            val randomFile = randomMockResponseFileAnnotation.randomFileBodyPath.randomOrNull()
                ?: return ""

            return getJsonDataFromAsset(randomFile)
        }

        return ""
    }

    @Throws(IOException::class)
    private fun getJsonDataFromAsset(fileName: String): String {
        return context.assets.open(fileName).bufferedReader().use { it.readText() }
    }
}
