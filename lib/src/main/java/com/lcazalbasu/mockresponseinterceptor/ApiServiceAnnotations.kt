package com.lcazalbasu.mockresponseinterceptor

/**
 * Annotation to mark a retrofit API call as a mock.
 * @param enabled True if the mock should be enabled, false otherwise.
 */
@Target(AnnotationTarget.FUNCTION)
@Retention(AnnotationRetention.RUNTIME)
internal annotation class Mock(val enabled: Boolean)

/**
 * Annotation to define a mock response for a retrofit API call.
 * @param headers The headers of the response.
 * @param code The response code.
 * @param message The response message.
 * @param stringBody The response body as a json string.
 * @param fileBodyPath The path to the asset file containing the response body.
 */
@Target(AnnotationTarget.FUNCTION)
@Retention(AnnotationRetention.RUNTIME)
internal annotation class MockResponse(
    val headers: Array<Header> = [],
    val code: Int = 200,
    val message: String = "",
    val stringBody: String = "",
    val fileBodyPath: String = "",
) {

    annotation class Header(
        val name: String,
        val value: String,
    )
}
