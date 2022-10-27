package com.wutsi.membership.endpoint

import com.wutsi.membership.dto.SearchCategoryRequest
import com.wutsi.membership.dto.SearchCategoryResponse
import org.junit.jupiter.api.BeforeEach
import org.junit.jupiter.api.Test
import org.springframework.boot.test.context.SpringBootTest
import org.springframework.boot.test.web.server.LocalServerPort
import org.springframework.http.HttpRequest
import org.springframework.http.HttpStatus
import org.springframework.http.client.ClientHttpRequestExecution
import org.springframework.http.client.ClientHttpRequestInterceptor
import org.springframework.http.client.ClientHttpResponse
import org.springframework.web.client.RestTemplate
import kotlin.test.assertEquals
import kotlin.test.assertTrue

@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
class SearchCategoryControllerTest : ClientHttpRequestInterceptor {
    @LocalServerPort
    val port: Int = 0

    private var language: String? = null

    private val rest = RestTemplate()

    @BeforeEach
    fun setUp() {
        language = null
        rest.interceptors = listOf(this)
    }

    @Test
    fun all() {
        val request = SearchCategoryRequest()
        val response = rest.postForEntity(url(), request, SearchCategoryResponse::class.java)

        assertEquals(HttpStatus.OK, response.statusCode)

        val categories = response.body!!.categories
        assertEquals(48, categories.size)
    }

    @Test
    fun overflow() {
        val request = SearchCategoryRequest(
            offset = 1000
        )
        val response = rest.postForEntity(url(), request, SearchCategoryResponse::class.java)

        assertEquals(HttpStatus.OK, response.statusCode)

        val categories = response.body!!.categories
        assertTrue(categories.isEmpty())
    }

    private fun url() = "http://localhost:$port/v1/categories/search"

    override fun intercept(
        request: HttpRequest,
        body: ByteArray,
        execution: ClientHttpRequestExecution
    ): ClientHttpResponse {
        if (language != null) {
            request.headers.add("Accept-Language", language)
        }
        return execution.execute(request, body)
    }
}
