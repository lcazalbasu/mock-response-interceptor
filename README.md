# mock-response-interceptor

The [MockResponseInterceptor] is an OkHttp interceptor that allows you to mock the response of a
request.

# Installation
add the following maven repository to your settings.gradle.kts file:

```
dependencyResolutionManagement {
    repositories {
        maven {
            url = uri("https://maven.pkg.github.com/lcazalbasu/mock-response-interceptor")
        }
    }
}
```

add the following dependency to your build.gradle.kts file:

```
implementation("com.lcazalbasu:mockresponseinterceptor:1.0.0")
```

# Configuration

To use the [MockResponseInterceptor] you need to add it to the OkHttpClient.Builder.

```kotlin
val logger = HttpLoggingInterceptor().apply {
    level = HttpLoggingInterceptor.Level.BODY
}
val client = OkHttpClient.Builder()
    .addInterceptor(logger)
    // Set this interceptor as the last interceptor
    .addInterceptor(MockResponseInterceptor())
    .build()
```

# Annotations

The [MockResponseInterceptor] uses annotations to set the mock response.

- **[Mock]**: Enable or disable the mock response.
- **[MockResponseHeaders]**: Set the response headers.
- **[MockResponseCode]**: Set the response code.
- **[MockResponseMessage]**: Set the response message.
- **[MockResponseBody]**: Set the response body as a json string.
- **[MockResponseFileBody]**: Set the response body from a file in the assets folder.
- **[RandomMockResponseBody]**: Set a random response body from a list of strings.
- **[RandomMockResponseFileBody]**: Set a random response body from a list of files in the assets

# Examples:

Success response
```kotlin
// Enable the mock response
@Mock(enabled = true)
// Set the response file path from the assets folder
@MockResponseFileBody(fileBodyPath = "responses/news_list_200.json")
// Define the Retrofit API call
@GET("/api/news/list")
suspend fun getNewsListResponseOk(): List<NewsResponse>
```

Error response
```kotlin
@Mock
@MockResponseCode(500)
@MockResponseMessage("Internal Server Error")
@MockResponseFileBody(fileBodyPath = "responses/news_list_500.json")
@GET("/api/news/list")
suspend fun getNewsListResponseError(): List<NewsResponse>
```

Random response
```kotlin
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
```

# Usage
If you are using the [MockResponseInterceptor] in a test environment,
you can use the **[Mock] annotation to enable or disable the mock response**.

If you will set the [Mock] annotation to true,
you can use the [MockResponseHeaders] to set the response headers,
[MockResponseCode] to set the response code,
[MockResponseMessage] to set the response message.

If you want to set the response body you can use one of the following annotations:
[MockResponseBody] to set the response body as a json string,
[MockResponseFileBody] to set the response body from a file in the assets folder,
[RandomMockResponseBody] to set a random response body from a list of strings,
[RandomMockResponseFileBody] to set a random response body from a list of files in the assets
folder.
**There annotations will be checked in the order they are listed.**

**So, if you set both [MockResponseBody] and [MockResponseFileBody],
the [MockResponseBody] will be used.**