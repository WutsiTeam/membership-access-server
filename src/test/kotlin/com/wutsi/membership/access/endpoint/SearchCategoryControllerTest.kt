package com.wutsi.membership.access.endpoint

import com.wutsi.membership.access.dto.SearchCategoryRequest
import com.wutsi.membership.access.dto.SearchCategoryResponse
import org.junit.jupiter.api.Test
import org.springframework.boot.test.context.SpringBootTest
import org.springframework.boot.test.web.server.LocalServerPort
import org.springframework.http.HttpStatus
import org.springframework.test.context.jdbc.Sql
import kotlin.test.assertEquals
import kotlin.test.assertTrue

@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
@Sql(value = ["/db/clean.sql", "/db/SearchCategoryController.sql"])
class SearchCategoryControllerTest : AbstractLanguageAwareControllerTest() {
    @LocalServerPort
    val port: Int = 0

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
}
