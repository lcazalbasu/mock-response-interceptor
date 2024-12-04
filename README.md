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