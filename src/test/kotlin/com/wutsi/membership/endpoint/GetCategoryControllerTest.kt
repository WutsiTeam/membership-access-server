package com.wutsi.membership.endpoint

import com.fasterxml.jackson.databind.ObjectMapper
import com.wutsi.membership.dto.GetCategoryResponse
import com.wutsi.membership.error.ErrorURN
import com.wutsi.platform.core.error.ErrorResponse
import org.junit.jupiter.api.BeforeEach
import org.junit.jupiter.api.Test
import org.junit.jupiter.api.assertThrows
import org.springframework.boot.test.context.SpringBootTest
import org.springframework.boot.test.web.server.LocalServerPort
import org.springframework.http.HttpRequest
import org.springframework.http.HttpStatus
import org.springframework.http.client.ClientHttpRequestExecution
import org.springframework.http.client.ClientHttpRequestInterceptor
import org.springframework.http.client.ClientHttpResponse
import org.springframework.web.client.HttpClientErrorException
import org.springframework.web.client.RestTemplate
import kotlin.test.assertEquals

@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
class GetCategoryControllerTest : ClientHttpRequestInterceptor {
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
    fun get() {
        // WHEN
        val response = rest.getForEntity(url(1000), GetCategoryResponse::class.java)

        // THEN
        assertEquals(HttpStatus.OK, response.statusCode)

        val category = response.body!!.category
        assertEquals(1000L, category.id)
        assertEquals("Advertising/Marketing", category.title)
    }

    @Test
    fun french() {
        // GIVEN
        language = "fr"

        // WHEN
        val response = rest.getForEntity(url(1000), GetCategoryResponse::class.java)

        // THEN
        assertEquals(HttpStatus.OK, response.statusCode)

        val category = response.body!!.category
        assertEquals(1000L, category.id)
        assertEquals("Marketing/Publicité", category.title)
    }

    @Test
    fun notFound() {
        // WHEN
        val ex = assertThrows<HttpClientErrorException> {
            rest.getForEntity(url(9999), GetCategoryResponse::class.java)
        }

        // THEN
        assertEquals(HttpStatus.NOT_FOUND, ex.statusCode)

        val response = ObjectMapper().readValue(ex.responseBodyAsString, ErrorResponse::class.java)
        assertEquals(ErrorURN.CATEGORY_NOT_FOUND.urn, response.error.code)
    }

    private fun url(id: Long) = "http://localhost:$port/v1/categories/$id"

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
