package com.lcazalbasu.mockresponseinterceptor

/**
 * Annotation to mark a retrofit API call as a mock.
 * @param enabled True if the mock should be enabled, false otherwise.
 */
@Target(AnnotationTarget.FUNCTION)
@Retention(AnnotationRetention.RUNTIME)
annotation class Mock(val enabled: Boolean)

/**
 * Annotation to define a mock response headers for a retrofit API call.
 * @param headers The headers of the response.
 */
@Target(AnnotationTarget.FUNCTION)
@Retention(AnnotationRetention.RUNTIME)
annotation class MockResponseHeaders(
    val headers: Array<Header>,
) {

    /**
     * Annotation to define a header for a mock response.
     * @param name The name of the header.
     * @param value The value of the header.
     */
    annotation class Header(
        val name: String,
        val value: String,
    )
}

/**
 * Annotation to define a mock response code for a retrofit API call.
 * @param code The response code.
 */
@Target(AnnotationTarget.FUNCTION)
@Retention(AnnotationRetention.RUNTIME)
annotation class MockResponseCode(
    val code: Int,
)

/**
 * Annotation to define a mock response message for a retrofit API call.
 * @param message The response message.
 */
@Target(AnnotationTarget.FUNCTION)
@Retention(AnnotationRetention.RUNTIME)
annotation class MockResponseMessage(
    val message: String,
)

/**
 * Annotation to define a json mock response body for a retrofit API call.
 * @param jsonStringBody The response body as a json string.
 */
@Target(AnnotationTarget.FUNCTION)
@Retention(AnnotationRetention.RUNTIME)
annotation class MockResponseBody(
    val jsonStringBody: String,
)

/**
 * Annotation to define a file mock response body for a retrofit API call.
 * @param fileBodyPath The path to the asset file containing the response body.
 */
@Target(AnnotationTarget.FUNCTION)
@Retention(AnnotationRetention.RUNTIME)
annotation class MockResponseFileBody(
    val fileBodyPath: String,
)

/**
 * Annotation to define a random mock response for a retrofit API call.
 * @param randomStringBody The list of response bodies as json strings. One of these will be randomly selected.
 */
@Target(AnnotationTarget.FUNCTION)
@Retention(AnnotationRetention.RUNTIME)
annotation class RandomMockResponseBody(
    val randomStringBody: Array<String>,
)

/**
 * Annotation to define a random mock response for a retrofit API call.
 * @param randomFileBodyPath The list of paths to the asset files containing the response bodies. One of these will be randomly selected.
 */
@Target(AnnotationTarget.FUNCTION)
@Retention(AnnotationRetention.RUNTIME)
annotation class RandomMockResponseFileBody(
    val randomFileBodyPath: Array<String>,
)
