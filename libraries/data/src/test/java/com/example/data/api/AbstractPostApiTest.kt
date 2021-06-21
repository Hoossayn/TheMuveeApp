package com.example.data.api

import com.example.data.model.movies.Movie
import com.example.test_util.RESPONSE_JSON_PATH
import com.example.test_util.utils.convertFromJsonToListOf
import com.example.test_util.utils.getResourceAsText
import okhttp3.mockwebserver.MockResponse
import okhttp3.mockwebserver.MockWebServer
import org.junit.jupiter.api.AfterEach
import org.junit.jupiter.api.BeforeEach
import java.io.IOException

/**
 * Abstract class fo testing api with [MockWebServer] and [JUnit5]
 */
abstract class AbstractMovieApiTest {

    lateinit var mockWebServer: MockWebServer

    private val responseAsString by lazy {
        getResourceAsText(RESPONSE_JSON_PATH)
    }

    val postList by lazy {
        convertFromJsonToListOf<Movie>(getResourceAsText(RESPONSE_JSON_PATH))!!
    }

    @BeforeEach
    open fun setUp() {

        println("AbstractMovieApiTest SET UP")

        mockWebServer = MockWebServer()
            .apply {
                start()
//                dispatcher = PostDispatcher()
            }
    }

    @AfterEach
    open fun tearDown() {
        println("AbstractMovieApiTest TEAR DOWN")
        mockWebServer.shutdown()
    }

    @Throws(IOException::class)
    fun enqueueResponse(
        code: Int = 200,
        headers: Map<String, String>? = null
    ): MockResponse {

        // Define mock response
        val mockResponse = MockResponse()
        // Set response code
        mockResponse.setResponseCode(code)

        // Set headers
        headers?.let {
            for ((key, value) in it) {
                mockResponse.addHeader(key, value)
            }
        }

        // Set body
        mockWebServer.enqueue(
            mockResponse.setBody(responseAsString)
        )
        println(
            "🍏 enqueueResponse() ${Thread.currentThread().name}," +
                " ${responseAsString.length} $mockResponse"
        )
        return mockResponse
    }
}
